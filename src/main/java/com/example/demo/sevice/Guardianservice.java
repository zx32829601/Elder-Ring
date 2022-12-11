package com.example.demo.sevice;

import com.example.demo.Entity.Guardian;
import com.example.demo.Entity.GuardianAccountDTO;
import com.example.demo.Entity.GuardianDTO;
import com.example.demo.Entity.Note;
import com.example.demo.repository.GuardianRepository;
import com.google.firebase.messaging.FirebaseMessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class Guardianservice {
    @Autowired
    private FirebaseMessagingService firebaseMessagingService;


    @Autowired
    private GuardianRepository guardianRepository;

    public Guardian createGuardian(GuardianDTO request) {
        return guardianRepository.save(new Guardian(request.getAccount(),request.getPassword(),request.getName(), request.getTelephone_number()));
    }

    public boolean verifyaccount(Guardian guardian, String password){
        if(guardian.getPassword().equals(password)){
            return true;
        }
        else {
            return  false;
        }

    }
    public void notifyGuardian(Guardian guardian) throws FirebaseMessagingException {
        Note note=new Note();
        note.setSubject("Your monitor target may have fallen");
        note.setContent("!Notice!");
        firebaseMessagingService.sendNotification(note,guardian.getDevice_code());

    }
}
