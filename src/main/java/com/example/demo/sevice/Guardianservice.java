package com.example.demo.sevice;

import com.example.demo.Entity.Guardian;
import com.example.demo.Entity.GuardianDTO;
import com.example.demo.repository.GuardianRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class Guardianservice {

    @Autowired
    private GuardianRepository guardianRepository;

    public Guardian createGuardian(GuardianDTO request) {
        return guardianRepository.save(new Guardian(request.getName(), request.getTelephone_number()));
    }

   
}
