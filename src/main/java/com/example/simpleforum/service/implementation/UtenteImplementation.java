package com.example.simpleforum.service.implementation;

import com.example.simpleforum.model.Utente;
import com.example.simpleforum.repository.UtenteRepo;
import com.example.simpleforum.service.interfaces.UtenteInterface;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@Slf4j
@AllArgsConstructor
@Service
public class UtenteImplementation implements UtenteInterface {

    private final UtenteRepo utenteRepo;

    @Override
    public Utente login(Utente utente) {
        boolean isReg = false;

        if (checkCredential(utente, isReg)) {
            Utente u = utenteRepo.findByEmailAndPassword(utente.getEmail(), utente.getPassword());

            if (null == u) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Utente: " + utente.getEmail() + " non presente o password errata.");
            }

            log.info("INFO - Utente: " + u.getEmail() + " ha effettuato il login.");
            return u;
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Utente: " + utente.getEmail() + " non presente o password errata.");
        }
    }

    @Override
    public Utente registrazione(Utente utente) {
        boolean isReg = true;

        if (!checkCredential(utente, isReg)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Credenziali non corrette");
        }

        Utente u = utenteRepo.findByEmail(utente.getEmail());

        if (null != u) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Utente: " + u.getEmail() + " già presente.");
        }

        log.info("INFO - Utente: " + utente.getEmail() + " inserito nel DB.");
        return utenteRepo.save(utente);
    }

    @Override
    public List<Utente> findAllUsers() {
        log.info("INFO - Sono stati stampati tutti gli utenti.");
        return utenteRepo.findAll();
    }

    @Override
    public Optional<Utente> findUserById(int id) {
        if (!utenteRepo.findById(id).isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Utente non presente.");
        }

        log.info("INFO - Sono state stampate le informazioni riguardo l'utente id: " + id);
        return utenteRepo.findById(id);
    }

    @Override
    public void deleteUser(int id) {
        log.info("INFO - E' stato eliminato l'utente id: " + id);
        utenteRepo.deleteById(id);
    }

    @Override
    public Utente updateUser(Utente utente) {
        if (!utenteRepo.findById(utente.getId()).isPresent() || !checkCredential(utente, true)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Errore con la categoria id: " + utente.getId());
        }

        utente.setId(utente.getId());
        utente.setEmail(utente.getEmail());
        utente.setPassword(utente.getPassword());
        utente.setCognome(utente.getCognome());
        utente.setNome(utente.getNome());
        utente.setTipo(utente.getTipo());

        log.info("INFO - E' stato eseguito un aggiornamento dei campi all'utente id: " + utente.getId());
        return utenteRepo.save(utente);
    }

    private boolean checkCredential(Utente utente, boolean isReg) {
        String regex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";

        if (null == utente.getPassword() || utente.getPassword().isEmpty()) {
            return false;
        }

        if (isReg) {
            if (null == utente.getCognome() || utente.getCognome().isEmpty() || null == utente.getNome() || utente.getNome().isEmpty() || null == utente.getTipo() || utente.getTipo().isEmpty()) {
                return false;
            }
        }

        return Pattern.matches(regex, utente.getEmail()) && null != utente.getEmail() && !utente.getEmail().isEmpty();
    }
}
