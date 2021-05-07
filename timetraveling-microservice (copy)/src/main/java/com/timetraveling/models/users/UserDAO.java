package com.timetraveling.models.users;

public interface UserDAO {
    User findByUsername(String username);

    User findByEmail(String email);

    User findByID(int id);

    void save(User user);

    void update(User user);
}
