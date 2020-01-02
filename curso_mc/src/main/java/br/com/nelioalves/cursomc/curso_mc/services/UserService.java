package br.com.nelioalves.cursomc.curso_mc.services;

import org.springframework.security.core.context.SecurityContextHolder;

import br.com.nelioalves.cursomc.curso_mc.security.UserSS;

public class UserService {

	/* retorno usuario que esta logado no sistema */
	public static UserSS authenticated() {
		try {
			return ((UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		} catch (Exception e) {
			return null;
		}
	}
}
