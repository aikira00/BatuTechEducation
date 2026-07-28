package edu.avo.demotomcatrest;

import jakarta.ws.rs.*;

import jakarta.ws.rs.core.Response;

@Path("/hello-world")
public class HelloResource {
    @GET
    @Produces("text/plain")
    public Response hello(@QueryParam("nome") String nome) {
        //return "Benvenuto! " + nome;
        return Response .ok("Benvenuto! " + nome).build();
    }

    @POST
    public Response doPost(@FormParam("nome") String nome){
        return Response .ok("Benvenuto! " + nome).build();
    }
}

