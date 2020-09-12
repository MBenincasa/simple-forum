package com.example.simpleforum.controller;

import com.example.simpleforum.model.Categoria;
import com.example.simpleforum.model.Post;
import com.example.simpleforum.service.interfaces.PostInterface;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/post")
@CrossOrigin(origins = "http://localhost:4200")
public class PostController {

    private PostInterface postInterface;

    @PostMapping("/crea")
    public ResponseEntity<Post> creaPost(@RequestBody Post post) {
        return ResponseEntity.ok(postInterface.creaPost(post));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Post>> stampaTuttiPost() {
        return ResponseEntity.ok(postInterface.findAllPosts());
    }

    @PostMapping("/getByCat")
    public ResponseEntity<List<Post>> stampaTuttiPostCategoria(@RequestBody Categoria categoria){
        return ResponseEntity.ok(postInterface.findByCategoria(categoria));
    }

    @GetMapping("/get-{id}")
    public ResponseEntity<Post> stampaPostPerId(@PathVariable Integer id) {
        Optional<Post> post = postInterface.findPostById(id);

        if (!post.isPresent()) {
            ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(post.get());
    }

    @PutMapping("/update")
    public ResponseEntity<Post> aggiornaPost(@RequestBody Post post) {
        if (!postInterface.findPostById(post.getId()).isPresent()) {
            ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(postInterface.updatePost(post));
    }

    @DeleteMapping("/delete-{id}")
    public ResponseEntity eliminaPost(@PathVariable Integer id) {
        if (!postInterface.findPostById(id).isPresent()) {
            ResponseEntity.badRequest().build();
        }

        postInterface.deletePost(id);
        return ResponseEntity.ok().build();
    }
}
