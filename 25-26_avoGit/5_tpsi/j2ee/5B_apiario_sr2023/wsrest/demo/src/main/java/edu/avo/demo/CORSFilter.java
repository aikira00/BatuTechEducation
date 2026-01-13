package edu.avo.demo;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
/*ho dovuto creare questa classe perché browser mi ferma le richieste.
vedi problema
si aggiungono header appropriati
CORS (Cross-Origin Resource Sharing) è un meccanismo di sicurezza implementato dai browser per impedire che una
pagina web carichi risorse da un dominio diverso da quello da cui è stata caricata, a meno che il server remoto
non autorizzi esplicitamente la condivisione.
*/

@WebFilter(urlPatterns = {"/api/*"})
public class CORSFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        // Allow requests from any origin
        response.setHeader("Access-Control-Allow-Origin", "*");

        // Allow specific HTTP methods
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");

        // Allow specific headers
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");

        // Allow credentials (if needed)
        // response.setHeader("Access-Control-Allow-Credentials", "true");

        // Set max age for preflight requests
        response.setHeader("Access-Control-Max-Age", "3600");

        // For preflight requests, return immediately
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Initialization code if needed
    }

    @Override
    public void destroy() {
        // Cleanup code if needed
    }
}