package edu.avo.eserciziowsnumstr;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.List;

@Path("/") //oppure qui calcola e altra classe per conta
public class HelloResource {
    @GET
    @Produces("text/plain")
    public String hello() {
        return "Hello, World!";
    }

    @GET
    @Path("calcola/add/{numeroa}/{numerob}")
    public Response add(@PathParam("numeroa") int numeroa, @PathParam("numerob") int numerob) {
        String res = numeroa + " + " + numerob + " = " + Integer.toString(numeroa+numerob);

       return Response.ok(res).build();
    }

    @GET
    @Path("calcola/sub/{numeroa}/{numerob}")
    public Response sub(@PathParam("numeroa") int numeroa, @PathParam("numerob") int numerob) {
        String res = numeroa + " - " + numerob + " = " + Integer.toString(numeroa-numerob);

        return Response.ok(res).build();
    }

    /**
     * Metodo per contare da 1 ad a
     * Esempio: /conta/5 -> "1 2 3 4 5"
     */
    @GET
    @Path("conta/{a}")
    @Produces("text/plain")
    public Response conta(@PathParam("a") String a) {

        try {
            // Conversione da String a int con gestione dell'eccezione
            int numero = Integer.parseInt(a);

            // Verifica che il numero sia maggiore o uguale a 1
            if (numero < 1) {
                return Response.ok("Errore").build();
            }

            // Creazione della lista di numeri da 1 ad a
            List<Integer> numeri = new ArrayList<>();
            for (int i = 1; i <= numero; i++) {
                numeri.add(i);
            }

            // Conversione della lista in stringa con spazi
            StringBuilder risultato = new StringBuilder();
            for (int i = 0; i < numeri.size(); i++) {
                risultato.append(numeri.get(i));
                if (i < numeri.size() - 1) {
                    risultato.append(" ");
                }
            }

            return Response.ok(risultato.toString()).build();

        } catch (NumberFormatException e) {
            // Gestione errore se il parametro non è un numero valido
            return Response.ok("Errore").build();
        }
    }
}