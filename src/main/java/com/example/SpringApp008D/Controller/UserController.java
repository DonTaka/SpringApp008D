package com.example.SpringApp008D.Controller;

import com.example.SpringApp008D.Assembler.UserModelAssembler;
import com.example.SpringApp008D.Model.User;
import com.example.SpringApp008D.Service.UserService;
import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/users")
@Tag(name = "Controlador Usuarios", description = "Servicio de gestion de usuarios Fullstack I")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    UserModelAssembler assembler;

    @GetMapping
    @Operation(summary = "Obtener Usuarios", description = "Obtiene la lista completa de usuarios registrados en sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna lista completa de usuarios"),
            @ApiResponse(responseCode = "404", description = "No se encuentran datos")
    })
    public ResponseEntity<CollectionModel<EntityModel<User>>> getAllUsers() {
        List<User> lista = userService.getUsers();
        if (lista.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
           return new ResponseEntity<>(assembler.toCollectionModel(lista), HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar Usuario por ID", description = "Obtiene un usuario segun el ID registrado en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna Usuario"),
            @ApiResponse(responseCode = "404", description = "No se encuentran datos")
    })
    @Parameter(description = "El ID del usuario", example = "123")
    public ResponseEntity<EntityModel<User>> getUserById(@PathVariable int id) {
        if (userService.getuser(id).isPresent()) {
            User user = userService.getuser(id).get();
            return new ResponseEntity<>(assembler.toModel(user), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    @Operation(summary = "Agregar Usuario", description = "Permite registrar un usuario en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuario creado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = User.class))),
            @ApiResponse(responseCode = "204", description = "No hay contenido en la solicitud")
    })
    public ResponseEntity<EntityModel<User>> addUser(@RequestBody User user) {
        userService.addUser(user);
        if (userService.getuser(user.getId()).isPresent()) {
            return new ResponseEntity<>(assembler.toModel(user), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar Usuario por ID", description = "Elimina un usuario segun el ID registrado en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna Usuario"),
            @ApiResponse(responseCode = "404", description = "No se encuentran datos")
    })
    @Parameter(description = "El ID del usuario", example = "123")
    public ResponseEntity<Void> deleteUserById(@PathVariable int id) {
        if (userService.getuser(id).isPresent()) {
            userService.deleteUser(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar Usuario", description = "Permite actualizar los datos de un usuario segun su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario modificado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = User.class))),
            @ApiResponse(responseCode = "204", description = "No hay contenido en la solicitud")
    })
    @Parameter(description = "El ID del usuario", example = "123")
    public ResponseEntity<EntityModel<User>> updateUser(@PathVariable int id, @RequestBody User user) {
        if (userService.getuser(id).isPresent()) {
            userService.updateUser(id, user);
            return new ResponseEntity<>(assembler.toModel(user), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
