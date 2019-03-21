package com.donatasd.chat.entity.message.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.donatasd.chat.entity.message.dto.MessageCreateDTO;
import com.donatasd.chat.entity.message.dto.MessageDTO;
import com.donatasd.chat.entity.message.service.MessageService;
import lombok.extern.log4j.Log4j2;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@Log4j2
public class MessageController {

    private MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping(value = "/api/messages")
    public Mono<MessageDTO> create(@RequestBody MessageCreateDTO dto) {
        log.debug("Create {}", dto);
        return messageService.create(dto);
    }

    @GetMapping(value = "/api/messages")
    public Flux<MessageDTO> findAll() {
        log.debug("FindAll");
        return messageService.findAll();
    }
}
