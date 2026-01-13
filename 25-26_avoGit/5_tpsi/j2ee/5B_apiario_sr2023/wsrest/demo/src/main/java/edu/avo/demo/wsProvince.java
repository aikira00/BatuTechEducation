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
public class wsProvince {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/province")
    public Response doGetProvince() throws SQLException, NamingException {
        Connection conn = ConnectionUtility.getConnection();
        Server server = new Server(conn);
        List<String> province = server.selectProvince();
        conn.close();
        return Response.ok(province).build();
    }

}