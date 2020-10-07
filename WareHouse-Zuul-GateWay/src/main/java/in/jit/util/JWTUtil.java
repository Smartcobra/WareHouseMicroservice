package in.jit.util;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTUtil {

	@Value("${app.secret}")
	private String secrete;

	public boolean validateToken(String token, String username) {
		String usernameInToken = getUsername(token);
		return (usernameInToken.equals(username) && !isTokenExpired(token));
	}

	private boolean isTokenExpired(String token) {
		final Date expiration = getExpDate(token);
		return expiration.before(new Date(System.currentTimeMillis()));
	}

	public String getUsername(String token) {
		return getClaims(token).getSubject();
	}

	public Date getExpDate(String token) {
		return getClaims(token).getExpiration();
	}

	public Claims getClaims(String token) {
		return Jwts.parser()
				.setSigningKey(secrete.getBytes())
				.parseClaimsJws(token).getBody();
	}

	public String generateToken(String subject) {
		return Jwts.builder()
				.setSubject(subject)
				.setIssuer("ABSWareHouse")
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(30)))
				.signWith(SignatureAlgorithm.HS512, secrete.getBytes()).compact();
	}

}
