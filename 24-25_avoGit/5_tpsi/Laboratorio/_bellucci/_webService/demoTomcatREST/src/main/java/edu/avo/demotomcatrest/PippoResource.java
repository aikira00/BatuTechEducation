package edu.avo.demotomcatrest;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

@Path("/pippo-world")
public class PippoResource {
    @GET
    @Produces("text/plain")
    public Response hello(@QueryParam("nome") String nome) {
        //return "Benvenuto! " + nome;
        return Response .ok("Altro servizio pippo! " + nome).build();
    }

    @POST
    public Response doPost(@FormParam("nome") String nome){
        return Response .ok("Benvenuto! " + nome).build();
    }
}

