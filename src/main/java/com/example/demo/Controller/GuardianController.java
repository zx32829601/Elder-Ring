package com.example.demo.Controller;

import com.example.demo.Entity.Guardian;
import com.example.demo.Entity.GuardianDTO;
import com.example.demo.repository.GuardianRepository;
import com.example.demo.sevice.Guardianservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/get/{id}")
    public ResponseEntity<Guardian> get_guardian(@PathVariable("id") long id) {

        Optional<Guardian> guardian_data = guardianRepository.findById(id);
        if (guardian_data.isPresent()) {
            return new ResponseEntity<>(guardian_data.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
