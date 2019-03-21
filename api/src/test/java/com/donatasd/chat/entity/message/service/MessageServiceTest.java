package com.donatasd.chat.entity.message.service;

import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.donatasd.chat.entity.message.dto.MessageCreateDTO;
import com.donatasd.chat.entity.message.dto.MessageDTO;
import com.donatasd.chat.entity.message.model.Message;
import com.donatasd.chat.entity.message.repository.MessageRepository;
import com.donatasd.chat.factory.MessageFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static java.util.stream.Collectors.toList;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MessageServiceTest {

    @Autowired
    private MessageService messageService;

    @MockBean
    private MessageRepository messageRepository;

    private int messageNumber = 3;

    private Message message;

    @Before
    public void setUp() {
        message = MessageFactory.create();
        var messageList = Stream.generate(MessageFactory::create)
                                .limit(messageNumber)
                                .collect(toList());

        Mockito.when(this.messageRepository.save(Mockito.any(Message.class)))
               .thenReturn(Mono.just(message));

        Mockito.when(this.messageRepository.findAll())
               .thenReturn(Flux.fromIterable(messageList));
    }

    @Test
    public void testShouldCreate() {
        Predicate<MessageDTO> messageDTOMatcher = messageDTO ->
                Objects.nonNull(messageDTO.getCreatedBy()) &&
                Objects.nonNull(messageDTO.getCreatedDate()) &&
                messageDTO.getContent().equals(message.getContent());

        var messageCreateDTO = new MessageCreateDTO(message.getContent());
        StepVerifier.create(messageService.create(messageCreateDTO))
                    .expectNextMatches(messageDTOMatcher)
                    .expectComplete()
                    .verify();
    }

    @Test
    public void testShouldFindAllMessages() {
        StepVerifier.create(messageService.findAll())
                    .expectNextCount(messageNumber)
                    .expectComplete()
                    .verify();
    }
}
