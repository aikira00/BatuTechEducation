package edu.avo.demo;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;
import jakarta.ws.rs.ext.ExceptionMapper;

/**
 * Lancia un'eccezione di tipo 500 quando qualche servizio va in errore
 *
 * @author palma
 */
@Provider
public class ExceptionManager implements ExceptionMapper<Throwable> {


    /**
     * Sulla base dell'eccezione avvenuta ivnia al client un respons especifico
     *
     * @param ex L'eccezione avvenuta
     * @return Codice della risposta e messaggio inviato al client
     */
    @Override
    public Response toResponse(Throwable ex) {
        Response response;
        switch (ex.getClass().getSimpleName()) {
            case "NumberFormatException" -> {
                response = Response.status(Response.Status.BAD_REQUEST)
                        .entity("Invalid id format: " + ex.getMessage())
                        .build();
            }
            case "ProcessingException" -> {
                response = Response.status(Response.Status.BAD_REQUEST)
                        .entity("Invalid request payload: " + ex.getMessage())
                        .build();
            }
            case "SQLIntegrityConstraintViolationException" -> {
                response = Response.status(Response.Status.BAD_REQUEST)
                        .entity("Invalid request payload, constraint violation: " + ex.getMessage())
                        .build();
            }
            case "SQLException" -> {
                response = Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                        .entity("SQL Exception, something went wrong " + ex.getMessage())
                        .build();
            }
            case "NamingException" -> {
                response = Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                        .entity("Naming Exception, internal error something went wrong. " + ex.getMessage())
                        .build();
            }
            default -> {
                response = Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                        .entity("Internal server error: " + ex.getMessage())
                        .build();
            }
        }
        return response;
    }
}
