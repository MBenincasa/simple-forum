package com.example.simpleforum.controller;

import com.example.simpleforum.model.Categoria;
import com.example.simpleforum.service.interfaces.CategoriaInterface;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/categoria")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class CategoriaController {
    private CategoriaInterface categoriaInterface;

    @PostMapping("/crea")
    public ResponseEntity<Categoria> creaCategoria(@RequestBody Categoria categoria){
        return ResponseEntity.ok(categoriaInterface.creaCategoria(categoria));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Categoria>> stampaTutteCategorie(){
        return ResponseEntity.ok(categoriaInterface.findAllCat());
    }

    @GetMapping("/get-{id}")
    public ResponseEntity<Categoria> stampaCategoriaPerId(@PathVariable Integer id){
        Optional<Categoria> categoria = categoriaInterface.findCatById(id);

        if(!categoria.isPresent()){
            ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(categoria.get());
    }

    @PutMapping("/update")
    public ResponseEntity<Categoria> aggiornaCategoria(@RequestBody Categoria categoria){
        if(!categoriaInterface.findCatById(categoria.getId()).isPresent()){
            ResponseEntity.badRequest().build();
        }

        return  ResponseEntity.ok(categoriaInterface.updateCat(categoria));
    }

    @DeleteMapping("delete-{id}")
    public ResponseEntity eliminaCategoria(@PathVariable Integer id){
        Optional<Categoria> categoria = categoriaInterface.findCatById(id);

        if(!categoria.isPresent()){
            ResponseEntity.badRequest().build();
        }

        categoriaInterface.deleteCat(id);
        return ResponseEntity.ok().build();
    }
}
