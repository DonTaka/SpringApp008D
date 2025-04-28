package com.example.SpringApp008D.Service;

import com.example.SpringApp008D.Model.User;
import com.example.SpringApp008D.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public String addUser(User user) {
        return userRepository.addUser(user);
    }
    public String updateUser(User user) {
        return userRepository.updateUser(user);
    }
    public String deleteUser(int id) {
        return userRepository.removeUser(id);
    }
    public String getUser(int id) {
        return userRepository.getUser(id);
    }
    public String getAllUsers() {
        return userRepository.getUsers();
    }
}
