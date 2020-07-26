package ru.geekbrains.clientui.chat;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ChatMessage {

    private String username;
    private String message;
    private LocalDateTime dateTime;

    public ChatMessage() {
        this.dateTime = LocalDateTime.now();
    }

    public ChatMessage(String username, String message) {
        this();
        this.username = username;
        this.message = message;
    }
}
