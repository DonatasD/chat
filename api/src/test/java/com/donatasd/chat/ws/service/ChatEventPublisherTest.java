package com.donatasd.chat.ws.service;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.client.StandardWebSocketClient;
import org.springframework.web.util.UriComponentsBuilder;

import com.donatasd.chat.dto.MessageDTO;
import com.donatasd.chat.ws.event.ChatEventType;
import reactor.core.publisher.ReplayProcessor;

import static com.donatasd.chat.ws.event.ChatEventType.MESSAGE_CREATED;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ChatEventPublisherTest {

    @Autowired
    private ChatEventPublisher chatEventPublisher;

    @LocalServerPort
    private String port;

    @Test
    public void testMessageCreated() {
        var count = 10;
        var messages = messages(count);
        var client = new StandardWebSocketClient();
        ReplayProcessor<WebSocketMessage> output = ReplayProcessor.create(count);
        client.execute(url("/chat"), session -> session
                .receive()
                .thenMany(session.receive().take(count))
                .subscribeWith(output)
                .then());
        messages.forEach(messageDTO -> chatEventPublisher.publish(messageDTO));
        output.collectList().doOnSuccess(webSocketMessages -> {
            assertMessageTypes(webSocketMessages, MESSAGE_CREATED);
            Assert.assertEquals(webSocketMessages.size(), count);
        });

    }

    private URI url(String path) {
        return UriComponentsBuilder.fromUriString("ws://localhost")
                                   .port(port)
                                   .pathSegment("ws")
                                   .path(path).build().toUri();
    }

    private void assertMessageTypes(
            List<WebSocketMessage> webSocketMessages,
            ChatEventType chatEventType
    ) {
        webSocketMessages.forEach(webSocketMessage -> Assert.assertEquals(
                webSocketMessage.getType()
                                .toString(),
                chatEventType.toString()
        ));
    }

    private List<MessageDTO> messages(int count) {
        return Stream.generate(this::message)
                     .limit(count)
                     .collect(Collectors.toList());
    }

    private MessageDTO message() {
        return new MessageDTO(
                UUID.randomUUID().toString(),
                LocalDateTime.now(),
                UUID.randomUUID().toString()
        );
    }
}
