package com.example.demo.Controller;

import com.example.demo.sevice.WebSocketService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/websocket")
public class WebSocketController {
    @GetMapping(value = "/pushone")
    public void pushone() {
        WebSocketService.sendMessage("badao", "Welcome to  Chatroom!!");

    }
}