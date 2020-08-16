package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/deconnexion")

public class Deconnexion extends HttpServlet {
    public static final String URL_REDIRECTION = "adsMercedesC"; // Cas d'une redirection
    public static final String URL_REDIRECTION2 = "connexion";
    //public static final String VUE = "/connexion";                    // Cas d'un forward

    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        /* Récupération et destruction de la session en cours */
        HttpSession session = request.getSession();
        session.invalidate();

        response.sendRedirect( URL_REDIRECTION );                    // Cas d'une redirection
        //this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );        // Cas d'un forward
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        /* Récupération et destruction de la session en cours */
        HttpSession session = request.getSession();
        session.invalidate();

        response.sendRedirect( URL_REDIRECTION2 );                    // Cas d'une redirection
        //this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );        // Cas d'un forward
    }
}