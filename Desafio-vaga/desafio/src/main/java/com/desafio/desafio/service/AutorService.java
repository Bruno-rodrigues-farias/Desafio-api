package com.desafio.desafio.service;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desafio.desafio.dtos.Autor.AutorRequest;
import com.desafio.desafio.dtos.Autor.AutorResponse;
import com.desafio.desafio.exception.ResourceNotFoundException;
import com.desafio.desafio.model.Autor;
import com.desafio.desafio.model.Livro;
import com.desafio.desafio.repository.AutorRepository;
import com.desafio.desafio.repository.LivroRepository;


@Service

public class AutorService {

    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private LivroRepository livroRepository;

    //criar autor
    public AutorResponse salvarAutor(AutorRequest dto) {
        Autor autor = new Autor(null, dto.getNome(), dto.getEmail(), dto.getDataNascimento(), null);
        autor = autorRepository.save(autor);
        return new AutorResponse(autor.getId(), autor.getNome(), autor.getEmail(), autor.getDataNascimento());
    }


    //listar todos autores
    public List<AutorResponse> listarAutores(){
        return autorRepository.findAll().stream()
                    .map(autor -> new AutorResponse(autor.getId(), autor.getNome(), autor.getEmail(), autor.getDataNascimento()))
                    .collect(Collectors.toList());

    }

    //buscar por id
    public Autor buscarPorId(Long id){
        return autorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Autor não encontrado:"));
    }

    //atualizar autor
    public AutorResponse atualizarAutor(Long id, AutorRequest request ){
        Autor autor = buscarPorId(id);

        autor.setNome(request.getNome());
        autor.setEmail(request.getEmail());
        autor.setDataNascimento(request.getDataNascimento());

        Autor atualizado = autorRepository.save(autor);

        return new AutorResponse(
            atualizado.getId(),
            atualizado.getNome(),
            atualizado.getEmail(),
            atualizado.getDataNascimento()
        );
    }


    //deletar autor
    public void deletarAutor(Long id){
        Autor autor = autorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Autor não encontrado ou já excluido"));

        autorRepository.delete(autor);

    }

    //listar livro do autor
    public List<Livro> listarLivrosPorAutor(Long autorId){
        autorRepository.findById(autorId)
                .orElseThrow(()-> new ResourceNotFoundException("Autor não encontrado"));

        return livroRepository.findByAutorId(autorId);
    }

}
