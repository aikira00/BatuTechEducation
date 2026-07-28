/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.avo.listapp;

import edu.avo.opservice.OpService;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.net.HttpURLConnection;
import java.util.List;

/**
 *
 * @author palma
 */
@Path("/operations")
public class ListService {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listResult(@HeaderParam("Authorization") String token) {
        String urlString = "http://localhost:8080/Auth/api";
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(urlString);
        Response response = target.request(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + token.substring(7))
                .get();
        int responseCode = response.getStatus();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            List<OpService> list = List.of(new OpService("Add", "http://localhost:9090/Add/api", "get", "a", "b", '+'),
                    new OpService("Sub", "http://localhost:9092/Sub/api", "get", "a", "b", '-'),
                    new OpService("Mul", "http://localhost:9094/Mul/api", "get", "a", "b", '*'),
                    new OpService("Div", "http://localhost:9096/Div/api", "get", "a", "b", '/'));
            response = Response.ok(list).build();

        }
        return response;
    }

}
