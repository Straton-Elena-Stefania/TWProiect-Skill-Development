package com.timetraveling.controllers;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.timetraveling.models.article.Section;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

@WebServlet(name = "HomepageServlet", value = "/home")
public class HomepageServlet extends HttpServlet {
    private final Gson gson = new Gson();
    private final CloseableHttpClient httpClient = HttpClients.createDefault();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("id") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        request.getRequestDispatcher("/welcome.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("id") == null) {
            response.sendRedirect(request.getContextPath() + "/");
            return;
        }

        System.out.println("Obtinem sectiunile de timetraveling: ");

        /*HttpGet sectionsRequest = new HttpGet("http://timetraveling-web:8080/sections");
        //sectionsRequest.addHeader(HttpHeaders.USER_AGENT, "SkiVI Gateway API");

        sectionsRequest.addHeader("content-type", "application/json");

        try (CloseableHttpResponse sectionsResponse = httpClient.execute(sectionsRequest)) {
            System.out.println(sectionsResponse.getStatusLine().toString());

            HttpEntity entity = sectionsResponse.getEntity();
            Header headers = entity.getContentType();
            System.out.println(headers);

            if (entity != null) {
                // return it as a String
                String result = EntityUtils.toString(entity);
                System.out.println("Fac un jsonarray");
                JsonArray sectionsList = gson.fromJson(result, JsonArray.class);

                System.out.println("Fac un obiect din string-ul meu json");
                Section section = gson.fromJson(sectionsList.get(0), Section.class);

                System.out.println("Afisez numele");
                sectionsList.forEach(sectionJson -> System.out.println("nume sectiune: " + sectionJson.getAsJsonObject().get("name")));

                System.out.println(result);
            }
        }*/

        request.getRequestDispatcher("/welcome.jsp").forward(request, response);
    }
}