package edu.avo;

import edu.avo.Autore;
import edu.avo.DbServer;

import jakarta.annotation.Resource;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@Path("/autori")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class AutoriResource {

   /* con glassfish si potrebbe fare cos` @Resource(lookup="jdbc/mydb")
   così ma con tomcat bisogna fare lookup manuale via codice*/

    private DataSource ds;
// GET /api/autori
    @GET
    public Response getAutori() {
        try (Connection conn = getConnection()) {
            List<Autore> autori = new DbServer(conn).getAutori();
            return Response.ok(autori).build();
        } catch (NamingException | SQLException e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

    // GET /api/autori/5
    @GET
    @Path("/{id}")
    public Response getAutore(@PathParam("id") int id) {
        try (Connection conn = getConnection()) {
            Autore autore = new DbServer(conn).getAutore(id);
            return Response.ok(autore).build();
        } catch (NamingException | SQLException e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

    // POST /api/autori
    @POST
    public Response insertAutore(Autore autore) {
        try (Connection conn = getConnection()) {
            int rows = new DbServer(conn).insertAutore(autore);
            if (rows == 0) return Response.status(Response.Status.BAD_REQUEST).build();
            return Response.status(Response.Status.CREATED).build();
        } catch (SQLException | NamingException e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

    // PUT /api/autori/5
    @PUT
    @Path("/{id}")
    public Response updateAutore(@PathParam("id") int id, Autore autore) {
        try (Connection conn = getConnection()) {
            DbServer dao = new DbServer(conn);
            int rows = dao.updateAutore(autore);
            if (rows == 0) return Response.status(Response.Status.NOT_FOUND).build();
            return Response.ok().build();
        } catch (SQLException | NamingException e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

    // DELETE /api/autori/5
    @DELETE
    @Path("/{id}")
    public Response deleteAutore(@PathParam("id") int id) {
        try (Connection conn = getConnection()) {
            DbServer dao = new DbServer(conn);
            int rows = dao.deleteAutore(id);
            if (rows == 0) return Response.status(Response.Status.NOT_FOUND).build();
            return Response.noContent().build();
        } catch (SQLException | NamingException e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

    private Connection getConnection() throws SQLException, NamingException {
        InitialContext ctx = new InitialContext();
        DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/biblioteca");
        return ds.getConnection();
    }
}