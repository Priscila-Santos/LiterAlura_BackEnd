package br.com.alura.literalura.dto.response;

import br.com.alura.literalura.model.Livro;

import java.util.List;
import java.util.Map;

public record LivroResponseDTO(
        Long id,
        String titulo,
        String idioma,
        Integer downloads,
        String resumo,
        List<AutorResponseDTO> autores,
        Map<String, String> formats

) {
    public static LivroResponseDTO fromEntity(Livro livro) {
        return new LivroResponseDTO(
                livro.getId(),
                livro.getTitulo(),
                livro.getIdioma(),
                livro.getDownloads(),
                livro.getResumo(),
                livro.getAutores().stream().map(AutorResponseDTO::fromEntity).toList(),
                livro.getFormats()

        );
    }
}
