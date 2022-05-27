package com.example.demo.Controller;

import com.example.demo.Entity.Note;
import com.example.demo.sevice.FirebaseMessagingService;
import com.google.firebase.internal.FirebaseService;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class FirebaseController {
    @Autowired
    FirebaseMessagingService firebaseMessagingService;


    @RequestMapping("/send-notification")
    @ResponseBody
    public String sendNotification(@RequestBody Note note,
                                   @RequestParam String token) throws FirebaseMessagingException {
        return firebaseMessagingService.sendNotification(note, token);
    }
}
