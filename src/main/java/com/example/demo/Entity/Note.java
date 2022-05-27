package com.example.demo.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Data
@Getter
@Setter
@AllArgsConstructor
public class Note {
    private String subject;
    private String content;


}