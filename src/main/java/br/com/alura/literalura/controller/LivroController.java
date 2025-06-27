package br.com.alura.literalura.controller;

import br.com.alura.literalura.dto.response.GutendexResponseDTO;
import br.com.alura.literalura.service.GutendexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/livros")
public class LivroController {

    @Autowired
    private GutendexService gutendexService;

    @GetMapping("/titulo")
    public GutendexResponseDTO buscarPorTitulo(@RequestParam String titulo) {
        return gutendexService.buscarPorTitulo(titulo);
    }

    @GetMapping("/autor")
    public GutendexResponseDTO buscarPorAutor(@RequestParam String autor) {
        return gutendexService.buscarPorAutor(autor);
    }

    @GetMapping("/idioma")
    public GutendexResponseDTO buscarPorIdioma(@RequestParam String idioma) {
        return gutendexService.buscarPorIdioma(idioma);
    }
}
