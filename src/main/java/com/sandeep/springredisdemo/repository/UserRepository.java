package com.sandeep.springredisdemo.repository;

import com.sandeep.springredisdemo.model.User;

import java.util.List;

public interface UserRepository {
    public boolean save(User user);

    List<User> fetchAllUsers();

    User getUserById(Integer id);
}
