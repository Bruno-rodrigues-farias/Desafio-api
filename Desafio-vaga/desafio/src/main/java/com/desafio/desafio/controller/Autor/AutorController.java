package com.desafio.desafio.controller.Autor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.desafio.desafio.dtos.Autor.AutorRequest;
import com.desafio.desafio.dtos.Autor.AutorResponse;
import com.desafio.desafio.model.Autor;
import com.desafio.desafio.model.Livro;
import com.desafio.desafio.service.AutorService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
@Tag(name = "Autores", description = "Endpoints para gerenciamento de autores")
public class AutorController {

    @Autowired
    private AutorService autorService;


  @PostMapping("/autores")
  @Operation(summary = "Criar autor", description = "Criar um novo autor com nome, e-mail e data de nascimento")
    public ResponseEntity<AutorResponse> criarAutor(@Valid @RequestBody AutorRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(autorService.salvarAutor(request));
    }

    //buscar por Id
    @GetMapping("/autores/{id}")
    @Operation(summary = "Buscar autor por ID", description = "Retorna os dados de um autor especifico")
    public ResponseEntity<AutorResponse> buscarPorId(@PathVariable Long id){
      Autor autor = autorService.buscarPorId(id);

      AutorResponse dto = new AutorResponse(
        autor.getId(),
        autor.getNome(),
        autor.getEmail(),
        autor.getDataNascimento()
      );

      return ResponseEntity.ok(dto);
    }

    //listar todos autores
    @GetMapping("/autores")
    @Operation(summary = "Listar autores",description = "Retorna a lista de todos os autores")
    public ResponseEntity<List<AutorResponse>> listarAutores(){
      List<AutorResponse> autores = autorService.listarAutores();

      return ResponseEntity.ok(autores);
    }

    //atualizar autor
    @PutMapping("/autores/{id}")
    @Operation(summary = "Atualizar autor", description = "Atualiza um autor pelo id")
    public ResponseEntity<AutorResponse> atualizarAutor(@PathVariable Long id, @RequestBody @Valid AutorRequest autor){

      AutorResponse autorAtualizado = autorService.atualizarAutor(id, autor);
      return ResponseEntity.ok(autorAtualizado);
    }

    //deletar autor
    @DeleteMapping("/autores/{id}")
    @Operation(summary = "Deleta um autor", description = "Remove um autor pelo id")
    public ResponseEntity<Void> deletarAutor(@PathVariable Long id){
      autorService.deletarAutor(id);

      return ResponseEntity.noContent().build();
    }

    //listar livro do autor
    @GetMapping("/autores/{id}/livros")
    @Operation(summary = "Busca um livro de um determinado autor", description = "buscar livro por id de um autor")
    public ResponseEntity<List<Livro>> listarLivroDoAutor(@PathVariable Long id){
      List<Livro> livros = autorService.listarLivrosPorAutor(id);

      return ResponseEntity.ok(livros);
    }

}