package com.timetraveling.timetraveling_microservice;

import com.timetraveling.models.users.User;
import com.timetraveling.models.users.UserOpenJPADAO;
import com.timetraveling.utils.validation.LoginValidator;
import com.timetraveling.utils.validation.SessionValidator;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * Acest servlet este pentru a implementa utilitatea
 * de login pe site. Utilitatea de login poate fi accesata
 * pe pagina de index atunci cand un utilizator acceseaza
 * site-ul pentru prima oara intr-o sesiune.
 */
@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(
            HttpServletRequest  request, HttpServletResponse response)
            throws ServletException, IOException {
            LoginValidator loginValidator = new LoginValidator();

            /**
            * Aceasta este pentru cand cineva introduce
            * manual url-ul pentru index.jsp sau pentru /,
            * ca sa il redirectionam catre pagina de home.
            *
            * Pentru a ajunge la pagina aceasta, userul trebuie
            * ori sa apeleze explicit logout, ori sesiunea sa
            * expire.
            */
            if (SessionValidator.isLoggedIn(request)) {
                System.out.println("userul este logat: " + request.getSession().getAttribute("id"));
                response.sendRedirect(request.getContextPath() + "/home");
                return;
            }

            /**
            * Obtinem email-ul si parola din formularul de pe
            * index.jsp ca si parametri pasati in request.
            */
            String email = request.getParameter("loginEmail");
            String password = request.getParameter("password");

            /**
            * Vedem daca credentialele se potrivesc cu ce este in
            * baza de date. Pentru asta, folosim email-ul si facem
            * un query peste baza de date. Pentru email-ul gasit
            * in baza de date, vedem daca parola (dupa ce este hashed)
            * se potriveste.
            */
            if (loginValidator.isAuthenticationValid(email, password)) {
                User user = (new UserOpenJPADAO()).findByEmail(email);

                /**
                * Aici setam parametrii de sesiune, precum timpul
                * maxim de inactivitate pana delogam userul si un id
                * ca sa stim cu cine avem de-a face.
                */
                request.getSession(true).setMaxInactiveInterval(3000);
                request.getSession(true).setAttribute("id", user.getId());

                /**
                * Prezentam homepage cu skill-uri.
                */
                response.sendRedirect(request.getContextPath() + "/home");
        } else {
                response.sendRedirect(request.getContextPath() + "/");
        }

    }

    @Override
    protected void doGet(
            HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (SessionValidator.isLoggedIn(request)) {
            response.sendRedirect(request.getContextPath() + "/home");
        } else {
            response.sendRedirect(request.getContextPath());
        }
    }
}