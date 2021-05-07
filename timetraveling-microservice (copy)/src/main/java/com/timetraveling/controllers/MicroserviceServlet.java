package com.timetraveling.controllers;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

@WebServlet(name = "MicroserviceServlet", value = "/micro")
public class MicroserviceServlet extends HttpServlet {
    private String getRequestToMicro(String targetURL, String urlParameters) {
        HttpURLConnection urlConnection = null;

        try {
            URL url = new URL(targetURL);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            //Send request
            DataOutputStream wr = new DataOutputStream (
                    urlConnection.getOutputStream());
            wr.writeBytes(urlParameters);
            wr.close();

            //Get Response
            InputStream is = urlConnection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            StringBuilder response = new StringBuilder(); // or StringBuffer if Java version 5+
            String line;
            while ((line = rd.readLine()) != null) {
                response.append(line);
                response.append('\r');
            }
            rd.close();
            return response.toString();
        } catch (IOException urlException) {
            urlException.printStackTrace();
        } finally {
            urlConnection.disconnect();
        }

        return targetURL;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("comunic cu micro");
        getRequestToMicro("http://localhost:8087", "value");
    }
}
