package com.desafio.desafio.controller.Livro;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.desafio.desafio.dtos.Livro.LivroRequest;
import com.desafio.desafio.dtos.Livro.LivroResponse;
import com.desafio.desafio.service.LivrosService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/livros")
@Tag(name = "Livros", description = "Endpoints para gerenciamento de livros")
public class LivroController {

    @Autowired
    private LivrosService livrosService;

    @GetMapping("/ping")
public ResponseEntity<String> ping() {
    return ResponseEntity.ok("pong");
}

    @GetMapping
    @Operation(summary = "Busca todos os livros",description = "Lista todos os livros por Id, autor e ano")
    public ResponseEntity<List<LivroResponse>> listarLivros(
            @RequestParam(required = false) Long categoriaId,
            @RequestParam(required = false) Long autorId,
            @RequestParam(required = false) Integer ano) {

        List<LivroResponse> livros = livrosService.listarLivrosFiltrados(categoriaId, autorId, ano);
        return ResponseEntity.ok(livros);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca livros por Id",description = "Lista os livros por Id")
    public ResponseEntity<LivroResponse> buscarPorId(@PathVariable Long id) {
        LivroResponse livro = livrosService.buscarLivros(id);
        return ResponseEntity.ok(livro);
    }

    @PostMapping
    @Operation(summary = "Criação de livros",description = "Endpoint para criação de livros")
    public ResponseEntity<LivroResponse> criarLivro(@RequestBody @Valid LivroRequest request) {
        LivroResponse livro = livrosService.criarLivros(request);
        return ResponseEntity.ok(livro);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualização de livro",description = "Endpoint para atualização de livro")
    public ResponseEntity<LivroResponse> atualizarLivro(
            @PathVariable Long id,
            @RequestBody @Valid LivroRequest request) {
        LivroResponse livro = livrosService.atualizarLivros(id, request);
        return ResponseEntity.ok(livro);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar d livros",description = "Endpoint para deletar um livros por id")
    public ResponseEntity<Void> deletarLivro(@PathVariable Long id) {
        livrosService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    @Operation(summary = "Buscar livros por titulo",description = "Endpoint para buscar um livros por um titulo espercifico")
    public ResponseEntity<LivroResponse> buscarPorTitulo(@RequestParam String titulo) {
        LivroResponse livro = livrosService.buscarTitulo(titulo);
        return ResponseEntity.ok(livro);
    }
}
