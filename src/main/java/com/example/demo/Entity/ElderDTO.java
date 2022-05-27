package com.example.demo.Entity;

import lombok.*;

@Data
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class ElderDTO {
    private String ID_number;
    private int age;
    private String name;
    private String precondition;
    private Long guardianID;
    private String birth;
    private String address;
}
