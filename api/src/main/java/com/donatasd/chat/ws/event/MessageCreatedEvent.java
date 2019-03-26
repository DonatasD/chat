package com.donatasd.chat.ws.event;

import com.donatasd.chat.dto.MessageDTO;

import static com.donatasd.chat.ws.event.ChatEventType.MESSAGE_CREATED;

public class MessageCreatedEvent extends ChatEvent<MessageDTO> {

    public MessageCreatedEvent(MessageDTO content) {
        super(MESSAGE_CREATED, content);
    }
}
