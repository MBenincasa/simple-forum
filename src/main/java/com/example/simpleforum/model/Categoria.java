package com.example.simpleforum.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "categoria")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_categoria", nullable = false, unique = true)
    private int id;

    @Column(name = "titolo", nullable = false)
    private String titolo;

    @OneToMany(mappedBy = "utente")
    //@JsonBackReference
    @JsonIgnore
    private List<Post> postList = new ArrayList<>();
}
