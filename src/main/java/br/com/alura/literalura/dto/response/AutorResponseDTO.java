package br.com.alura.literalura.dto.response;


import br.com.alura.literalura.model.Autor;

public record AutorResponseDTO(
        Long id,
        String nome,
        Integer anoNascimento,
        Integer anoFalecimento
) {
    public static AutorResponseDTO fromEntity(Autor autor) {
        return new AutorResponseDTO(
                autor.getId(),
                autor.getNome(),
                autor.getAnoNascimento(),
                autor.getAnoFalecimento()
        );
    }
}

