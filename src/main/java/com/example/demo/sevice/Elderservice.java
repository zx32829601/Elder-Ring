package com.example.demo.sevice;


import com.example.demo.Entity.Elder;
import com.example.demo.Entity.ElderDTO;
import com.example.demo.Entity.Guardian;
import com.example.demo.repository.ElderRepository;
import com.example.demo.repository.GuardianRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class Elderservice {

    @Autowired
    private ElderRepository elderrepository;

    @Autowired
    private GuardianRepository guardianRepository;

    public Elder createElder(ElderDTO request) {
        Long guardianId = request.getGuardianID();
        Optional<Guardian> _guardian = guardianRepository.findById(guardianId);
        return elderrepository.save(new Elder(request.getID_number(), request.getAge(), request.getName(),
                request.getBirth(), request.getPrecondition(), request.getAddress(), _guardian.get()));
    }
}
