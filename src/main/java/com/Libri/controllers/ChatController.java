package com.Libri.controllers;


import com.Libri.dtos.ChatDto;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.time.LocalTime;

@Controller
public class ChatController {


    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    public ChatDto send(ChatDto chatDto){

        chatDto.timestamp() = LocalTime.now().toString();
        return chatDto;
    }
}
