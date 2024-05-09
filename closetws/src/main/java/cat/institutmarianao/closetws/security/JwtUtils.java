package cat.institutmarianao.closetws.security;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

@Component
public class JwtUtils {
	
	@Autowired
	private MessageSource messageSource;
	
	private static final long MAX_TOKEN_DURATION_MILISECONDS=1800000L;
	
	@Value("${security.jwt.key.private}")
	private String privateKey;
	
	@Value("${security.jwt.user.generator}")
	private String userGenerator;
	
	public String createToken(Authentication authentication) {
		Algorithm algorithm= Algorithm.HMAC256(privateKey);
		String username= authentication.getPrincipal().toString();
		String jwtToken=JWT.create()
				.withIssuer(userGenerator)
				.withSubject(username)
				.withIssuedAt(new Date())
				.withExpiresAt(new Date(System.currentTimeMillis() + MAX_TOKEN_DURATION_MILISECONDS))
				.withJWTId(UUID.randomUUID().toString())
				.withNotBefore(new Date(System.currentTimeMillis()))
				.sign(algorithm);
		return jwtToken;
	}
	
	public DecodedJWT validateToken(String token) {
		try {
			Algorithm algorithm= Algorithm.HMAC256(privateKey);
			
			JWTVerifier verifier= JWT.require(algorithm)
					.withIssuer(userGenerator)
					.build();
			DecodedJWT decodedJWT= verifier.verify(token);
			return decodedJWT;
		} catch (JWTVerificationException e) {
			throw new JWTVerificationException(messageSource.getMessage("error.JwtUtils.token",
					null, LocaleContextHolder.getLocale()));
		}
	}
	
	public String extractUsername(DecodedJWT decodedJWT) {
		return decodedJWT.getSubject().toString();
	}

	public Claim getSpecificClaim(DecodedJWT decodedJWT, String claimName) {
		return decodedJWT.getClaim(claimName);
	}
	
	public Map<String, Claim> returnAllClaims(DecodedJWT decodedJWT){
		return decodedJWT.getClaims();
	}
}
