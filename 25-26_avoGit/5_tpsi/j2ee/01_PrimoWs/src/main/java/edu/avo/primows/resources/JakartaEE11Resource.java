package edu.avo.primows.resources;

import edu.avo.primows.bo.Allievo;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/**
 *
 * @author 
 */
@Path("ws1")
public class JakartaEE11Resource {
    
    @GET
    @Path("{messaggio}")
    public Response ping(@PathParam("messaggio") String messaggio){
        return Response
                .ok("Hello "+messaggio)
                .build();
    }
    @GET
    @Path("allievo")
    @Produces(MediaType.APPLICATION_JSON)
    public Response pong(){
        Allievo allievo=new Allievo();
        allievo.setCognome("shgffhg");
        allievo.setNome("hdsfhhgfg");
        allievo.setEta(19);
        
        return Response
                .ok(allievo)
                .build();
    }
}
