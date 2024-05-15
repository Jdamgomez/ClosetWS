package cat.institutmarianao.closetws.security.filter;

import java.io.IOException;

import org.springframework.http.HttpHeaders;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.interfaces.DecodedJWT;

import cat.institutmarianao.closetws.security.JwtUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RequestFilter extends OncePerRequestFilter{
	
	private JwtUtils jwtUtils;
	

	@Override
	protected void doFilterInternal(@NonNull HttpServletRequest request, 
									@NonNull HttpServletResponse response, 
									@NonNull FilterChain filterChain) throws ServletException, IOException {
		
		String jwtToken= request.getHeader(HttpHeaders.AUTHORIZATION);
		
		if(!"".equals(jwtToken) && jwtToken!=null) {
			jwtToken= jwtToken.substring(7);
			DecodedJWT decodedJWT= jwtUtils.validateToken(jwtToken);
			String username=jwtUtils.extractUsername(decodedJWT);
			SecurityContext context= SecurityContextHolder.getContext();
			Authentication authentication= new UsernamePasswordAuthenticationToken(username, null, jwtUtils.getAuthorities());
			context.setAuthentication(authentication);
			SecurityContextHolder.setContext(context);
		}
		
		filterChain.doFilter(request, response);
	}
}
