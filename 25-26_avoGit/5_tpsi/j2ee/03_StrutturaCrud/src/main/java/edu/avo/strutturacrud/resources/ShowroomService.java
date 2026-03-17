
package edu.avo.strutturacrud.resources;

import edu.avo.esempiodbserver.bo.Showroom;
import edu.avo.esempiodbserver.bo.so.ShowroomSearchObject;
import edu.avo.strutturacrud.jsonutility.ShowroomJson;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/showrooms")
public class ShowroomService {
        @GET
        @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response selectAll(){
        return Response
                .ok("no param")
                .build();
    }
    @GET
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response selectById(@PathParam("id") int id){
       return Response
                .ok(id)
                .build();
    }
    
    @GET
    @Path("/search")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response search(ShowroomSearchObject object){
        return Response
                .ok("ping Jakarta EE")
                .build();
    }
    
    @DELETE
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteById(@QueryParam("id") int id){
        return Response
                .ok("ping Jakarta EE")
                .build();
    }
    
    @POST
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response insert(ShowroomJson object){
        Showroom s = object.getShowroom();
        return Response
                .ok("ping Jakarta EE")
                .build();
    }
    
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(ShowroomJson object){
        Showroom s = object.getShowroom();
        return Response
                .ok("ping Jakarta EE")
                .build();
    }
}
