package com.desafio.desafio.dtos.Autor;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AutorResponse {

    private Long id;
    private String nome;
    private String email;
    private LocalDate dataNascimento;
}
