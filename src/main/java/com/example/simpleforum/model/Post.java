package com.example.simpleforum.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_post", nullable = false, unique = true)
    private int id;

    @Column(name = "titolo", nullable = false)
    private String titolo;

    @Column(name = "testo", nullable = false)
    private String testo;

    @Column(name = "data", nullable = false)
    private LocalDateTime data;

    @ManyToOne
    @JoinColumn(name = "id_utente")
    //@JsonManagedReference
    private Utente utente;

    @ManyToOne
    @JoinColumn(name = "id_categoria")
    //@JsonManagedReference
    private Categoria categoria;

    @OneToMany(mappedBy = "post")
    //@JsonBackReference
    @JsonIgnore
    private List<Risposta> rispostaList = new ArrayList<>();
}
