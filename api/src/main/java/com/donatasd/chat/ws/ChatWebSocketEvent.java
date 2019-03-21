package com.donatasd.chat.ws;

import org.springframework.context.ApplicationEvent;

import com.donatasd.chat.ws.event.ChatEvent;

public class ChatWebSocketEvent<T> extends ApplicationEvent {

    public ChatWebSocketEvent(ChatEvent<T> chatEvent) {
        super(chatEvent);
    }
}
