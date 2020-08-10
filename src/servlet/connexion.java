package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/connexion")
public class connexion extends HttpServlet {

    public static final String VUE = "/WEB-INF/connexion.jsp";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String user = request.getParameter("User");

        HttpSession session = request.getSession();

        session.setAttribute("User", user);


        this.getServletContext().getRequestDispatcher(VUE).forward(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
    }
}
