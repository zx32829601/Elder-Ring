package com.example.demo.Controller;

import com.example.demo.Entity.Elder;
import com.example.demo.Entity.ElderDTO;
import com.example.demo.repository.Elderrepository;
import com.example.demo.repository.GuardianRepository;
import com.example.demo.sevice.Elderservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/Elder")
public class ElderController {
    @Autowired
    Elderrepository elderrepository;
    @Autowired
    GuardianRepository guardianRepository;

    @Autowired
    Elderservice elderservice;

    @PostMapping("/create")
    public ResponseEntity<Elder> create_elder(@RequestBody ElderDTO elderDTO) {
        Elder elder = elderservice.createElder(elderDTO);
        return new ResponseEntity<>(elder, HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Elder> get_elder(@PathVariable("id") long id) {
        Optional<Elder> elder_data = elderrepository.findById(id);
        if (elder_data.isPresent()) {
            return new ResponseEntity<>(elder_data.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }


}
