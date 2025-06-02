package com.example.SpringApp008D.Controller;

import com.example.SpringApp008D.Model.User;
import com.example.SpringApp008D.Service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@Tag(name="Controlador Usuarios",description = "Servicios de gestion de usuarios")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    @Operation(summary = "Obtener usuarios",description = "Obtiene la lista completa de los usuarios del sistema")
    @ApiResponse(responseCode = "200",description = "Retorna la lista de usuarios")
    public String getUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busqueda de usuario",description = "Busqueda de usuario en el sistema segun su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Usuario encontrado"),
            @ApiResponse(responseCode = "404",description = "Usuario no existente")
    })
    public String getUserById(@PathVariable int id){
        return userService.getUser(id);
    }

    @PostMapping
    public String postUser(@RequestBody User user){
        return userService.addUser(user);
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable int id){
        return userService.deleteUser(id);
    }
    @PutMapping
    public String putUser(@RequestBody User user){
        return userService.updateUser(user);
    }
}
