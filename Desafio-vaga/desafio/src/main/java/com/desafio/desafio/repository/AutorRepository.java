package com.desafio.desafio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.desafio.desafio.model.Autor;
@Repository
public interface AutorRepository extends JpaRepository<Autor, Long> {

}
