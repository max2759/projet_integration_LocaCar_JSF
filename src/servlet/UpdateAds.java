package servlet;

import forms.AdsForm;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;

@WebServlet("/update")
@MultipartConfig(
        fileSizeThreshold=1024*1024*2, // 2MB
        maxFileSize=1024*1024*10,      // 10MB
        maxRequestSize=1024*1024*50   // 50MB
)
public class UpdateAds extends HttpServlet {

    public static final String VUE = "annonces";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        AdsForm adsForm = new AdsForm();

        try {
            adsForm.updateAds(request);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        response.sendRedirect(VUE);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
