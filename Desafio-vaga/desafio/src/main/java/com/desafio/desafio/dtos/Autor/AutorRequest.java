package com.desafio.desafio.dtos.Autor;

import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.Data;

@Data
public class AutorRequest {
    private Long id;

    @NotBlank
    private String nome;

    @Email
    @NotBlank
    private String email;

    @Past
    @NotNull
    private LocalDate dataNascimento;
}
