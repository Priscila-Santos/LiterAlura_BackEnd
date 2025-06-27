package br.com.alura.literalura.dto.response;

import br.com.alura.literalura.dto.LivroDTO;

import java.util.List;

public record GutendexResponseDTO(
        Integer count,
        List<LivroDTO> results
) {}

