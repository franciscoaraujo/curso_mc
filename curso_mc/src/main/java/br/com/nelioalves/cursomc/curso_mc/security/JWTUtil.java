package br.com.nelioalves.cursomc.curso_mc.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/*Criar uma classe JWTUtil (@Component) com a operação String generateToken(String username)*/
@Component
public class JWTUtil {

	@Value("${jwt.secret}")
	private String secret;

	@Value("${jwt.expiration}")
	private Long expiration;

	/* Criando o metodo para gerar o token */
	public String generateToken(String userName) {
		return Jwts.builder().setSubject(userName).setExpiration(new Date(System.currentTimeMillis() + expiration))
				.signWith(SignatureAlgorithm.HS512, secret.getBytes()).compact();
	}
	
	public boolean tokenValido(String token) {
		Claims claims = getClaims(token);// reivindicando o usuairo pelo token
		if (claims != null) {
			String username = claims.getSubject();
			Date expirationDate = claims.getExpiration();
			Date now = new Date(System.currentTimeMillis());
			if (username != null && expirationDate != null && now.before(expirationDate)) {
				return true;
			}
		}
		return false;
	}

	/* Aqui esta reivindicando o usuario e o tempo de expiracao */
	private Claims getClaims(String token) {
		try {
			return Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token).getBody();
		} catch (Exception e) {
			return null;
		}

	}

	public String getUserName(String token) {
		Claims claims = getClaims(token);// reivindicando o usuario pelo token
		if (claims != null) {
			return claims.getSubject();
		}
		return null;
	}

}
