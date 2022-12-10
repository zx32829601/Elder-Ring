package com.example.demo.Entity;

import lombok.*;


@Data
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class GuardianAccountDTO {
    private String account;
    private String password;

}
