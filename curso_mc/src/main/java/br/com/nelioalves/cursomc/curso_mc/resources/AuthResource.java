package br.com.nelioalves.cursomc.curso_mc.resources;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.nelioalves.cursomc.curso_mc.security.JWTUtil;
import br.com.nelioalves.cursomc.curso_mc.security.UserSS;
import br.com.nelioalves.cursomc.curso_mc.services.UserService;

@RestController
@RequestMapping(value = "/auth")
public class AuthResource {
	
	@Autowired
	private JWTUtil jwtUtil;
	
	@RequestMapping(value = "/refresh_token", method = RequestMethod.POST)
	public ResponseEntity<Void> refreshToken(HttpServletResponse response) {
		
		UserSS user = UserService.authenticated();
		String token = jwtUtil.generateToken(user.getUsername());//gerando um novo tokem com o meu usuario
		response.addHeader("Authorization", "Bearer " + token);//adicionando o token na requisicao
		
		return ResponseEntity.noContent().build();
	}
}
