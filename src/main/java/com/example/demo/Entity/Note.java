package com.example.demo.Entity;

import lombok.*;

import javax.management.Notification;
import java.util.HashMap;
import java.util.Map;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Note {
    private String subject;
    private String content;
    private Map<String, String> data=new HashMap<>();
    private String image;
    private String title;
    private String body;
    private Notification notification;
    
}

