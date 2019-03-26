package com.donatasd.chat.ws.service;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import com.donatasd.chat.dto.MessageDTO;

import static com.donatasd.chat.ws.ChatWebSocketEventFactory.createdMessage;

@Service
public class ChatEventPublisher {

    private final ApplicationEventPublisher applicationEventPublisher;

    public ChatEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void publish(MessageDTO messageDTO) {
        applicationEventPublisher.publishEvent(createdMessage(messageDTO));
    }

}
