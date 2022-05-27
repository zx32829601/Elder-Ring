package com.example.demo.sevice;

import com.example.demo.Entity.Condition;
import com.example.demo.Entity.ConditionDTO;
import com.example.demo.Entity.Elder;
import com.example.demo.Entity.Note;
import com.example.demo.repository.ConditionRepository;
import com.example.demo.repository.Elderrepository;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ConditionService {


    @Autowired
    private ConditionRepository conditionRepository;

    @Autowired
    private Elderrepository elderrepository;
    @Autowired
    private FirebaseMessagingService firebaseMessagingService;
    public void change_state(String st){
        state=st;

    }
    private int notify_frequency;
    String state = "normal";
    String msg="your monitor target have condition";
    //createCondition
    public Condition createCondition(ConditionDTO request,String token) throws FirebaseMessagingException {
        if (request.getHeartrhythm() < 60 || request.getHeartrhythm() >= 100) {
            change_state("abnormal");
            Note note=new Note(msg,state+request.getHeartrhythm());
            firebaseMessagingService.sendNotification(note,token);

            notify_frequency = 10;
        } else if (notify_frequency != 0) {
            change_state("normal but have precondition");
            notify_frequency -= 1;
            Note note=new Note(msg,state+request.getHeartrhythm());
            firebaseMessagingService.sendNotification(note,token);
        }else {
            change_state("normal");
        }
        Long elderId = request.getElderID();
        Optional<Elder> _elder = elderrepository.findById(elderId);
        Condition condition=new Condition(request.getHeartrhythm(),
                request.getBloody_oxy(), request.getLonggps(), request.getLatigps(), state, _elder.get());
        return conditionRepository.save(condition);

    }

    public List<Condition> get_Condition(long id) {
        Optional<Elder> elder_data = elderrepository.findById(id);
        List<Condition> condition_data;
        if (elder_data.isPresent()) {
            condition_data = conditionRepository.findByElder(elder_data.get());
            System.out.println(condition_data.toString());
        } else {
            condition_data = new ArrayList<Condition>();
        }
        return condition_data;
    }

    public Condition changenotify(long id) {
        Optional<Condition> condition = conditionRepository.findById(id);
        condition.get().setNotify_accept(true);
        notify_frequency = 0;
        return conditionRepository.save(condition.get());

    }

    public List<Condition> get_top10condition(long id) {
        Optional<Elder> elder_data = elderrepository.findById(id);
        List<Condition> condition_data;
        if (elder_data.isPresent()) {
            condition_data = conditionRepository.findFirst10ByElder(elder_data.get());
        } else {
            condition_data = new ArrayList<Condition>();
        }
        return condition_data;
    }


}
