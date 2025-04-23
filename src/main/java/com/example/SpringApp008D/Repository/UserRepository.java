package com.example.SpringApp008D.Repository;

import com.example.SpringApp008D.Model.User;

import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    private List<User> users = new ArrayList<>();

    public UserRepository() {

    }

    public String getUser(String username){
        for(User user:users){
            if(user.getUsername().equalsIgnoreCase(username)){
                return user.toString();
            }
        }
        return "Usuario no encontrado";
    }
    public void addUser(User user){
        users.add(user);
        System.out.println("Usuario agregado con exito");
    }

    public void removeUser(String username){
        for(User user:users){
            if(user.getUsername().equalsIgnoreCase(username)){
                users.remove(user);
                System.out.println("Usuario eliminado con exito");
                break;
            }
        }
        System.out.println("Usuario no encontrado");
    }

    public void updateUser(User newUser){
        for(User user:users){
            if(user.getUsername().equalsIgnoreCase(newUser.getUsername())){
                //SET = Indice + objeto nuevo para reemplazar
                int index = users.indexOf(user);
                users.set(index, newUser);
                System.out.println("Usuario actualizado con exito");
                break;
            }
        }
        System.out.println("Usuario no encontrado");
    }
    public String getUsers(){
        String output="";
        for(User user:users){
            output+=user.toString();
        }
        return output;
    }
}
