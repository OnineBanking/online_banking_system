package com.dxc.obs.api.util;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;

import com.dxc.obs.api.dto.JwtTokenDTO;
import com.dxc.obs.api.entity.UserDetails;
import com.dxc.obs.api.exception.BadRequestException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtUtil {

	private final Logger logger = LoggerFactory.getLogger(JwtUtil.class);

	@Value("${obs.app.jwtSecret}")
	private String jwtSecret;

	@Value("${obs.app.jwtExpirationMs}")
	private int jwtExpirationMs;

	@Value("${obs.app.jwtCookieName}")
	private String jwtCookie;

	private final String jwtIssuer = "test_issuer";

	public String generateAccessToken(UserDetails user) {

		JwtBuilder builder = Jwts.builder().setSubject(user.getEmail()).setIssuedAt(Date.from(Instant.now()))
				.setExpiration(Date.from(Instant.now().plus(15, ChronoUnit.MINUTES))).signWith(SignatureAlgorithm.HS512, jwtSecret);
		return builder.compact();

		/*
		 * return Jwts.builder().setSubject(user.getEmail()).setIssuer(jwtIssuer)
		 * .claim("ROLE", user.getRole().getRoleName()).claim("SUBJECT",
		 * user.getUserId()).setIssuedAt(new Date())
		 * 
		 * .setExpiration(new Date(System.currentTimeMillis() + 7 * 24 * 60 * 60 *
		 * 1000))// 1 week
		 * 
		 * 
		 * .setExpiration(new Date(System.currentTimeMillis() + 1 * 60 * 1000))// 1min
		 * 
		 * .signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
		 */

	}

	// TODO Throw And Handle Below Exceptions Under
	// Global Exception Handler

	public boolean validate(String token) {

		try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
			return true;
		} catch (MalformedJwtException ex) {
			logger.error("Invalid JWT token - {}", ex.getMessage());
		} catch (ExpiredJwtException ex) {
			logger.error("Expired JWT token - {}", ex.getMessage());
		} catch (UnsupportedJwtException ex) {
			logger.error("Unsupported JWT token - {}", ex.getMessage());
		} catch (IllegalArgumentException ex) {
			logger.error("JWT claims string is empty - {}", ex.getMessage());
		}
		return false;
	}

	public JwtTokenDTO getJwtTokenDTO(String token) {
		Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();

		return JwtTokenDTO.builder()
				.subject(claims.getSubject())
				.expirationDate(claims.getExpiration())
				//.role(claims.get("ROLE", String.class))
				.build();
	}

	public ResponseCookie getCleanJwtCookie() {
		ResponseCookie cookie = ResponseCookie.from(jwtCookie, null).path("/user").build();
		return cookie;
	}
}
