package br.com.nelioalves.cursomc.curso_mc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.nelioalves.cursomc.curso_mc.domain.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

	/* fazer busca por email, o framework vai criar essa busca automaticamente */
	@Transactional(readOnly = true)
	Cliente findByEmail(String email);
	
}
