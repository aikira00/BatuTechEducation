package edu.avo.autservice;

import jakarta.annotation.Resource;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Path("/auth")
public class HelloResource {

    @Resource(lookup = "jdbc/tpsi5_usersdb")
    private DataSource ds;

    // Lista statica dei token attivi — condivisa tra tutte le istanze
    private static final List<Integer> tokens = new ArrayList<>();
    private static final Random random = new Random();

    // POST /api/auth — login con username e password
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response login(
            @FormParam("username") String username,
            @FormParam("password") String password) {

        String sql = "SELECT * FROM users WHERE username=? AND password=SHA2(?,256)";

        try (Connection conn = ds.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);
            ps.setString(2, password);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    // Genera token univoco tra 1000000 e 2000000
                    int token;
                    do {
                        token = 1000000 + random.nextInt(1000000);
                    } while (tokens.contains(token));

                    tokens.add(token);
                    return Response.ok(String.valueOf(token)).build();
                } else {
                    return Response.status(Response.Status.UNAUTHORIZED)
                            .entity("Credenziali non valide").build();
                }
            }
        } catch (SQLException e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

    // GET /api/auth — verifica token
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response verify(@HeaderParam("Authorization") String authHeader) {

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Header Authorization mancante o malformato").build();
        }

        // Estrae il token dalla stringa "Bearer 1234567"
        String tokenStr = authHeader.substring(7); // rimuove "Bearer "
        int token;
        try {
            token = Integer.parseInt(tokenStr);
        } catch (NumberFormatException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Token non valido").build();
        }

        if (tokens.contains(token)) {
            return Response.ok("true").build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity("false").build();
        }
    }
}