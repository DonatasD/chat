package com.donatasd.chat.entity.message.service;

import org.springframework.stereotype.Service;

import com.donatasd.chat.ws.service.ChatEventPublisher;
import com.donatasd.chat.entity.message.dto.MessageCreateDTO;
import com.donatasd.chat.entity.message.dto.MessageDTO;
import com.donatasd.chat.entity.message.mapper.MessageMapper;
import com.donatasd.chat.entity.message.repository.MessageRepository;
import lombok.extern.log4j.Log4j2;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Log4j2
public class MessageService {

    private MessageRepository messageRepository;

    private MessageMapper messageMapper;

    private ChatEventPublisher publisher;

    public MessageService(
            MessageRepository messageRepository,
            MessageMapper messageMapper,
            ChatEventPublisher publisher
    ) {
        this.messageRepository = messageRepository;
        this.messageMapper = messageMapper;
        this.publisher = publisher;
    }

    public Mono<MessageDTO> create(MessageCreateDTO dto) {
        log.debug("Create {}", dto);
        return messageRepository.save(messageMapper.toModel(dto))
                                .map(messageMapper::toDto)
                                .doOnSuccess(this.publisher::publish);
    }

    public Flux<MessageDTO> findAll() {
        log.debug("FindAll");
        return messageRepository.findAll().map(messageMapper::toDto);
    }
}
