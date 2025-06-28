package br.com.alura.literalura.controller;

import br.com.alura.literalura.dto.LivroDTO;
import br.com.alura.literalura.dto.response.AutorResponseDTO;
import br.com.alura.literalura.dto.response.LivroResponseDTO;
import br.com.alura.literalura.model.Livro;
import br.com.alura.literalura.repository.AutorRepository;
import br.com.alura.literalura.repository.LivroRepository;
import br.com.alura.literalura.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/favoritos")
public class FavoritoController {

    @Autowired
    private LivroService livroService;

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private AutorRepository autorRepository;

    @PostMapping("/livro")
    public Livro adicionarLivro(@RequestBody LivroDTO dto) {
        return livroService.salvarLivroFavorito(dto);
    }

    @GetMapping("/livros")
    public List<LivroResponseDTO> listarLivrosFavoritos() {
        return livroRepository.findAll().stream()
                .map(LivroResponseDTO::fromEntity)
                .toList();
    }

    @GetMapping("/autores")
    public List<AutorResponseDTO> listarAutoresFavoritos() {
        return autorRepository.findAll().stream()
                .map(AutorResponseDTO::fromEntity)
                .toList();
    }

    @DeleteMapping("/livro/{id}")
    public ResponseEntity<Void> removerLivroFavorito(@PathVariable Long id) {
        if (livroRepository.existsById(id)) {
            livroRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/autor/{id}")
    public ResponseEntity<Void> removerAutorFavorito(@PathVariable Long id) {
        if (autorRepository.existsById(id)) {
            autorRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}
