package com.senai.api.security;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.senai.api.entities.User;
import com.senai.api.repositories.UserRepository;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTGenerator {

	@Value("${jwt.lifetime.token}")
	private Long lifeTimeToken;
	@Value("${jwt.secret}")
	private String tokenSecret;

		@Autowired
		private UserRepository userRepository;

		public String generateToken(Authentication authentication) {

			String username = authentication.getName();
			Date currentDate = new Date();
			Date expireDate = new Date(currentDate.getTime() + lifeTimeToken);
			Optional<User> user = userRepository.findByEmail(username);
			Map<String, Object> claims = new HashMap<>();
			claims.put("id", user.get().getId());
			claims.put("nome", user.get().getName());
			claims.put("role", authentication.getAuthorities());

			String token = Jwts.builder().setClaims(claims).setSubject(username).setIssuedAt(new Date())
					.setExpiration(expireDate).signWith(SignatureAlgorithm.HS512, tokenSecret).compact();

			return token;
		}

		public String getUsernameFromJWT(String token) {

			Claims claims = Jwts.parser().setSigningKey(tokenSecret).parseClaimsJws(token).getBody();

			return claims.getSubject();
		}

		public boolean validateToken(String token) {
			try {
				Jwts.parser().setSigningKey(tokenSecret).parse(token);
				return true;
			} catch (Exception ex) {
				throw new AuthenticationCredentialsNotFoundException("Token expirado ou incorreto.");
			}
		}
}
