package com.example.simpleforum.controller;

import com.example.simpleforum.model.Utente;
import com.example.simpleforum.service.interfaces.UtenteInterface;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping(value = "/utente")
@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class UtenteController {
    private UtenteInterface utenteInterface;

    @PostMapping("/login")
    public ResponseEntity<Utente> login(@RequestBody Utente utente) {
        return ResponseEntity.ok(utenteInterface.login(utente));
    }

    @PostMapping("/registrazione")
    public ResponseEntity<Utente> registrazione(@RequestBody Utente utente) {
        return ResponseEntity.ok((utenteInterface.registrazione(utente)));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Utente>> stampaTuttiUtenti() {
        return ResponseEntity.ok(utenteInterface.findAllUsers());
    }

    @GetMapping("/get-{id}")
    public ResponseEntity<Utente> stampaUtentePerId(@PathVariable Integer id) {
        Optional<Utente> utente = utenteInterface.findById(id);

        if (!utente.isPresent()) {
            ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(utente.get());
    }

    @PutMapping("/update")
    public ResponseEntity<Utente> aggiornaUtente(@RequestBody Utente utente) {
        if (!utenteInterface.findById(utente.getId()).isPresent()) {
            ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(utenteInterface.updateUser(utente));
    }

    @DeleteMapping("/delete-{id}")
    public ResponseEntity eliminaUtente(@PathVariable Integer id) {
        if (!utenteInterface.findById(id).isPresent()) {
            ResponseEntity.badRequest().build();
        }

        utenteInterface.deleteUser(id);
        return ResponseEntity.ok().build();
    }
}
