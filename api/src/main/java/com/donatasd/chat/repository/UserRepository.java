package com.donatasd.chat.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.donatasd.chat.model.User;
import reactor.core.publisher.Mono;

public interface UserRepository extends ReactiveCrudRepository<User, String> {
    Mono<User> findByEmail(String email);
}
