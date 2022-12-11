package com.example.demo.Controller;

import com.example.demo.Entity.DeviceDTO;
import com.example.demo.Entity.Guardian;
import com.example.demo.Entity.GuardianAccountDTO;
import com.example.demo.Entity.GuardianDTO;
import com.example.demo.repository.GuardianRepository;
import com.example.demo.sevice.Guardianservice;
import com.google.firebase.messaging.FirebaseMessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.RegEx;
import java.util.Optional;


@RestController
@RequestMapping("/Guardian")
public class GuardianController {
    @Autowired
    GuardianRepository guardianRepository;

    @Autowired
    Guardianservice guardianservice;

    @PostMapping("/create")
    public ResponseEntity<Guardian> create_guardian(@RequestBody GuardianDTO guardianDTO) {
        Guardian guardian = guardianservice.createGuardian(guardianDTO);
        return new ResponseEntity<>(guardian, HttpStatus.OK);

    }

    @GetMapping("/verify/{account}/{password}")
    public ResponseEntity<Guardian> verify_guardian(@PathVariable("account")String account,@PathVariable("password") String password){
        Optional<Guardian> guardian_data = Optional.ofNullable(guardianRepository.findByaccount(account));
        if (guardian_data.isPresent()) {
            if(guardianservice.verifyaccount(guardian_data.get(),password)){
                return new ResponseEntity<>(guardian_data.get(),HttpStatus.OK);
            }else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }


        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @RequestMapping("/notify/{account}")
    public ResponseEntity<Guardian> notify_guardian(@PathVariable("account") String account) throws FirebaseMessagingException {
        Optional<Guardian> guardian_data = Optional.ofNullable(guardianRepository.findByaccount(account));
        if (guardian_data.isPresent()) {
            guardianservice.notifyGuardian(guardian_data.get());
            return  new ResponseEntity<>(null,HttpStatus.OK);
        }
        return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @GetMapping("/get/{account}")
    public ResponseEntity<Guardian> get_guardian(@PathVariable("account") String account) {

        Optional<Guardian> guardian_data = Optional.ofNullable(guardianRepository.findByaccount(account));
        if (guardian_data.isPresent()) {
            return new ResponseEntity<>(guardian_data.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PatchMapping("/create/device")
    public ResponseEntity<Guardian> create_device(@RequestBody DeviceDTO deviceDTO){
        Guardian guardian_data=guardianRepository.findByaccount(deviceDTO.getGuardian_account());

            guardian_data.setDevice_code(deviceDTO.getDevice_code());
            guardianRepository.save(guardian_data);
        return new ResponseEntity<>(guardian_data, HttpStatus.OK);
    }
}
