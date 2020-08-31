package com.example.simpleforum.repository;

import com.example.simpleforum.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepo extends JpaRepository<Categoria, Integer> {
}
