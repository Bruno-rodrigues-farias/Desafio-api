package com.desafio.desafio.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.desafio.desafio.model.Livro;
@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {
    List<Livro> findByAutorId(Long autorId);

    Optional<Livro> findByTitulo( String titulo);

    List<Livro> findByCategoriaId(Long categoriaId);
}
