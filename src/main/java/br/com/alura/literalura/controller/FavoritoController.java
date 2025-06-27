package br.com.alura.literalura.controller;

import br.com.alura.literalura.dto.LivroDTO;
import br.com.alura.literalura.model.Autor;
import br.com.alura.literalura.model.Livro;
import br.com.alura.literalura.repository.AutorRepository;
import br.com.alura.literalura.repository.LivroRepository;
import br.com.alura.literalura.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Livro> listarLivrosFavoritos() {
        return livroRepository.findAll();
    }

    @GetMapping("/autores")
    public List<Autor> listarAutoresFavoritos() {
        return autorRepository.findAll();
    }
}
