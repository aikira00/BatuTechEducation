package edu.avo.backend;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.info.Contact;

/**
 * 
 * Configures Jakarta RESTful Web Services for the application.
 * @author Juneau
 */
@OpenAPIDefinition(
        info = @Info(
                title = "REST web service with CRUD operations on a MySQL database",
                version = "1.0.0",
                description = """
                      This service exposes several endpoints for managing operations.
                      Besides the documented endpoints, the project uses some utility classes:
                      - ConnectionUtility: A utility class with a static method for obtaining 
                        database connection via JNDI. The connection is retrieved from a DataSource 
                        configured in Tomcat's JNDI context (usually defined in the context.xml file). 
                        This allows applications to manage database connections efficiently 
                        within the Tomcat container, promoting a more scalable and 
                        maintainable approach to database connectivity.
                      - ExceptionManager: a class for centralized exception handling.""",
                contact = @Contact(name = "Giuliano Bellucci", email = "gbellucci@itisavogadro.it")
        )
)
@ApplicationPath("api")
public class JakartaRestConfiguration extends Application {
    
}
