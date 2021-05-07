package com.timetraveling.controllers;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "ExceptionHandlerServlet", value = "/error")
public class ExceptionHandlerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Throwable throwable = (Throwable)
                request.getAttribute("jakarta.servlet.error.exception");
        Integer statusCode = (Integer)
                request.getAttribute("jakarta.servlet.error.status_code");
        String servletName = (String)
                request.getAttribute("jakarta.servlet.error.servlet_name");

        if (servletName == null) {
            servletName = "Unknown";
        }
        String requestUri = (String)
                request.getAttribute("jakarta.servlet.error.request_uri");

        if (requestUri == null) {
            requestUri = "Unknown";
        }

        request.setAttribute("requestUri", requestUri);
        request.setAttribute("servletName", servletName);

        response.sendRedirect(request.getContextPath() + "/error.jsp?status=" + statusCode);
    }
}
