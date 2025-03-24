package com.Libri.services;


import com.Libri.dtos.AuthDto;
import com.Libri.dtos.UserRequestDto;
import com.Libri.dtos.UserResponseDto;
import com.Libri.infra.security.TokenService;
import com.Libri.models.User;
import com.Libri.repositories.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;


    @Autowired
    private AuthenticationManager authenticationManager;


    @Autowired
    TokenService tokenService;

    @Autowired
    EmailService emailService;




    public UserResponseDto updateUser(UUID id, UserRequestDto userRequestDto) {
        var user = userRepository.findById(id).orElseThrow(()-> new RuntimeException("Cannot be found"));
        BeanUtils.copyProperties(userRequestDto, user);
        userRepository.save(user);
        return new UserResponseDto(user.getUserid(), user.getName(), user.getEmail());

    }

    public User findById1(UUID id){
        return userRepository.findById(id).orElseThrow(()-> new RuntimeException("Cannot be found"));
    }

    public UserResponseDto createUser(UserRequestDto userRequestDto) {
        if(userRepository.findByEmail(userRequestDto.email()) != null) return null;
        String encryptedPass = new BCryptPasswordEncoder().encode(userRequestDto.password());

        var user = new User();
        BeanUtils.copyProperties(userRequestDto, user);
        user.setPassword(encryptedPass);
        emailService.enviarEmailTexto(user.getEmail(), "Conta criada","Bem vindo ao Libri " + user.getName() + ", faça bom uso :)");
        userRepository.save(user);
        return new UserResponseDto(user.getUserid(), user.getName(), user.getEmail());
    }
    public String returnToken(AuthDto authDto) {
        var usernamePassord = new UsernamePasswordAuthenticationToken(authDto.email(), authDto.password());
        var auth = this.authenticationManager.authenticate(usernamePassord);
        var token = tokenService.generateToken((User) auth.getPrincipal());
        return token;
    }



    public void deleteUser(UUID id) {
        var user = userRepository.findById(id).orElseThrow(()-> new RuntimeException("Cannot be found"));
        userRepository.delete(user);
    }
    public UserResponseDto findById(UUID id) {
        var user = userRepository.findById(id).orElseThrow(()-> new RuntimeException("Cannot be found"));
        return new UserResponseDto(user.getUserid(), user.getName(),user.getEmail());
    }
    public List<UserResponseDto> findAll(){
        var users = userRepository.findAll();
        return users.stream()
                .map(user -> new UserResponseDto(user.getUserid(), user.getName(), user.getEmail()))
                .toList();
    }
}
