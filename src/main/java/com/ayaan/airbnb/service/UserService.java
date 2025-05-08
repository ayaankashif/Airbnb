package com.ayaan.airbnb.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ayaan.airbnb.model.User;
import com.ayaan.airbnb.repository.UserRepository;

@Service
public class UserService {
    
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }
 
    public User updateUser(User user) {
        return userRepository.save(user);
    }
    
    public void deleteUser(Integer id) {
       userRepository.deleteById(id);
    }
    
    public User getUserById(int id) {
        return userRepository.findById(id).orElse(null);
    }

    public User login(String name) {
        if (name == null){
            return null; // Handle null values as needed
        }
        return userRepository.login(name);
    }
}
