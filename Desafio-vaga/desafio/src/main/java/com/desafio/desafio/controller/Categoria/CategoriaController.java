package com.desafio.desafio.controller.Categoria;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.desafio.desafio.dtos.Categoria.CategoriaRequest;
import com.desafio.desafio.dtos.Categoria.CategoriaResponse;
import com.desafio.desafio.dtos.Livro.LivroResponse;
import com.desafio.desafio.service.CategoriaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/categorias")
@Tag(name = "Categorias", description = "Endpoints para gerenciamento de categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    @Operation(summary = "Listar todas as categorias",description = "Lista todas as categorias criadas")
    public ResponseEntity<List<CategoriaResponse>> listarCategorias() {
        List<CategoriaResponse> categorias = categoriaService.listarCategorias();
        return ResponseEntity.ok(categorias);
    }

    @PostMapping
    @Operation(summary = "criação de categorias",description = "Cria categorias ")
    public ResponseEntity<CategoriaResponse> criarCategoria(@RequestBody @Valid CategoriaRequest request) {
        CategoriaResponse categoria = categoriaService.criarCategoria(request);
        return ResponseEntity.ok(categoria);
    }

    @GetMapping("/{id}/livros")
    @Operation(summary = "Busca livros por categoria",description = "Busca livro por categoria passando o id")
    public ResponseEntity<List<LivroResponse>> listarLivrosPorCategoria(@PathVariable Long id) {
        List<LivroResponse> livros = categoriaService.listarLivrosPorCategoria(id);
        return ResponseEntity.ok(livros);
    }
}