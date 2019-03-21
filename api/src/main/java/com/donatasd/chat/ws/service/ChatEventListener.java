package com.donatasd.chat.ws.service;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.function.Consumer;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import com.donatasd.chat.ws.ChatWebSocketEvent;
import lombok.extern.log4j.Log4j2;
import reactor.core.publisher.FluxSink;

@Component
@Log4j2
public class ChatEventListener implements
        ApplicationListener<ChatWebSocketEvent>,
        Consumer<FluxSink<ChatWebSocketEvent>> {

    private final Executor executor;

    private final BlockingQueue<ChatWebSocketEvent> queue;

    public ChatEventListener(Executor executor) {
        this.executor = executor;
        this.queue = new LinkedBlockingQueue<>();
    }

    @Override
    public void onApplicationEvent(ChatWebSocketEvent event) {
        if (this.queue.offer(event)) {
            log.info("Successfully published {}", event);
        } else {
            log.error("Failed to publish {}", event);
        }
    }

    @Override
    public void accept(FluxSink<ChatWebSocketEvent> sink) {
        this.executor.execute(() -> {
            while (true) {
                try {
                    ChatWebSocketEvent event = queue.take();
                    sink.next(event);
                } catch (InterruptedException e) {
                    ReflectionUtils.rethrowRuntimeException(e);
                }
            }
        });
    }
}
