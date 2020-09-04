package com.example.simpleforum.service.interfaces;

import com.example.simpleforum.model.Risposta;

import java.util.List;
import java.util.Optional;

public interface RispostaInterface {
    Risposta creaRisposta(Risposta risposta);

    List<Risposta> findAllAnswer();

    Optional<Risposta> findAnswerById(int id);

    Risposta updateAnswer(Risposta risposta);

    void deleteAnswer(int id);
}
