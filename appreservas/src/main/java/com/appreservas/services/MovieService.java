package com.appreservas.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.RestTemplate;

public class MovieService{

    private final RestTemplate restTemplate;

    @Autowired
    public MovieService() {
        this.restTemplate = new RestTemplate();
    }

    public String getMoviePlainJSON(String movieId) {
        HttpHeaders headers = new HttpHeaders();

        headers.set("X-RapidAPI-Key", "c3a1fc6601msh79a6cdecd3c2c55p13e6b5jsn853f7aef5e54");
        headers.set("X-RapidAPI-Host", "movie-details1.p.rapidapi.com");

        HttpEntity<String> entity = new HttpEntity<>(headers);

        String url = "https://movie-details1.p.rapidapi.com/imdb_api/movie?id=" + movieId;
        try{
            var response = this.restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
            if(response.getStatusCode() == HttpStatus.OK) {
                return response.getBody();
            } else {
                return null;
            }
        }catch(Exception e){
            return null;
        }
    }
}