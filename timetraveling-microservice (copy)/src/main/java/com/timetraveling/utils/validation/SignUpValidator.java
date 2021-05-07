package com.timetraveling.utils.validation;


import com.timetraveling.models.users.User;
import com.timetraveling.models.users.UserOpenJPADAO;

public class SignUpValidator implements UserValidator {
    private User user;
    private UserOpenJPADAO userDAO = new UserOpenJPADAO();

    /**
     * Validare de backend
     * @param password
     * @return
     */
    private boolean isPasswordValid(String password) {
        if (password.equals("parola")) {
            return false;
        }
        return true;
    }

    private boolean isEmailAvailable(String email) {
        this.user = userDAO.findByEmail(email);

        if (user == null) {
            return true;
        }
        return false;
    }

    private boolean isEmailValid(String email) {
        boolean isValid = true;

        if (email.equals("teodora@yahoo.com")) {
            isValid = false;
        }

        return isValid && isEmailAvailable(email);
    }

    public boolean isRegistrationValid(User user) {
        return isEmailValid(user.getEmail()) && isPasswordValid(user.getPassword());
    }
}
