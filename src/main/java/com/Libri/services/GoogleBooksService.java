package com.Libri.services;

import com.Libri.dtos.GoogleBookDto;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class GoogleBooksService {

    private final String GOOGLE_BOOKS_API_URL = "https://www.googleapis.com/books/v1/volumes";

    public List<GoogleBookDto> searchBooks(String query) {
        RestTemplate restTemplate = new RestTemplate();

        String uri = UriComponentsBuilder
                .fromHttpUrl(GOOGLE_BOOKS_API_URL)
                .queryParam("q", query)
                .queryParam("maxResults", 10)
                .toUriString();

        Map<String, Object> response = restTemplate.getForObject(uri, Map.class);

        List<GoogleBookDto> books = new ArrayList<>();

        if (response != null && response.containsKey("items")) {
            List<Map<String, Object>> items = (List<Map<String, Object>>) response.get("items");

            for (Map<String, Object> item : items) {

                String id = (String) item.get("id");

                Map<String, Object> volumeInfo = (Map<String, Object>) item.get("volumeInfo");

                String title = (String) volumeInfo.getOrDefault("title", "Sem título");
                List<String> authorsList = (List<String>) volumeInfo.get("authors");
                String authors = (authorsList != null) ? String.join(", ", authorsList) : "Desconhecido";
                String description = (String) volumeInfo.getOrDefault("description", "Sem descrição");

                books.add(new GoogleBookDto(id, title, description, authors));
            }
        }

        return books;
    }
    public GoogleBookDto getBookById(String id) {
        RestTemplate restTemplate = new RestTemplate();

        String url = "https://www.googleapis.com/books/v1/volumes/" + id;

        Map<String, Object> response = restTemplate.getForObject(url, Map.class);

        if (response != null) {
            Map<String, Object> volumeInfo = (Map<String, Object>) response.get("volumeInfo");

            String title = (String) volumeInfo.getOrDefault("title", "Sem título");
            List<String> authorsList = (List<String>) volumeInfo.get("authors");
            String authors = (authorsList != null) ? String.join(", ", authorsList) : "Desconhecido";
            String description = (String) volumeInfo.getOrDefault("description", "Sem descrição");

            return new GoogleBookDto(id, title, description, authors);
        }

        return null;
    }

}

