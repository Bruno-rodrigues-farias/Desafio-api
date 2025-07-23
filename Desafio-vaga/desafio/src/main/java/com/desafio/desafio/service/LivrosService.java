package com.desafio.desafio.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desafio.desafio.dtos.Livro.LivroRequest;
import com.desafio.desafio.dtos.Livro.LivroResponse;
import com.desafio.desafio.exception.ResourceNotFoundException;
import com.desafio.desafio.model.Livro;
import com.desafio.desafio.repository.AutorRepository;
import com.desafio.desafio.repository.CategoriaRepository;
import com.desafio.desafio.repository.LivroRepository;

@Service
public class LivrosService {

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    private LivroResponse toResponse(Livro livro) {
        return new LivroResponse(
            livro.getId(),
            livro.getTitulo(),
            livro.getIsbn(),
            livro.getAnoPublicacao(),
            livro.getPreco(),
            livro.getAutor().getNome(),
            livro.getCategoria().getNome()
        );
    }

    public List<LivroResponse> listarLivrosFiltrados(Long categoriaId, Long autorId, Integer ano) {
        List<Livro> livros = livroRepository.findAll();

        return livros.stream()
            .filter(l -> categoriaId == null || l.getCategoria().getId().equals(categoriaId))
            .filter(l -> autorId == null || l.getAutor().getId().equals(autorId))
            .filter(l -> ano == null || l.getAnoPublicacao().equals(ano))
            .map(this::toResponse)
            .collect(Collectors.toList());
    }

    public LivroResponse buscarLivros(Long id) {
        Livro livro = livroRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Livro não encontrado"));

        return toResponse(livro);
    }

    public LivroResponse criarLivros(LivroRequest dto) {
        var autor = autorRepository.findById(dto.getAutorId())
                .orElseThrow(() -> new ResourceNotFoundException("Autor não encontrado"));

        var categoria = categoriaRepository.findById(dto.getCategoriaId())
                .orElseThrow(() -> new ResourceNotFoundException("Categoria não encontrada"));

        Livro livro = new Livro();
        livro.setTitulo(dto.getTitulo());
        livro.setIsbn(dto.getIsbn());
        livro.setAnoPublicacao(dto.getAnoPublicacao());
        livro.setPreco(dto.getPreco());
        livro.setAutor(autor);
        livro.setCategoria(categoria);

        livro = livroRepository.save(livro);
        return toResponse(livro);
    }

    public LivroResponse atualizarLivros(Long id, LivroRequest dto) {
        Livro livro = livroRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Livro não encontrado"));

        var autor = autorRepository.findById(dto.getAutorId())
                .orElseThrow(() -> new ResourceNotFoundException("Autor não encontrado"));

        var categoria = categoriaRepository.findById(dto.getCategoriaId())
                .orElseThrow(() -> new ResourceNotFoundException("Categoria não encontrada"));

        livro.setTitulo(dto.getTitulo());
        livro.setIsbn(dto.getIsbn());
        livro.setAnoPublicacao(dto.getAnoPublicacao());
        livro.setPreco(dto.getPreco());
        livro.setAutor(autor);
        livro.setCategoria(categoria);

        livro = livroRepository.save(livro);
        return toResponse(livro);
    }

    public void deletar(Long id) {
        Livro livro = livroRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Livro não existe ou já foi excluído"));

        livroRepository.delete(livro);
    }

    public LivroResponse buscarTitulo(String titulo) {
        Livro livro = livroRepository.findByTitulo(titulo)
                .orElseThrow(() -> new ResourceNotFoundException("Título não encontrado"));

        return toResponse(livro);
    }
}
