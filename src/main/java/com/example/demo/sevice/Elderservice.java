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
        String account=request.getGuardian_account();
        Optional<Guardian> _guardian = Optional.ofNullable(guardianRepository.findByaccount(account));
        return elderrepository.save(new Elder(request.getID_number(), request.getAge(), request.getName(),
                request.getBirth(), request.getPrecondition(), request.getAddress(), _guardian.get()));
    }
}
