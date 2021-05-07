package com.timetraveling.controllers;

import com.timetraveling.models.users.Skill;
import com.timetraveling.models.users.User;
import com.timetraveling.models.users.UserOpenJPADAO;
import com.timetraveling.utils.validation.SessionValidator;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@MultipartConfig
@WebServlet(name = "AddSkillServlet", value = "/add-skill")
public class AddSkillServlet extends HttpServlet {
    private UserOpenJPADAO userDAO = new UserOpenJPADAO();

    @Override
    protected void doPost(
            HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (!SessionValidator.isLoggedIn(request)) {
            response.sendRedirect(request.getContextPath() + "/");
            return;
        }
        String skill = new String(request.getPart("skill").getInputStream().readAllBytes());
        User user = userDAO.findByID((int) request.getSession().getAttribute("id"));
        if (skill.equals("Cooking") && user.getCooking() == Skill.UNACTIVATED) {
            user.setCooking(Skill.ACTIVATED);
        } else if (skill.equals("Time travelling") && user.getTimeTravelling() == Skill.UNACTIVATED) {
            user.setTimeTravelling(Skill.ACTIVATED);
        } else if (skill.equals("First aid") && user.getFirstAid() == Skill.UNACTIVATED) {
            user.setFirstAid(Skill.ACTIVATED);
        } else {
            response.setStatus(400);
            return;
        }
        userDAO.update(user);
    }
}
