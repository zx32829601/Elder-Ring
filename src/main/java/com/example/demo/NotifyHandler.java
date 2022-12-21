package com.example.demo;

import com.example.demo.Entity.Guardian;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

public interface NotifyHandler {
    public ResponseEntity<Guardian> verify_Account(String account,  String password);
}
