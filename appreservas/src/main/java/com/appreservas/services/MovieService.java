package com.appreservas.services;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MovieService {

    private final RestTemplate restTemplate;

    public MovieService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public String getMoviePlainJSON(String movieId) {
        HttpHeaders headers = new HttpHeaders();

        headers.set("X-RapidAPI-Key", "c3a1fc6601msh79a6cdecd3c2c55p13e6b5jsn853f7aef5e54");
        headers.set("X-RapidAPI-Host", "movie-details1.p.rapidapi.com");


        String url = "https://movie-details1.p.rapidapi.com/imdb_api/movie?id={id}";
        return this.restTemplate.getForObject(url, String.class, movieId);
    }
}