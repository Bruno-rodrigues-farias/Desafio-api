package com.desafio.desafio.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Categoria {

    @Id
    private Long id;

    private String nome;

    private String descricao;


    @OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL)
    private List<Livro> livros = new ArrayList<>();

}
