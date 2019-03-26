package com.donatasd.chat.ws;

import com.donatasd.chat.dto.MessageDTO;
import com.donatasd.chat.ws.event.ChatEvent;
import com.donatasd.chat.ws.event.MessageCreatedEvent;

public class ChatWebSocketEventFactory {

    public static ChatWebSocketEvent<ChatEvent<MessageDTO>> createdMessage(MessageDTO messageDTO) {
        return new ChatWebSocketEvent(new MessageCreatedEvent(messageDTO));
    }

}
