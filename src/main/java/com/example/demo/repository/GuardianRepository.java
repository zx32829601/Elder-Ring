package com.example.demo.repository;

import com.example.demo.Entity.Condition;
import com.example.demo.Entity.Elder;
import com.example.demo.Entity.Guardian;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GuardianRepository extends JpaRepository<Guardian, Long> {


}
