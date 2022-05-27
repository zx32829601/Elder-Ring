package com.example.demo.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.websocket.Session;

@Getter
@Setter
public class WebSocketClient {
    private Session session;
    private String uri;
}
