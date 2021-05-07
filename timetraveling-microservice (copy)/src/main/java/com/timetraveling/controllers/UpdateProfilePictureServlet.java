package com.timetraveling.controllers;

import com.sun.xml.bind.v2.model.util.ArrayInfoUtil;
import com.timetraveling.models.users.User;
import com.timetraveling.models.users.UserOpenJPADAO;
import com.timetraveling.utils.validation.SessionValidator;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.SequenceInputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

@MultipartConfig
@WebServlet(name = "AddSkillServlet", value = "/add-skill")
public class UpdateProfilePictureServlet extends HttpServlet {
    private UserOpenJPADAO userDAO = new UserOpenJPADAO();

    @Override
    protected void doPost(
            HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (!SessionValidator.isLoggedIn(request)) {
            response.sendRedirect(request.getContextPath() + "/");
            return;
        }
        Part file = request.getPart("file");
        if (file == null) {
            response.setStatus(400);
            return;
        }
        byte[] profilePicture = file.getInputStream().readAllBytes();
        User user = userDAO.findByID((int) request.getSession().getAttribute("id"));
        user.setProfilePictureType(file.getContentType());
        user.setProfilePicture(profilePicture);
        userDAO.update(user);
    }
}
