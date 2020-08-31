package com.example.simpleforum.repository;

import com.example.simpleforum.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepo extends JpaRepository<Post, Integer> {
}
