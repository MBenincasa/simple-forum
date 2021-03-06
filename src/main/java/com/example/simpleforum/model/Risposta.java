package com.example.simpleforum.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "risposta")
public class Risposta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_risposta", nullable = false, unique = true)
    private int id;

    @Column(name = "testo", nullable = false)
    private String testo;

    @Column(name = "data", nullable = false)
    private LocalDateTime data;

    @ManyToOne
    @JoinColumn(name = "id_utente")
    //@JsonManagedReference
    private Utente utente;

    @ManyToOne
    @JoinColumn(name = "id_post")
    //@JsonManagedReference
    private Post post;
}
