package com.timetraveling.utils.validation;

import com.timetraveling.models.users.User;
import com.timetraveling.models.users.UserOpenJPADAO;

public class UpdateInfoValidator {
    private User user;
    private UserOpenJPADAO userDAO = new UserOpenJPADAO();

    public boolean isUpdateValid(User user) {
        this.user = userDAO.findByEmail(user.getEmail());
        if (this.user == null) {
            return true;
        }
        if (this.user.getId() == user.getId()) {
            return true;
        }
        return false;
    }
}
