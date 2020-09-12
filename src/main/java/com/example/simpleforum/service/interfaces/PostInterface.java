package com.example.simpleforum.service.interfaces;

import com.example.simpleforum.model.Categoria;
import com.example.simpleforum.model.Post;

import java.util.List;
import java.util.Optional;

public interface PostInterface {
    Post creaPost(Post post);

    List<Post> findAllPosts();

    List<Post> findByCategoria(Categoria categoria);

    Optional<Post> findPostById(int id);

    Post updatePost(Post post);

    void deletePost(int id);
}
