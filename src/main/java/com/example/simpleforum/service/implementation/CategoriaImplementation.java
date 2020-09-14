package com.example.simpleforum.service.implementation;

import com.example.simpleforum.model.Categoria;
import com.example.simpleforum.repository.CategoriaRepo;
import com.example.simpleforum.service.interfaces.CategoriaInterface;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Slf4j
@AllArgsConstructor
@Service
public class CategoriaImplementation implements CategoriaInterface {

    private final CategoriaRepo categoriaRepo;

    @Override
    public Categoria creaCategoria(Categoria categoria) {
        if (!checkDataCat(categoria)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Errore nella Categoria. Il titolo è vuoto o nullo.");
        }

        log.info("INFO - La categoria: " + categoria.getTitolo() + " è stata appena inserita.");
        return categoriaRepo.save(categoria);
    }

    @Override
    public List<Categoria> findAllCat() {
        log.info("INFO - Sono state stampate tutte le categoria.");
        return categoriaRepo.findAll();
    }

    @Override
    public Optional<Categoria> findCatById(int id) {
        if (!categoriaRepo.findById(id).isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Categoria non trovata.");
        }

        log.info("INFO - Sono state stampate le informazioni rguardo la categoria id: " + id);
        return categoriaRepo.findById(id);
    }

    @Override
    public Categoria updateCat(Categoria categoria) {
        if (!categoriaRepo.findById(categoria.getId()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Errore  con la categoria id: " + categoria.getId());
        }

        categoria.setId(categoria.getId());
        categoria.setTitolo(categoria.getTitolo());

        log.info("INFO - E' stato effettuato un aggiornamento sulla categoria id: " + categoria.getId());
        return categoriaRepo.save(categoria);
    }

    @Override
    public void deleteCat(int id) {
        log.info("INFO - E' stata eliminata la categoria con id: " + id);
        categoriaRepo.deleteById(id);
    }

    private boolean checkDataCat(Categoria categoria) {
        log.info("checkCredential DEBUG => " + categoria.toString());
        return null != categoria.getTitolo() && !categoria.getTitolo().isEmpty();
    }
}
