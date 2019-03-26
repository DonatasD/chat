package com.donatasd.chat.ws.service;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.WebSocketSession;

import com.donatasd.chat.ws.ChatWebSocketEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.UnicastProcessor;

@Log4j2
public class ChatWebSocketHandler implements WebSocketHandler {

    private final ObjectMapper objectMapper;

    private final UnicastProcessor<ChatWebSocketEvent> publisher;

    private final Flux<String> events;

    public ChatWebSocketHandler(
            ObjectMapper objectMapper,
            UnicastProcessor<ChatWebSocketEvent> publisher,
            Flux<ChatWebSocketEvent> events
    ) {
        this.objectMapper = objectMapper;
        this.publisher = publisher;
        this.events = Flux.from(events).map(this::toEvent);
    }

    @Override
    public Mono<Void> handle(WebSocketSession webSocketSession) {

        ChatEventListener chatEventListener = new ChatEventListener(publisher);

        webSocketSession.receive()
                        .map(WebSocketMessage::getPayloadAsText)
                        .map(this::toEvent)
                        .subscribe(chatEventListener::onApplicationEvent);

        return webSocketSession.send(events.map(webSocketSession::textMessage));
    }

    private ChatWebSocketEvent toEvent(String json) {
        try {
            return objectMapper.readValue(json, ChatWebSocketEvent.class);
        } catch (IOException e) {
            throw new RuntimeException("Invalid JSON:" + json, e);
        }
    }

    private String toEvent(ChatWebSocketEvent chatWebSocketEvent) {
        try {
            return objectMapper.writeValueAsString(chatWebSocketEvent.getSource());
        } catch (JsonProcessingException e) {
            log.error("Failed to serialize {}", e);
            throw new RuntimeException(e);
        }
    }
}
