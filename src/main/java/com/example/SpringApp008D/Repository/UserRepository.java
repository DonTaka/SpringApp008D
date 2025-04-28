package com.example.SpringApp008D.Repository;

import com.example.SpringApp008D.Model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository {
    private List<User> users = new ArrayList<>();

    public UserRepository() {

    }

    public String getUser(int id) {
        for (User user : users) {
            if (user.getId() == id) {
                return user.toString();
            }
        }
        return "Usuario no encontrado";
    }

    public String addUser(User user) {
        users.add(user);
        return "Usuario agregado con exito";
    }

    public String removeUser(int id) {
        for (User user : users) {
            if (user.getId() == id) {
                users.remove(user);
                return "Usuario removido con exito";
            }
        }
        return "Usuario no encontrado";
    }

    public String updateUser(User newUser) {
        for (User user : users) {
            if (user.getUsername().equalsIgnoreCase(newUser.getUsername())) {
                //SET = Indice + objeto nuevo para reemplazar
                int index = users.indexOf(user);
                users.set(index, newUser);
               return "Usuario actualizado con exito";
            }
        }
        return "Usuario no encontrado";
    }

    public String getUsers() {
        String output = "";
        for (User user : users) {
            output += "Id: " + user.getId() + "\n";
            output += "Username: " + user.getUsername() + "\n";
            output += "Password: " + user.getPassword() + "\n";
            output += "Email: " + user.getEmail() + "\n\n";
        }
        if(output.isEmpty()){
            return "No existen usuarios";
        }else{
            return output;
        }
    }
}
