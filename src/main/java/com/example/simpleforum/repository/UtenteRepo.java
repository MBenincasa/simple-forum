package com.example.simpleforum.repository;

import com.example.simpleforum.model.Utente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UtenteRepo extends JpaRepository<Utente, Integer> {
}
