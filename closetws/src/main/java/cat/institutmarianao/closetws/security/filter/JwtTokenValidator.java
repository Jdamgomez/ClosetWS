package cat.institutmarianao.closetws.security.filter;

import java.io.IOException;

import org.springframework.http.HttpHeaders;
import org.springframework.lang.NonNull;
import org.springframework.web.filter.OncePerRequestFilter;

import cat.institutmarianao.closetws.security.JwtUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class JwtTokenValidator extends OncePerRequestFilter{
	
	private JwtUtils jwtUtils;
	

	@Override
	protected void doFilterInternal(@NonNull HttpServletRequest request, 
									@NonNull HttpServletResponse response, 
									@NonNull FilterChain filterChain) throws ServletException, IOException {
		
		String jwtToken= request.getHeader(HttpHeaders.AUTHORIZATION);
		
		if(!"".equals(jwtToken) && jwtToken!=null) {
			jwtToken= jwtToken.substring(7);
			jwtUtils.validateToken(jwtToken);
		}
		
		filterChain.doFilter(request, response);
	}

}
