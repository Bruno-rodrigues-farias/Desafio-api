package com.desafio.desafio.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desafio.desafio.dtos.Categoria.CategoriaRequest;
import com.desafio.desafio.dtos.Categoria.CategoriaResponse;
import com.desafio.desafio.dtos.Livro.LivroResponse;
import com.desafio.desafio.exception.ResourceNotFoundException;
import com.desafio.desafio.model.Categoria;
import com.desafio.desafio.repository.CategoriaRepository;
import com.desafio.desafio.repository.LivroRepository;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private LivroRepository livroRepository;

    private CategoriaResponse toResponse(Categoria categoria) {
        return new CategoriaResponse(categoria.getId(), categoria.getNome());
    }

    public List<CategoriaResponse> listarCategorias() {
        return categoriaRepository.findAll()
            .stream()
            .map(this::toResponse)
            .collect(Collectors.toList());
    }

    public CategoriaResponse criarCategoria(CategoriaRequest request) {
        Categoria categoria = new Categoria();
        categoria.setNome(request.getNome());

        categoria = categoriaRepository.save(categoria);
        return toResponse(categoria);
    }

    public List<LivroResponse> listarLivrosPorCategoria(Long categoriaId) {
        categoriaRepository.findById(categoriaId)
                .orElseThrow(()-> new ResourceNotFoundException("Categoria não encontrada"));

    return livroRepository.findByCategoriaId(categoriaId)
            

            .stream()
            .map(livro -> new LivroResponse(
                livro.getId(),
                livro.getTitulo(),
                livro.getIsbn(),
                livro.getAnoPublicacao(),
                livro.getPreco(),
                livro.getAutor().getNome(),
                livro.getCategoria().getNome()
            ))
            .collect(Collectors.toList());
}
}