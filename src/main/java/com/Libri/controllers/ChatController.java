package com.Libri.controllers;


import com.Libri.dtos.ChatDto;
import com.Libri.dtos.PrivateMessageDto;
import com.Libri.models.ChatMessage;
import com.Libri.services.ChatMessageService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.time.LocalTime;

@Controller
public class ChatController {


    @Autowired
    ChatMessageService chatMessageService;


    @Operation(description = "Chat aberto")
    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    public ChatMessage send(ChatDto chatDto){

        return chatMessageService.createMessage(chatDto);
    }

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Operation(description = "Chat privado")
    @MessageMapping("/private-message")
    public void sendPrivateMessage(PrivateMessageDto message) {
        messagingTemplate.convertAndSendToUser(
                message.to(),
                "/queue/messages",
                message
        );
    }
}
