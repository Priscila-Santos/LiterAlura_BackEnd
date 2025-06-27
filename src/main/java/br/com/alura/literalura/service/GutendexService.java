package br.com.alura.literalura.service;

import br.com.alura.literalura.dto.response.GutendexResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GutendexService {

    private final String BASE_URL = "https://gutendex.com/books";

    @Autowired
    private RestTemplate restTemplate;

    public GutendexResponseDTO buscarPorTitulo(String titulo) {
        String url = BASE_URL + "?search=" + titulo;
        return restTemplate.getForObject(url, GutendexResponseDTO.class);
    }

    public GutendexResponseDTO buscarPorAutor(String autor) {
        String url = BASE_URL + "?search=" + autor;
        return restTemplate.getForObject(url, GutendexResponseDTO.class);
    }

    public GutendexResponseDTO buscarPorIdioma(String idioma) {
        String url = BASE_URL + "?languages=" + idioma;
        return restTemplate.getForObject(url, GutendexResponseDTO.class);
    }
}
