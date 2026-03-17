
package  edu.avo.strutturacrud.resources;



import edu.avo.esempiodbserver.bo.Category;
import edu.avo.esempiodbserver.bo.so.CategorySearchObject;
import edu.avo.strutturacrud.jsonutility.CategoryJson;
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
import java.util.ArrayList;
import java.util.List;


@Path("/categories")
public class CategoryService {

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response selectAll() {
        List<Category> list = new ArrayList();
        list.add(new Category(1, "qiejgigh"));
        list.add(new Category(2, "hDJFDHDFJD"));
        return Response
                .ok(list)
                .build();
    }

    @GET
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response selectById(@PathParam("id") int id) {
        return Response
                .ok(id)
                .build();
    }

    @GET
    @Path("/search")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response search(CategorySearchObject object) {
        return Response
                .ok("ping Jakarta EE")
                .build();
    }

    @DELETE
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteById(@QueryParam("id") int id) {
        return Response
                .ok("ping Jakarta EE")
                .build();
    }

    @POST
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response insert(CategoryJson object) {
        Category c=object.getCategory();
        return Response
                .ok("ping Jakarta EE")
                .build();
    }

    
}
