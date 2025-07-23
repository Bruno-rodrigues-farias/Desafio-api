package com.desafio.desafio.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.desafio.desafio.model.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {


}
