package com.example.simpleforum.service.interfaces;

import com.example.simpleforum.model.Categoria;

import java.util.List;
import java.util.Optional;

public interface CategoriaInterface {
    Categoria creaCategoria(Categoria categoria);

    List<Categoria> findAllCat();

    Optional<Categoria> findCatById(int id);

    Categoria updateCat(Categoria categoria);

    void deleteCat(int id);
}
