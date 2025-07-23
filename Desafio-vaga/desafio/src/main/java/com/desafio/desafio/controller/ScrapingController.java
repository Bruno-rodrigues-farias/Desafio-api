package com.desafio.desafio.controller;

import com.desafio.desafio.model.Livro;
import com.desafio.desafio.service.ScrapingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/livros")
public class ScrapingController {

    @Autowired
    private ScrapingService scrapingService;

    @PostMapping("/importar")
    public ResponseEntity<Livro> importarLivro(@RequestBody ImportarLivroRequest request) {
        Livro livro = scrapingService.buscarLivro(request.getUrl());
    
        return ResponseEntity.ok(livro);
    }

    public static class ImportarLivroRequest {
        private String url;
        private Long autorId;
        private Long categoriaId;

        public String getUrl() {
            return url;
        }
        public void setUrl(String url) {
            this.url = url;
        }
        public Long getAutorId() {
            return autorId;
        }
        public void setAutorId(Long autorId) {
            this.autorId = autorId;
        }
        public Long getCategoriaId() {
            return categoriaId;
        }
        public void setCategoriaId(Long categoriaId) {
            this.categoriaId = categoriaId;
        }
    }
}
