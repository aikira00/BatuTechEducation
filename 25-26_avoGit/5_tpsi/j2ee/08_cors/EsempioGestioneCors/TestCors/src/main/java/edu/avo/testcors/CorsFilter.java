package edu.avo.testcors;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;



@WebFilter("/*")
public class CorsFilter implements Filter{

    @Override
   
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletResponse res = (HttpServletResponse) response;
        HttpServletRequest req = (HttpServletRequest) request;

        // Permette richieste solo da HTTPS (sostituisci con il tuo dominio)
        res.setHeader("Access-Control-Allow-Origin", "*");

        // Metodi HTTP consentiti
        res.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");

        // Header consentiti
        res.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");

        // Permette cookie / autenticazione
        res.setHeader("Access-Control-Allow-Credentials", "true");
       
        if ("OPTIONS".equalsIgnoreCase(req.getMethod())) {
            res.setStatus(HttpServletResponse.SC_OK);
            return; // NON continua la chain
        }
        chain.doFilter(request, response);
    }
}

