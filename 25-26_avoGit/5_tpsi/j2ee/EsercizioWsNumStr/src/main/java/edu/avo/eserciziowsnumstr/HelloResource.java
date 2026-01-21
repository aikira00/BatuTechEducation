package edu.avo.eserciziowsnumstr;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;

@Path("calcola")
public class HelloResource {
    @GET
    @Produces("text/plain")
    public String hello() {
        return "Hello, World!";
    }

    @GET
    @Path("add/{numeroa}/{numerob}")
    public Response add(@PathParam("numeroa") int numeroa, @PathParam("numerob") int numerob) {
        String res = numeroa + " + " + numerob + " = " + Integer.toString(numeroa+numerob);

       return Response.ok(res).build();
    }

    @GET
    @Path("sub/{numeroa}/{numerob}")
    public Response sub(@PathParam("numeroa") int numeroa, @PathParam("numerob") int numerob) {
        String res = numeroa + " - " + numerob + " = " + Integer.toString(numeroa-numerob);

        return Response.ok(res).build();
    }
}