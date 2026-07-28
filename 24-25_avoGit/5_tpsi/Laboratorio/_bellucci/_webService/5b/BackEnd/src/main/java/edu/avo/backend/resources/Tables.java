package edu.avo.backend.resources;

import edu.avo.backend.ConnectionUtility;
import edu.avo.mysqllibrary.Server;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import javax.naming.NamingException;

/**
 *
 * @author
 */
@Path("tables")
public class Tables {

    @GET
    public Response getTable() throws NamingException, SQLException {
        Connection connection = ConnectionUtility.getConnection();
        Server server = new Server(connection);
        List<String> list = server.selectTables();
        connection.close();
        return Response.ok(list, MediaType.APPLICATION_JSON).build();
    }
    
}
