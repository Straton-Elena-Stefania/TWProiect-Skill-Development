package com.timetraveling.utils.validation;

import jakarta.servlet.http.HttpServletRequest;

public final class SessionValidator {
    private SessionValidator() {}

    public static boolean isLoggedIn(HttpServletRequest request) {
        if (request.getSession().getAttribute("id") != null) {
            System.out.println("Esti logat");
            return true;
        }

        System.out.println("Nu esti logat");
        return false;
    }
}
