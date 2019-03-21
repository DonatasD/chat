package com.donatasd.chat.ws.event;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public abstract class ChatEvent<T> {

    private ChatEventType type;
    private LocalDateTime timestamp;
    private T content;

    public ChatEvent(ChatEventType type, T content) {
        this.type = type;
        this.timestamp = LocalDateTime.now();
        this.content = content;
    }


}
