package com.donatasd.chat.ws.service;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.WebSocketSession;

import com.donatasd.chat.ws.ChatWebSocketEvent;
import com.donatasd.chat.ws.service.ChatEventListener;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@Log4j2
public class ChatWebSocketHandler implements WebSocketHandler {

    private final ObjectMapper objectMapper;

    private final ChatEventListener listener;

    public ChatWebSocketHandler(
            ObjectMapper objectMapper,
            ChatEventListener listener
    ) {
        this.objectMapper = objectMapper;
        this.listener = listener;
    }

    @Override
    public Mono<Void> handle(WebSocketSession webSocketSession) {
        Flux<ChatWebSocketEvent> publish = Flux
                .create(listener)
                .share();

        Flux<WebSocketMessage> messageFlux = publish
                .map(event -> {
                    try {
                        return objectMapper.writeValueAsString(event.getSource());
                    } catch (JsonProcessingException e) {
                        log.error("Failed to serialize {}", e);
                        throw new RuntimeException(e);
                    }
                })
                .map(message -> {
                    log.info("Sending {}", message);
                    return webSocketSession.textMessage(message);
                });
        return webSocketSession.send(messageFlux);
    }
}
