package ru.geekbrains.clientui.chat;

import lombok.AllArgsConstructor;

import java.security.Principal;

@AllArgsConstructor
public class ChatPrincipal implements Principal {

    private String name;

    @Override
    public String getName() {
        return name;
    }
}
