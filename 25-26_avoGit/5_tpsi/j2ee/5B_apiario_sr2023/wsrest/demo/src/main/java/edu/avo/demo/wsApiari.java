package edu.avo.demo;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path("/apiari")
public class wsApiari {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/all")
    public Response doGetApiari() throws SQLException, NamingException {
        Connection conn = ConnectionUtility.getConnection();
        Server server = new Server(conn);
        List<Apiario> apiari = server.selectApiari();
        conn.close();
        return Response.ok(apiari).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/search")
    public Response doGetApiariByProvincia(@QueryParam("provincia") String provincia) throws NamingException, SQLException {
        Connection conn = ConnectionUtility.getConnection();
        Server server = new Server(conn);
        List<Apiario> apiari = server.selectApiariByProvincia(provincia);
        conn.close();
        return Response.ok(apiari).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/add")
    public Response doPostCreateMiele(Miele miele) throws NamingException, SQLException {
        try (Connection conn = ConnectionUtility.getConnection()) {
            Server server = new Server(conn);
            boolean existsMiele = server.existsMiele(miele);
            Map<String, Object> result = new HashMap<>();
            if (existsMiele) {
                //miele non inserito perché esiste
                result.put("error", "Inserimento non riuscito, il miele esiste già");
                return Response.status(Response.Status.CONFLICT) // HTTP 409
                        .entity(result)
                        .build();
            } else {
                //check eventuale tipologia miele se esiste
                List<TipoMiele> tipologie = server.selectTipiMiele();
                boolean foundTipologia = false;
                for(TipoMiele tipoMiele : tipologie){
                    if(tipoMiele.getId() == miele.getTipologia()){
                        foundTipologia = true;
                        break;
                    }
                }
                if(foundTipologia){
                    int insertedRows = server.insertMiele(miele);
                    if (insertedRows >0) {
                        result.put("added", insertedRows);
                        result.put("miele", miele);
                        return Response.ok(result).build();
                    }
                    else{
                        result.put("error", "Inserimento non riuscito, json malformato? eh?");
                        return Response.status(Response.Status.BAD_REQUEST) // HTTP 400
                                .entity(result)
                                .build();
                    }
                }
                else{
                    result.put("error", "Inserimento non riuscito, tipologia miele non valida");
                    return Response.status(Response.Status.BAD_REQUEST) // HTTP 400
                            .entity(result)
                            .build();
                }
            }
        }
    }
}