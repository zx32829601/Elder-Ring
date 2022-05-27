package com.example.demo.Entity;

import lombok.*;

@Data
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class DeviceDTO {
    private Long guardian_id;
    private String device_code;
}
