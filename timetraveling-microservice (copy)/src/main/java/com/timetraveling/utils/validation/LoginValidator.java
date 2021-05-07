package com.timetraveling.utils.validation;

import com.timetraveling.exceptions.UserNotFoundException;
import com.timetraveling.models.users.User;
import com.timetraveling.models.users.UserOpenJPADAO;
import com.timetraveling.utils.secure.PasswordHandler;

public class LoginValidator implements UserValidator {
    private User user;
    private UserOpenJPADAO userDAO = new UserOpenJPADAO();

    private boolean isPasswordValid(String password) {
        String userPassword = user.getPassword();
        String userSalt = userPassword.substring(userPassword.length() - SALT_LENGTH);
        String securePassword = userPassword.substring(0, userPassword.length() - SALT_LENGTH);

        boolean passwordMatch = PasswordHandler.verifyUserPassword(password,
                                                                   securePassword, userSalt);

        return passwordMatch;
    }

    public boolean isAuthenticationValid(String email, String password) {
        this.user = userDAO.findByEmail(email);
        if (user == null) {
            return false;
        }
        return isPasswordValid(password);
    }
}
