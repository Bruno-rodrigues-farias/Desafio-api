package com.desafio.desafio.service;

import com.desafio.desafio.model.Livro;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ScrapingService {

    public Livro buscarLivro(String url) {
        try {
            Document doc = Jsoup.connect(url).get();

            String titulo = doc.selectFirst("span#productTitle").text();
            String precoTexto = doc.selectFirst("span.a-price span.a-offscreen").text();

            Element anoEl = doc.selectFirst("span:contains(Ano de publicação)");
            int anoPublicacao = 0;
            if (anoEl != null) {
                String anoTexto = anoEl.text().replaceAll("\\D+", "");
                if (!anoTexto.isEmpty()) {
                    anoPublicacao = Integer.parseInt(anoTexto);
                }
            }

            BigDecimal preco = new BigDecimal(precoTexto.replace("R$", "").replace(",", ".").trim());

            Livro livro = new Livro();
            livro.setTitulo(titulo);
            livro.setPreco(preco);
            livro.setAnoPublicacao(anoPublicacao);

            return livro;

        } catch (Exception e) {
            throw new RuntimeException("Erro ao fazer scraping: " + e.getMessage(), e);
        }
    }
}
