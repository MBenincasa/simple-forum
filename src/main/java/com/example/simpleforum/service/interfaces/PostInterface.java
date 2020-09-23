package com.example.simpleforum.service.interfaces;

import com.example.simpleforum.model.Categoria;
import com.example.simpleforum.model.Post;
import com.example.simpleforum.model.Utente;

import java.util.List;
import java.util.Optional;

public interface PostInterface {
    Post creaPost(Post post);

    List<Post> findAllPosts();

    List<Post> findByCategoria(Categoria categoria);

    List<Post> findByUtente(Utente utente);

    Optional<Post> findPostById(int id);

    Post updatePost(Post post);

    void deletePost(int id);
}
