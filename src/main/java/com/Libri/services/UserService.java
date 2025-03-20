package com.Libri.services;


import com.Libri.dtos.UserRequestDto;
import com.Libri.dtos.UserResponseDto;
import com.Libri.repositories.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;


    public UserResponseDto updateUser(UUID id, UserRequestDto userRequestDto) {
        var user = userRepository.findById(id).orElseThrow(()-> new RuntimeException("Cannot be found"));
        BeanUtils.copyProperties(userRequestDto, user);
        userRepository.save(user);
        return new UserResponseDto(user.getUserid(), user.getName(), user.getEmail());

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
