package com.example.demo.Entity;

import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class ConditionDTO2 {
    private Long elderID;
    private Date data;
    private Date after;
}
