package com.donatasd.chat.factory;

import java.time.LocalDateTime;

import com.donatasd.chat.model.Message;
import lombok.Builder;

import static com.donatasd.chat.utils.FactoryUtils.alphanumericString;
import static com.donatasd.chat.utils.FactoryUtils.uuid;

public class MessageFactory {

    @Builder()
    public static Message newMessage(
            String id,
            String content,
            LocalDateTime createdAt,
            String createdBy
    ) {
        return new Message(id, content, createdAt, createdBy);
    }

    public static Message create() {
        return MessageFactory.builder()
                             .id(uuid())
                             .content(alphanumericString())
                             .createdAt(LocalDateTime.now())
                             .createdBy(uuid())
                             .build();
    }
}
