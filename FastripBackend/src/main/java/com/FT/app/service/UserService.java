package com.FT.app.service;

import java.util.List;

import com.FT.app.domain.Role;
import com.FT.app.domain.User;

public interface UserService {
    User saveUser(User user);
    Role saveRole(Role role);
    void addRoleToUser(String username, String roleName);
    User getUser(String username);
    Role getRole(String name);
    List<User> getUsers();
}
