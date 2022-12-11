package com.example.demo.Entity;

import lombok.*;

@Data
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class DeviceDTO {
    private String guardian_account;
    private String device_code;
}
