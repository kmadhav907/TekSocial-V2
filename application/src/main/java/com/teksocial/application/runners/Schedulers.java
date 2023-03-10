package com.teksocial.application.runners;


import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class Schedulers {
    private static final String WS_MESSAGE_TRANSFER_DESTINATION = "/topic/greetings";
    private final SimpMessagingTemplate simpMessagingTemplate;

    public Schedulers(SimpMessagingTemplate simpMessagingTemplate) {
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    public void scheduleTask(){
     simpMessagingTemplate.convertAndSend(WS_MESSAGE_TRANSFER_DESTINATION, "Hello madam");
    }
}

