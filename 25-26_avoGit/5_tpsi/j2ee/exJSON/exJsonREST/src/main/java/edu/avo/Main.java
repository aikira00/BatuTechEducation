package edu.avo;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
    // 1. Crea HttpClient (può essere riutilizzato)
        HttpClient httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .connectTimeout(Duration.ofSeconds(5))
                .build();

    // 2. Costruisci richiesta
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://jsonplaceholder.typicode.com/users/1"))
                .GET()
                .header("Accept", "application/json")
                .timeout(Duration.ofSeconds(5))
                .build();

    // 3. Esegui richiesta (sincrono)
        HttpResponse<String> response = httpClient.send(
                request,
                HttpResponse.BodyHandlers.ofString()
        );

    // 4. Parse JSON
        JSONObject json = new JSONObject(response.body());
        System.out.println(json.optJSONObject("address").optString("street"));
        System.out.println(json.getString("name"));


        request = HttpRequest.newBuilder()
                .uri(URI.create("https://jsonplaceholder.typicode.com/users"))
                .GET()
                .header("Accept", "application/json")
                .timeout(Duration.ofSeconds(5))
                .build();

        // 3. Esegui richiesta (sincrono)
        response = httpClient.send(
                request,
                HttpResponse.BodyHandlers.ofString()
        );

        JSONArray jsonArray = new JSONArray(response.body());
        for (int i = 0; i < 2; i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            System.out.println(jsonObject);
            System.out.println(jsonObject.optJSONObject("address").optString("street"));
            System.out.println(jsonObject.getString("name"));
        }
    }
}