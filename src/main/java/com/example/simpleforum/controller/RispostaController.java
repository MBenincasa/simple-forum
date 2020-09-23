package com.example.simpleforum.controller;

import com.example.simpleforum.model.Post;
import com.example.simpleforum.model.Risposta;
import com.example.simpleforum.model.Utente;
import com.example.simpleforum.service.interfaces.RispostaInterface;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/risposta")
@CrossOrigin(origins = "http://localhost:4200")
public class RispostaController {
    private RispostaInterface rispostaInterface;

    @PostMapping("/crea")
    public ResponseEntity<Risposta> creaRisposta(@RequestBody Risposta risposta) {
        return ResponseEntity.ok(rispostaInterface.creaRisposta(risposta));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Risposta>> stampaTutteRisposte() {
        return ResponseEntity.ok(rispostaInterface.findAllAnswer());
    }

    @PostMapping("/getByPost")
    public ResponseEntity<List<Risposta>> stampaTutteRispostePost(@RequestBody Post post) {
        return ResponseEntity.ok(rispostaInterface.findByPost(post));
    }

    @PostMapping("/getByUser")
    public ResponseEntity<List<Risposta>> stampaTutteRisposteUtente(@RequestBody Utente utente){
        return ResponseEntity.ok(rispostaInterface.findByUtente(utente));
    }

    @GetMapping("/get-{id}")
    public ResponseEntity<Risposta> stampaRispostaPerId(@PathVariable Integer id) {
        Optional<Risposta> risposta = rispostaInterface.findAnswerById(id);

        if (!risposta.isPresent()) {
            ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(risposta.get());
    }

    @PutMapping("/update")
    public ResponseEntity<Risposta> aggiornaRisposta(@RequestBody Risposta risposta) {
        if (!rispostaInterface.findAnswerById(risposta.getId()).isPresent()) {
            ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(rispostaInterface.updateAnswer(risposta));
    }

    @DeleteMapping("/delete-{id}")
    public ResponseEntity eliminaCommento(@PathVariable Integer id) {
        if (!rispostaInterface.findAnswerById(id).isPresent()) {
            ResponseEntity.badRequest().build();
        }

        rispostaInterface.deleteAnswer(id);
        return ResponseEntity.ok().build();
    }
}
