package br.com.nelioalves.cursomc.curso_mc.repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.nelioalves.cursomc.curso_mc.domain.Cidade;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Integer> {
	
	@Query("select obj from Cidade obj where obj.estado.id = :estado_id order by obj.nome")
	public Collection<Cidade>findCidade(Long estado_id);
}
