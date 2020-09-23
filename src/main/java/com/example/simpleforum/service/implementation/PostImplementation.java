package com.example.simpleforum.service.implementation;

import com.example.simpleforum.model.Categoria;
import com.example.simpleforum.model.Post;
import com.example.simpleforum.model.Utente;
import com.example.simpleforum.repository.CategoriaRepo;
import com.example.simpleforum.repository.PostRepo;
import com.example.simpleforum.repository.UtenteRepo;
import com.example.simpleforum.service.interfaces.PostInterface;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class PostImplementation implements PostInterface {

    private final PostRepo postRepo;
    private final UtenteRepo utenteRepo;
    private final CategoriaRepo categoriaRepo;

    @Override
    public Post creaPost(Post post) {
        if (!checkDataPost(post)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Errore nei dati del post.");
        }

        log.info("INFO - E' stato creato un nuovo post titolo: " + post.getTitolo());
        return postRepo.save(post);
    }

    @Override
    public List<Post> findAllPosts() {
        log.info("INFO - Sono stati stampati tutti i post");
        return postRepo.findAll();
    }

    @Override
    public List<Post> findByCategoria(Categoria categoria) {
        log.info("INFO - Sono stati stampati tutti i post della categoria: " + categoria.getTitolo());
        return postRepo.findByCategoria(categoria);
    }

    @Override
    public List<Post> findByUtente(Utente utente) {
        log.info("INFO - Sono stati stampati tutti i post dell'utente: " + utente.getEmail());
        return postRepo.findByUtente(utente);
    }

    @Override
    public Optional<Post> findPostById(int id) {
        if (!postRepo.findById(id).isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Errore nella richiesta del post id: " + id);
        }

        log.info("INFO - Sono state stampate le info riguardanti il post id: " + id);
        return postRepo.findById(id);
    }

    @Override
    public Post updatePost(Post post) {
        if (!postRepo.findById(post.getId()).isPresent() || !checkDataPost(post)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Post non trovato o i dati non sono corretti");
        }

        post.setId(post.getId());
        post.setTitolo(post.getTitolo());
        post.setTesto(post.getTesto());

        log.info("INFO - E' stato effettuato un aggiornamento sul posto id: " + post.getId());
        return postRepo.save(post);
    }

    @Override
    public void deletePost(int id) {
        if (!postRepo.findById(id).isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Non è stato trovato un post id" + id);
        }

        log.info("INFO - E' stato eliminato il post id: " + id);
        postRepo.deleteById(id);
    }

    private boolean checkDataPost(Post post) {
        log.info("checkCredential DEBUG => " + post.toString());
        if (null == post.getTitolo() || post.getTitolo().isEmpty() || null == post.getTesto() || post.getTesto().isEmpty() || null == post.getData() || post.getData().toString().isEmpty()) {
            return false;
        }

        Optional<Utente> utente = utenteRepo.findById(post.getUtente().getId());
        Optional<Categoria> categoria = categoriaRepo.findById(post.getCategoria().getId());

        return utente.isPresent() && categoria.isPresent();
    }
}
