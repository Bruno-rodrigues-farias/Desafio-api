package com.desafio.desafio.dtos.Livro;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LivroResponse {

    private Long id;
    private String titulo;
    private String isbn;
    private Integer anoPublicacao;
    private BigDecimal preco;
    private String nomeAutor;
    private String nomeCategoria;
}