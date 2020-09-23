package com.example.simpleforum.repository;

import com.example.simpleforum.model.Categoria;
import com.example.simpleforum.model.Post;
import com.example.simpleforum.model.Utente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepo extends JpaRepository<Post, Integer> {
    List<Post> findByCategoria(Categoria categoria);
    List<Post> findByUtente(Utente utente);
}
