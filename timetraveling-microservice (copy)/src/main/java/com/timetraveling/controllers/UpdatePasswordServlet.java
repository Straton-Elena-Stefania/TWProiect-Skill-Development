package com.timetraveling.controllers;

import com.timetraveling.models.users.User;
import com.timetraveling.models.users.UserOpenJPADAO;
import com.timetraveling.utils.secure.PasswordHandler;
import com.timetraveling.utils.validation.LoginValidator;
import com.timetraveling.utils.validation.SessionValidator;
import com.timetraveling.utils.validation.SignUpValidator;
import com.timetraveling.utils.validation.UpdateInfoValidator;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.security.SecureRandom;

@WebServlet(name = "UpdatePasswordServlet", value = "/update-password")
public class UpdatePasswordServlet extends HttpServlet {
    private UserOpenJPADAO userDAO = new UserOpenJPADAO();

    @Override
    protected void doPost(
            HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (!SessionValidator.isLoggedIn(request)) {
            response.sendRedirect(request.getContextPath() + "/");
            return;
        }

        User user = userDAO.findByID((int) request.getSession().getAttribute("id"));

        LoginValidator loginValidator = new LoginValidator();
        if (loginValidator.isAuthenticationValid(user.getEmail(), request.getParameter("oldPassword"))) {
            String password = request.getParameter("newPassword");
            String salt = PasswordHandler.getSalt(SignUpValidator.SALT_LENGTH);
            String securePassword = PasswordHandler.generateSecurePassword(password, salt);
            securePassword = securePassword + salt;
            user.setPassword(securePassword);
            userDAO.update(user);
            response.sendRedirect(request.getContextPath() + "/home");
        } else {
            response.sendRedirect(request.getContextPath() + "/settings.jsp");
        }
    }
}
