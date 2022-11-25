package com.example.demo.sevice;

import com.example.demo.Entity.*;
import com.example.demo.repository.ConditionRepository;
import com.example.demo.repository.ElderRepository;
import com.example.demo.repository.GuardianRepository;

import com.google.firebase.messaging.FirebaseMessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ConditionService {
    Note note=new Note();

    @Autowired
    private ConditionRepository conditionRepository;

    @Autowired
    private ElderRepository elderrepository;
    @Autowired
    private FirebaseMessagingService firebaseMessagingService;
    @Autowired
    private GuardianRepository guardianRepository;

    private  int StayTime;

    public void change_state(String st){
        state=st;

    }
    private int notify_frequency;
   private String state = "normal";
    private String msg="your monitor target have condition";
    //createCondition
    public Condition createCondition(ConditionDTO request) throws FirebaseMessagingException {
        if (request.getHeartrhythm() < 60 || request.getHeartrhythm() >= 100) {
            change_state("abnormal");
            note.setSubject(msg);
            note.setContent(state+"heart rhythm is"+request.getHeartrhythm());
            notify_frequency = 10;



        } else if (notify_frequency != 0) {
            change_state("normal but have precondition");
            notify_frequency -= 1;
            note.setSubject(msg);
            note.setContent(state+"heart rhythm is"+request.getHeartrhythm());

        }else {
            change_state("normal");
        }


        Long elderId = request.getElderID();
        Optional<Elder> _elder = elderrepository.findById(elderId);



        Condition condition=new Condition(request.getHeartrhythm(),
                request.getBloody_oxy(), request.getLonggps(), request.getLatigps(), state, _elder.get());
        conditionRepository.save(condition);
        note.getData().put("key1",conditionRepository.findTopByOrderByIdDesc().getId().toString());
        if(notify_frequency!=0&&
                guardianRepository.findById(_elder.get().getGuardian().getId()).get().getDevice_code()!=null){
            firebaseMessagingService.sendNotification(note,guardianRepository.findById(_elder.get().getGuardian().getId()).get().getDevice_code());

        }
        return condition;

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

    public Condition update_notify(Long id) {
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
    //以Elder_ID查詢最新的兩個Condition
    public List<Condition> get_top2Condition(long id){
        Optional<Elder> elder_data = elderrepository.findById(id);
        Condition condition_data ;
        if (elder_data.isPresent()) {
            condition_data = (Condition) conditionRepository.findFirst2ByElder(id);
        } else {
            condition_data = new Condition();
        }
        return (List<Condition>) condition_data;
    }


    //傳入兩格condition並累計時間，再計算兩個經緯度如差異相加超過0.005則判定為不同位置，時間重置。
    public int PositionTime(List<Condition> condition_data){
        Condition one = condition_data.get(0);
        Condition two = condition_data.get(1);
        StayTime++;//應該加入Condition_Table,程式碼也要改!
        if((Math.abs(one.getLatigps() - two.getLatigps()) + Math.abs(one.getLonggps() - two.getLonggps())) >= 0.005) {
            StayTime = 0;
        }
        return StayTime;
    }

}
