package com.desafio.desafio.dtos.Categoria;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;

@Data
public class CategoriaRequest {
    @NotBlank(message = "Nome é obrigatório")
    private String nome;
}
