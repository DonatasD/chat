package com.donatasd.chat.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.HandlerMapping;
import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping;
import org.springframework.web.reactive.socket.server.support.WebSocketHandlerAdapter;

import com.donatasd.chat.ws.ChatWebSocketEvent;
import com.donatasd.chat.ws.service.ChatWebSocketHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import reactor.core.publisher.Flux;
import reactor.core.publisher.UnicastProcessor;

import static java.util.Collections.singletonMap;

@Configuration
@Log4j2
public class WebSocketConfig {

    @Bean
    public UnicastProcessor<ChatWebSocketEvent> eventPublisher() {
        return UnicastProcessor.create();
    }

    @Bean
    public Flux<ChatWebSocketEvent> events(UnicastProcessor<ChatWebSocketEvent> eventPublisher) {
        return eventPublisher
                .replay(0)
                .autoConnect();
    }

    @Bean
    HandlerMapping handlerMapping(UnicastProcessor<ChatWebSocketEvent> eventPublisher, Flux<ChatWebSocketEvent> events, ObjectMapper objectMapper) {
        return new SimpleUrlHandlerMapping() {
            {
                setUrlMap(singletonMap("/ws/chat", new ChatWebSocketHandler(objectMapper,eventPublisher, events)));
                setOrder(HIGHEST_PRECEDENCE);
            }
        };
    }

    @Bean
    WebSocketHandlerAdapter webSocketHandlerAdapter() {
        return new WebSocketHandlerAdapter();
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}
