package br.com.nelioalves.cursomc.curso_mc.repositories;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.nelioalves.cursomc.curso_mc.domain.Cliente;
import br.com.nelioalves.cursomc.curso_mc.domain.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
	
	@Transactional(readOnly=true)
	Page<Pedido> findByCliente(Cliente cliente, Pageable pageRequest);
	
}
