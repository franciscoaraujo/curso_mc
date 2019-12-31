package br.com.nelioalves.cursomc.curso_mc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.nelioalves.cursomc.curso_mc.domain.Cliente;
import br.com.nelioalves.cursomc.curso_mc.repositories.ClienteRepository;
import br.com.nelioalves.cursomc.curso_mc.security.UserSS;


/**
 * Verifica a o check list da aula "Implementando autenticação e geracao do Token JWT"
 * @author foaj
 * Criando uma class de servico conforme o contrato do Spring Security(Implemenst UserDetaisService)
 */
@Service
public class UserDetailsServiceImp implements UserDetailsService {

	@Autowired
	private ClienteRepository repo;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Cliente cli =repo.findByEmail(email);
		if(cli == null) {
			throw new UsernameNotFoundException(email);
		}
		return new UserSS(cli.getId(), cli.getEmail(), cli.getSenha(), cli.getPerfis());
	}
	
	
}
