package com.donatasd.chat.ws.service;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.donatasd.chat.ws.ChatWebSocketEvent;
import lombok.extern.log4j.Log4j2;
import reactor.core.publisher.UnicastProcessor;

@Component
@Log4j2
public class ChatEventListener implements ApplicationListener<ChatWebSocketEvent> {

    private final UnicastProcessor<ChatWebSocketEvent> publisher;

    public ChatEventListener(UnicastProcessor<ChatWebSocketEvent> publisher) {
        this.publisher = publisher;
    }

    @Override
    public void onApplicationEvent(ChatWebSocketEvent event) {
        log.info("Publishing {}", event);
        publisher.onNext(event);
    }
}
