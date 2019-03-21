package com.donatasd.chat.entity.message.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.donatasd.chat.entity.message.model.Message;

public interface MessageRepository extends ReactiveCrudRepository<Message, String> {

}
