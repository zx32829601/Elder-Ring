package com.example.demo.Controller;

import com.example.demo.Entity.Condition;
import com.example.demo.Entity.ConditionDTO;
import com.example.demo.Entity.ConditionDTO2;
import com.example.demo.Entity.Elder;
import com.example.demo.repository.ConditionRepository;
import com.example.demo.repository.Elderrepository;
import com.example.demo.sevice.ConditionService;
import com.google.firebase.FirebaseException;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Condition")
public class ConditionController {
    @Autowired
    ConditionRepository conditionRepository;

    @Autowired
    Elderrepository elderrepository;

    @Autowired
    ConditionService conditionService;

    @PostMapping("/create")
    public ResponseEntity<Condition> createcondition(@RequestBody ConditionDTO conditionDTO,
                                                     @RequestParam String token) throws FirebaseMessagingException {
        Condition condition = conditionService.createCondition(conditionDTO,token);
        return new ResponseEntity<>(condition, HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<List<Condition>> get_condition(@PathVariable("id") long id) {
        List<Condition> conditionList = conditionService.get_Condition(id);
        return ResponseEntity.ok(conditionList);
    }

    @PutMapping("/accept/{id}")
    public ResponseEntity<Condition> changecondition(@PathVariable("id") long id) {
        Condition condition = conditionService.changenotify(id);

        return new ResponseEntity<>(condition, HttpStatus.OK);
    }

    @GetMapping("/gettop/{id}")
    public Condition get_lastcondition(@PathVariable("id") long id) {
        Optional<Elder> elder_data = elderrepository.findById(id);
        Condition condition_data;
        if (elder_data.isPresent()) {
            condition_data = conditionRepository.findTopByOrderByIdDesc();

        } else {
            condition_data = new Condition();
        }
        return condition_data;

    }

    @GetMapping("/gettop10/{id}")
    public ResponseEntity<List<Condition>> get_top10condition(@PathVariable("id") long id) {
        List<Condition> conditionList = conditionService.get_top10condition(id);
        return ResponseEntity.ok(conditionList);
    }

    @GetMapping("/gettop2/{id}")
    public ResponseEntity<List<Condition>> get_top2condition(@PathVariable("id") long id) {
        Optional<Elder> elder_data = elderrepository.findById(id);
        List<Condition> condition_data;
        if (elder_data.isPresent()) {
            condition_data = conditionRepository.findFirst2ByElder(elder_data.get());

        } else {
            condition_data = new ArrayList<Condition>();
        }
        return ResponseEntity.ok(condition_data);
    }

    @PostMapping("/getbytime")
    public ResponseEntity<List<Condition>> getbytime_condition(@RequestBody ConditionDTO2 conditionDTO2) {
        Optional<Elder> elder_data = elderrepository.findById(conditionDTO2.getElderID());
        List<Condition> condition_data;
        if (elder_data.isPresent()) {
            condition_data = conditionRepository.findByElderAndTimestampBetween(conditionDTO2.getElderID(), conditionDTO2.getData(), conditionDTO2.getAfter());
            System.out.println(condition_data.toString());
        } else {
            condition_data = new ArrayList<Condition>();
        }
        return ResponseEntity.ok(condition_data);
    }

}

