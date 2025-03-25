package com.Libri.controllers;

import com.Libri.dtos.AuthDto;
import com.Libri.dtos.UserRequestDto;
import com.Libri.dtos.UserResponseDto;
import com.Libri.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;


    @Operation(description="Lista todos os usuarios")
    @GetMapping
    public ResponseEntity<List<UserResponseDto>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(userService.findAll());
    }

    @GetMapping("/{id}")
    @Operation(description="Lista usuario pelo id")
    public ResponseEntity<UserResponseDto> findById(@PathVariable(name = "id") UUID id){
        return ResponseEntity.status(HttpStatus.OK).body(userService.findById(id));
    }

    @PostMapping("/register")
    @Operation(description="Registra um usuario")
    public ResponseEntity<UserResponseDto> createUser(@RequestBody UserRequestDto userRequestDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(userRequestDto));
    }

    @Operation(description="Login de usuario")
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody AuthDto authDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.returnToken(authDto));
    }

    @Operation(description="Atualiza um usuario")
    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDto> updateUser(@PathVariable(name = "id")UUID id,
                                                      @RequestBody UserRequestDto userRequesDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.updateUser(id, userRequesDto));
    }


    @Operation(description="Deleta um usuario")
    @DeleteMapping("/{id}")
    public ResponseEntity<UserResponseDto> deleteUser(@PathVariable(name = "id")UUID id){
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
