/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.avo.addapp;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.net.HttpURLConnection;

/**
 *
 * @author palma
 */
@OpenAPIDefinition(
        info = @Info(
                title = "Parte di un'applicazione distribuita",
                version = "1.0.0",
                description = """
                      Questo servizio rihciede un'autenticazione che va effettuata presso un altro servizio.
                      L'autenticazione è di tipo Bearer utilizzando un token.
                      Le caratteristiche di questo sercizio (url, nome parametri etc. etc.) sono reperite presso un terzo servizio.
                      Questo servizio utilizza una possibilità offerta da Jakarta di avere una gestione
                      centralizzata delle eccezioni tramite la classe ExceptionManager che implementa una specifica interfaccia: ExceptionMapper<Throwable>
                             """,
                contact = @Contact(name = "Giuliano Bellucci", email = "gbellucci@itisavogadro.it")
        )
)
@Path("/")
public class AddService {

    @GET
    @Operation(
            summary = "Addizione fra due numeri",
            description = "L'addizione di due numeri passati sulla url"
    )
    @ApiResponses({
        @ApiResponse(
                responseCode = "200",
                description = "Restituisce la somma dei due numeri",
                content = @Content(mediaType = "application/json")
        ),
        @ApiResponse(
                responseCode = "400",
                description = "Parametri della richiesta errati"
        )
    })
    @Produces(MediaType.APPLICATION_JSON)
    public Response addResult(@QueryParam("a") String a, @QueryParam("b") String b, @HeaderParam("Authorization") String token) throws NumberFormatException {
        Response response = Response.status(Response.Status.ACCEPTED).build();
        String urlString = "http://localhost:8080/Auth/api";
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(urlString);
        response = target.request(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + token.substring(7))
                .get();
        int responseCode = response.getStatus();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            String op;
            response = Response.status(Response.Status.BAD_REQUEST).build();
            if (a != null && a.matches("-?\\d+")) {
                int p1 = Integer.parseInt(a);
                if (b != null && b.matches("-?\\d+")) {
                    int p2 = Integer.parseInt(b);
                    op = "" + (p1 + p2);
                    response = Response.ok("{\"result\":" + op + "}").build();
                }
            }
        }
        return response;
    }

}
