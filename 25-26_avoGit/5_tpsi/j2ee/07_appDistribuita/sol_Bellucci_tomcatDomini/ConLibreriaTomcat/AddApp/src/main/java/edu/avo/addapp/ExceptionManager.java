/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.avo.addapp;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

/**
 * Lancia un'eccezione  quando qualche servizio va in errore
 * 
 * @author palma
 */
@Provider
public class ExceptionManager implements ExceptionMapper<Throwable> {
    
    /**
     * Sulla base dell'eccezione avvenuta ivnia al client un respons especifico
     * 
     * @param ex L'eccezione avvenuta
     * 
     * @return Codice della risposta e messaggio inviato al client
     */
    @Override
    public Response toResponse(Throwable ex) {
        Response response;
        switch(ex.getClass().getSimpleName()){
            case "NumberFormatException"->{
                response=Response.status(Response.Status.BAD_REQUEST)
                .entity("Invalid id format: " + ex.getMessage())
                .build();
            }  
            case "ProcessingException"->{
                response=Response.status(Response.Status.BAD_REQUEST)
                .entity("Invalid request payload: " + ex.getMessage())
                .build();
            }
            default->{
                response=Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity("Internal server error: " + ex.getMessage())
                .build();
            }
        }
        return response;
    }
}
