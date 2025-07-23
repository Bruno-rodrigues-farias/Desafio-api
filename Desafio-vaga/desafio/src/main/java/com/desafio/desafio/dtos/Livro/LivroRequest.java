package com.desafio.desafio.dtos.Livro;

import java.math.BigDecimal;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class LivroRequest {

    @NotBlank(message = "Título do livro não pode ser vazio")
    private String titulo;

    @NotBlank
    @Pattern(regexp = "\\d{10}|\\d{13}", message = "ISBN deve ter 10 ou 13 dígitos")
    private String isbn;

    @NotNull
    @Max(value = 2025, message = "Ano de publicação não pode ser no futuro")
    private Integer anoPublicacao;

    @NotNull
    @Positive(message = "Preço deve ser positivo")
    private BigDecimal preco;

    @NotNull
    @NotNull(message = "ID do autor é obrigatório")
    private Long autorId;

    @NotNull
    @NotNull(message = "ID da categoria é obrigatório")
    private Long categoriaId;
}