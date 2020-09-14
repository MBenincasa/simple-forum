package com.example.simpleforum.service.implementation;

import com.example.simpleforum.model.Post;
import com.example.simpleforum.model.Risposta;
import com.example.simpleforum.model.Utente;
import com.example.simpleforum.repository.PostRepo;
import com.example.simpleforum.repository.RispostaRepo;
import com.example.simpleforum.repository.UtenteRepo;
import com.example.simpleforum.service.interfaces.RispostaInterface;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class RispostaImplementation implements RispostaInterface {

    private final RispostaRepo rispostaRepo;
    private final PostRepo postRepo;
    private final UtenteRepo utenteRepo;

    @Override
    public Risposta creaRisposta(Risposta risposta) {
        if (!checkRispostaData(risposta)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Errore nei dati della risposta.");
        }

        log.info("INFO - E' stato creata una nuova risposta sotto il post id: " + risposta.getPost().getId());
        return rispostaRepo.save(risposta);
    }

    @Override
    public List<Risposta> findAllAnswer() {
        log.info("INFO - Sono stampate tutte le risposte");
        return rispostaRepo.findAll();
    }

    @Override
    public Optional<Risposta> findAnswerById(int id) {
        if (!rispostaRepo.findById(id).isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Errore nella richiesta del post id: " + id);
        }

        log.info("INFO - Sono state stampate le info della risposta id: " + id);
        return rispostaRepo.findById(id);
    }

    @Override
    public Risposta updateAnswer(Risposta risposta) {
        if (!rispostaRepo.findById(risposta.getId()).isPresent() || !checkRispostaData(risposta)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Risposta non trovata oppure errore nei dati della risposta");
        }

        risposta.setTesto(risposta.getTesto());

        log.info("INFO - Aggiornata la risposta id: " + risposta.getId());
        return rispostaRepo.save(risposta);
    }

    @Override
    public void deleteAnswer(int id) {
        if (!rispostaRepo.findById(id).isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Errore nel recuperare la risposta id: " + id);
        }

        log.info("INFO - E' stata eliminata la risposta id: " + id);
        rispostaRepo.deleteById(id);
    }

    private boolean checkRispostaData(Risposta risposta) {
        log.info("checkCredential DEBUG => " + risposta.toString());
        if (null == risposta.getTesto() || risposta.getTesto().isEmpty()) {
            return false;
        }

        Optional<Utente> utente = utenteRepo.findById(risposta.getUtente().getId());
        Optional<Post> post = postRepo.findById(risposta.getPost().getId());

        return utente.isPresent() && post.isPresent();
    }
}
