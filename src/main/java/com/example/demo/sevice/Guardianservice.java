package com.example.demo.sevice;

import com.example.demo.Entity.Guardian;
import com.example.demo.Entity.GuardianAccountDTO;
import com.example.demo.Entity.GuardianDTO;
import com.example.demo.Entity.Note;
import com.example.demo.repository.GuardianRepository;
import com.google.firebase.messaging.FirebaseMessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.example.demo.NotifyHandler;

@Service
public class Guardianservice implements NotifyHandler{
    @Autowired
    private FirebaseMessagingService firebaseMessagingService;


    @Autowired
    private GuardianRepository guardianRepository;

    public Guardian createGuardian(GuardianDTO request) {
        return guardianRepository.save(new Guardian(request.getAccount(),request.getPassword(),request.getName(), request.getTelephone_number()));
    }

    public ResponseEntity<Guardian> verify_Account(Guardian guardian, String password){
        if(guardian.getPassword().equals(password)){
            return new ResponseEntity<>(guardian, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
    public void notifyGuardian(Guardian guardian) throws FirebaseMessagingException {
        Note note=new Note();
        note.setSubject("Your monitor target may have fallen");
        note.setContent("!Notice!");
        firebaseMessagingService.sendNotification(note,guardian.getDevice_code());

    }

    @Override
    public ResponseEntity<Guardian> verify_Account(String account, String password) {
        Guardian guardian=guardianRepository.findByaccount(account);
        if(guardian.getPassword().equals(password)){
            return new ResponseEntity<>(guardian, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
