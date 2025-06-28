package br.com.alura.literalura.util;

import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.stream.Collectors;


public class ResumoUtil {

    public static String extrairResumo(String url) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            String texto = restTemplate.getForObject(url, String.class);

            if (texto == null || texto.isBlank()) return "Resumo não disponível.";

            int startIndex = texto.indexOf("*** START OF THIS PROJECT GUTENBERG EBOOK");
            if (startIndex != -1) {
                texto = texto.substring(startIndex + 50);
            }

            String[] paragrafos = texto.split("\\r?\\n\\r?\\n");
            return Arrays.stream(paragrafos)
                    .map(String::trim)
                    .filter(p -> p.length() > 150)
                    .limit(3)
                    .collect(Collectors.joining("\n\n"));
        } catch (Exception e) {
            return "Resumo não disponível.";
        }
    }
}

