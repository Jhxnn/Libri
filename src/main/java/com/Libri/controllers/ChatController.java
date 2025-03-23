package com.Libri.controllers;


import com.Libri.dtos.ChatDto;
import com.Libri.models.ChatMessage;
import com.Libri.services.ChatMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.time.LocalTime;

@Controller
public class ChatController {


    @Autowired
    ChatMessageService chatMessageService;

    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    public ChatMessage send(ChatDto chatDto){

        return chatMessageService.createMessage(chatDto);
    }
}
