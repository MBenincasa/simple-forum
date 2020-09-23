package com.example.simpleforum.repository;

import com.example.simpleforum.model.Post;
import com.example.simpleforum.model.Risposta;
import com.example.simpleforum.model.Utente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RispostaRepo extends JpaRepository<Risposta, Integer> {
    List<Risposta> findByPost(Post post);
    List<Risposta> findByUtente(Utente utente);
}
