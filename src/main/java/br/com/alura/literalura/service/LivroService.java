package br.com.alura.literalura.service;

import br.com.alura.literalura.dto.AutorDTO;
import br.com.alura.literalura.dto.LivroDTO;
import br.com.alura.literalura.model.Autor;
import br.com.alura.literalura.model.Livro;
import br.com.alura.literalura.repository.AutorRepository;
import br.com.alura.literalura.repository.LivroRepository;
import br.com.alura.literalura.util.ResumoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Service
public class LivroService {

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private AutorRepository autorRepository;

    public Livro salvarLivroFavorito(LivroDTO dto) {
        if (livroRepository.existsById(dto.id())) {
            return livroRepository.findById(dto.id()).get(); // já salvo
        }

        Livro livro = new Livro();
        livro.setId(dto.id());
        livro.setTitulo(dto.title());
        livro.setIdioma(dto.languages().isEmpty() ? "desconhecido" : dto.languages().get(0));
        livro.setDownloads(dto.download_count());

        // Buscar e setar o resumo
        String txtUrl = buscarUrlTexto(dto.formats());
        if (txtUrl != null) {
            String resumo = ResumoUtil.extrairResumo(txtUrl);
            livro.setResumo(resumo);
        } else {
            livro.setResumo("Resumo não disponível.");
        }

        Set<Autor> autores = new HashSet<>();
        for (AutorDTO autorDTO : dto.authors()) {
            Autor autor = autorRepository.findByNome(autorDTO.name())
                    .orElseGet(() -> {
                        Autor novo = new Autor();
                        novo.setNome(autorDTO.name());
                        novo.setAnoNascimento(autorDTO.birth_year());
                        novo.setAnoFalecimento(autorDTO.death_year());
                        return autorRepository.save(novo);
                    });
            autores.add(autor);
        }

        livro.setAutores(autores);
        return livroRepository.save(livro);
    }

    private String buscarUrlTexto(Map<String, String> formats) {
        if (formats.containsKey("text/plain; charset=utf-8"))
            return formats.get("text/plain; charset=utf-8");

        for (String key : formats.keySet()) {
            if (key.startsWith("text/plain"))
                return formats.get(key);
        }

        return null;
    }

}
