package com.example.todoWebApplication.service;

import com.example.todoWebApplication.dto.Product;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ScraperService {

    public List<Product> scrapeAmazon(String query) throws IOException {
        String url = "https://www.amazon.com.tr/s?k=" + query;
        Document document = Jsoup.connect(url)
                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36")
                .get();

        List<Product> products = new ArrayList<>();
        Elements items = document.select(".s-result-item");

        for (Element item : items) {
            String asin = item.attr("data-asin");
            if (asin.isEmpty()) continue; // Geçersiz ürün

            String title = item.select(".a-size-base-plus.a-color-base.a-text-normal").text();
            String price = item.select(".a-price-whole").text();
            String image = item.select(".s-image").attr("src");
            String productUrl = "https://www.amazon.com.tr" + item.select("a.a-link-normal").attr("href");

            // Fiyat veya başlık olmayan ürünleri atla
            if (!title.isEmpty() && !price.isEmpty()) {
                products.add(new Product(title, price + " TL", image, productUrl));
            }
        }

        return products;
    }
}
