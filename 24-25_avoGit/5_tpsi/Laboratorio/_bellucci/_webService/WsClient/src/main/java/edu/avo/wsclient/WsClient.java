/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package edu.avo.wsclient;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import jakarta.json.JsonValue;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

/**
 *
 * @author palma
 */
public class WsClient {

    public static void main(String[] args) throws IOException, URISyntaxException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        String[] voices = {"University", "Coords of place", "Place of coords", "Exit"};
        JComboBox<String> menu = new JComboBox<>(voices);
        int voice = JOptionPane.showConfirmDialog(null, menu, "Web service selection", JOptionPane.OK_CANCEL_OPTION);
        String result = "";
        HttpRequest request;
        HttpResponse<String> response;
        while (menu.getSelectedIndex() != 3) {
            if (voice == JOptionPane.OK_OPTION) {
                switch (menu.getSelectedIndex()) {
                    case 0 -> {
                        String country = JOptionPane.showInputDialog("Insert country");
                        String key = JOptionPane.showInputDialog("Insert key in the name of university");
                        request = HttpRequest.newBuilder()
                                .GET()
                                .uri(new URI("http://universities.hipolabs.com/search?name=" + key + "&country=" + country))
                                .build();
                        response = client.send(request, HttpResponse.BodyHandlers.ofString());
                        JsonReader rdr = Json.createReader(new ByteArrayInputStream(response.body().getBytes()));
                        JsonArray array = rdr.readArray(); //JsonObject per il reverse: dare lat  e lon e avere il luogo; nome dell'attributo display_name
                        JsonObject obj;
                        result = "";
                        for (JsonValue o : array) {
                            obj = o.asJsonObject();
                            result += obj.getString("name") + "\n";
                        }
                    }
                    case 1 -> {
                        String city = JOptionPane.showInputDialog("Insert city");
                        request = HttpRequest.newBuilder()
                                .GET()
                                .uri(new URI("https://nominatim.openstreetmap.org/search?q=" + city + "&format=json"))
                                .build();
                        response = client.send(request, HttpResponse.BodyHandlers.ofString());
                        JsonReader rdr = Json.createReader(new ByteArrayInputStream(response.body().getBytes()));
                        JsonArray array = rdr.readArray();
                        JsonObject obj;
                        result = "";
                        for (JsonValue o : array) {
                            obj = o.asJsonObject();
                            result += obj.getString("lat") + " " + obj.getString("lon") + "\n";
                        }
                    }
                    case 2 -> {
                        String lat = JOptionPane.showInputDialog("Insert latitude");
                        String lon = JOptionPane.showInputDialog("Insert longitude");
                        request = HttpRequest.newBuilder()
                                .GET()
                                .uri(new URI("https://nominatim.openstreetmap.org/reverse?lat=" + lat + "&lon=" + lon + "&format=json"))
                                .build();
                        response = client.send(request, HttpResponse.BodyHandlers.ofString());
                        JsonReader rdr = Json.createReader(new ByteArrayInputStream(response.body().getBytes()));
                        JsonObject obj = rdr.readObject();
                        result = obj.getString("display_name");
                    }
                    case 3 -> {

                    }
                }
                JOptionPane.showMessageDialog(menu, result, "Info", JOptionPane.INFORMATION_MESSAGE);

            }
            voice = JOptionPane.showConfirmDialog(null, menu, "Web service selection", JOptionPane.OK_CANCEL_OPTION);
        }
        JOptionPane.showMessageDialog(menu, "Shutdown", "Exit", JOptionPane.INFORMATION_MESSAGE);
    }
}
