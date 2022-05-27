package com.example.demo.Entity;

import lombok.*;

@Data
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class ConditionDTO {

    private int heartrhythm;
    private float bloody_oxy;
    private double longgps;
    private double latigps;
    private Long elderID;
}
