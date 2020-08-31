package com.example.simpleforum.service.interfaces;

import com.example.simpleforum.model.Utente;

import java.util.List;
import java.util.Optional;

public interface UtenteInterface {
    Utente login(Utente utente);

    Utente registrazione(Utente utente);

    List<Utente> findAllUsers();

    Optional<Utente> findById(int id);

    void deleteUser(int id);

    Utente updateUser(Utente utente);
}
