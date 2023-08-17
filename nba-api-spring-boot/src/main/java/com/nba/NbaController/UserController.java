package com.nba.NbaController;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@RestController
@RequestMapping("/api")
public class UserController {

    private final String API_BASE_URL = "https://api-nba-v1.p.rapidapi.com";
    private final String API_KEY = "******************************";

    @GetMapping("/seasons")
    public ResponseEntity<String> getNbaSeasons() throws IOException, InterruptedException {

        String apiUrl = API_BASE_URL + "/seasons";
        String response = makeApiCall(apiUrl);
        return ResponseEntity.ok("API success work! " + response);
    }

    // Other methods and endpoints can be added here

    private String makeApiCall(String apiUrl) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiUrl))
                .header("X-RapidAPI-Key", API_KEY)
                .header("X-RapidAPI-Host", "api-nba-v1.p.rapidapi.com")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();

        HttpResponse<String> httpResponse = HttpClient.newHttpClient()
                .send(request, HttpResponse.BodyHandlers.ofString());

        return httpResponse.body();
    }
}
