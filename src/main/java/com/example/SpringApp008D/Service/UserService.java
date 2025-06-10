package com.example.SpringApp008D.Service;

import com.example.SpringApp008D.Model.User;
import com.example.SpringApp008D.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository usersRepository;

    public List<User> getUsers() {
        return usersRepository.findAll();
    }

    public void addUser(User user) {
        usersRepository.save(user);
    }

    public Optional<User> getuser(int id) {
        return usersRepository.findById(id);
    }

    public void deleteUser(int id) {
        usersRepository.deleteById(id);
    }

    public void updateUser(int id, User user) {
        User us = usersRepository.findById(id).get();
        us.setUsername(user.getUsername());
        us.setPassword(user.getPassword());
        us.setEmail(user.getEmail());
        usersRepository.save(us);
    }
}
