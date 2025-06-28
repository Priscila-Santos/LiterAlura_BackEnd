package br.com.alura.literalura.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.stream.Collectors;

public class ResumoUtil {

    public static String extrairResumo(String url) {
        try {
            String texto = baixarTextoComRedirecionamento(url);
            if (texto == null || texto.isBlank()) return "Resumo não disponível.";

            texto = texto.toLowerCase(); // padroniza para facilitar busca de marcadores

            // Localiza início e fim do conteúdo principal
            int inicio = texto.indexOf("*** start");
            int fim = texto.indexOf("*** end");

            if (inicio != -1 && fim != -1 && fim > inicio) {
                texto = texto.substring(inicio, fim);
            }

            // Quebra por parágrafo e filtra os mais significativos
            String[] paragrafos = texto.split("\\r?\\n\\r?\\n");
            String resumo = Arrays.stream(paragrafos)
                    .map(String::trim)
                    .filter(p -> p.length() > 150 && !p.contains("project gutenberg")) // ignora créditos repetidos
                    .limit(3)
                    .collect(Collectors.joining("\n\n"));

            return resumo.isBlank() ? "Resumo não disponível." : resumo;

        } catch (Exception e) {
            return "Resumo não disponível.";
        }
    }

    private static String baixarTextoComRedirecionamento(String urlString) throws Exception {
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setInstanceFollowRedirects(false);

        int status = conn.getResponseCode();
        if (status == HttpURLConnection.HTTP_MOVED_TEMP || status == HttpURLConnection.HTTP_MOVED_PERM) {
            String novaUrl = conn.getHeaderField("Location");
            return baixarTextoComRedirecionamento(novaUrl);
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
            return reader.lines().collect(Collectors.joining("\n"));
        }
    }
}