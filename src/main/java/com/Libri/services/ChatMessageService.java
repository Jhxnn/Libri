package com.Libri.services;


import com.Libri.dtos.ChatDto;
import com.Libri.models.ChatMessage;
import com.Libri.repositories.ChatMessageRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatMessageService {

    @Autowired
    ChatMessageRepository chatMessageRepository;


    public ChatMessage createMessage(ChatDto chatDto){
        var chatMessage = new ChatMessage();
        BeanUtils.copyProperties(chatDto, chatMessage);
        return chatMessageRepository.save(chatMessage);
    }



}
