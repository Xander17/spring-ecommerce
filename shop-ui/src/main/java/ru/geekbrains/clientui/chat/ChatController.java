package ru.geekbrains.clientui.chat;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageType;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ChatController {

    private final SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/send_message")
    public void messageReceiver(@Payload ChatMessage message, SimpMessageHeaderAccessor headerAccessor) {
        if (headerAccessor.getUser() == null) {
            log.error("No user to send message");
            return;
        }
        messagingTemplate.convertAndSendToUser(
                headerAccessor.getUser().getName(),
                "/chat_out/receive_message",
                new ChatMessage("Server", "Answer to " + message.getMessage()),
                createHeaders(headerAccessor.getSessionId()));
    }

    private MessageHeaders createHeaders(String sessionId) {
        SimpMessageHeaderAccessor headerAccessor = SimpMessageHeaderAccessor.create(SimpMessageType.MESSAGE);
        headerAccessor.setSessionId(sessionId);
        headerAccessor.setLeaveMutable(true);
        return headerAccessor.getMessageHeaders();
    }
}
