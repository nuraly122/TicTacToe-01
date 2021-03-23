package org.example.web.controller;

import org.example.backend.model.Message;
import org.example.backend.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/", produces = {MediaType.APPLICATION_JSON_VALUE})
public class MainController {

    @Autowired
    private SimpMessagingTemplate template;

    @MessageMapping("/{room}")
    public void sendMessageToRooms (@DestinationVariable String room, Message message) {
        this.template.convertAndSend("/topic/"+room, new Message(message.getContent(), message.getButtonName()));
    }

    @MessageMapping("/auth")
    @SendTo("/topic/activity")
    public User getUser(User user) {
        return new User(user.getName(), user.getVictory());
    }


}
