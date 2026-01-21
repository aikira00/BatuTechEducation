package edu.avo.simplejspservlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet("/saluta")
public class SalutoServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Recupera il parametro dal form
        String nome = request.getParameter("nome");

        // Imposta il nome come attributo della request
        request.setAttribute("nomeUtente", nome);

        // Forward alla pagina JSP
        request.getRequestDispatcher("/risultato.jsp").forward(request, response);
    }
}