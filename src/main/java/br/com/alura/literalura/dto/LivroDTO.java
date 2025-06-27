package br.com.alura.literalura.dto;

import java.util.List;
import java.util.Map;

public record LivroDTO(
        Long id,
        String title,
        List<AutorDTO> authors,
        List<String> languages,
        Integer download_count,
        Map<String, String> formats
) {}
