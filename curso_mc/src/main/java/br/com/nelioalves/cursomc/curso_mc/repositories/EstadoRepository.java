package br.com.nelioalves.cursomc.curso_mc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.nelioalves.cursomc.curso_mc.domain.Estado;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Integer> {
}
