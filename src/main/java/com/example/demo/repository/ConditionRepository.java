package com.example.demo.repository;

import com.example.demo.Entity.Condition;
import com.example.demo.Entity.Elder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;


@Repository
public interface ConditionRepository extends JpaRepository<Condition, Long> {


    List<Condition> findByElder(Elder elder);


    Condition findTopByOrderByIdDesc();


    List<Condition> findFirst10ByElder(Elder elder);

    @Query(value = "select * from condition_table  where  elder_id = :elder ORDER BY time_stamp desc limit 2", nativeQuery = true)
    List<Condition> findFirst2ByElder(long elder);

    @Query(value = "select * from condition_table  where (time_stamp between :data and :after) and elder_id = :elderId", nativeQuery = true)
    List<Condition> findByElderAndTimestampBetween(Long elderId, Date data, Date after);

}
