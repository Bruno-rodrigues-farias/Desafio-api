package com.desafio.desafio.dtos.Categoria;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CategoriaResponse {
    private Long id;
    private String nome;
}
