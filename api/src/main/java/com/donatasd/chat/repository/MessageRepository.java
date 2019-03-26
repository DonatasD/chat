package com.donatasd.chat.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.donatasd.chat.model.Message;

public interface MessageRepository extends ReactiveCrudRepository<Message, String> {

}
