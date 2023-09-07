package com.sandeep.springredisdemo.services;

import com.sandeep.springredisdemo.model.User;
import com.sandeep.springredisdemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    public final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean saveUser(User user) {
        return userRepository.save(user);
    }


    @Cacheable("usersListCache")
    public List<User> fetchAllUsers() {
        return userRepository.fetchAllUsers();
    }

    public User getUserById(Integer id) {
        return userRepository.getUserById(id);
    }
}
