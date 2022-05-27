package com.example.demo.repository;

import com.example.demo.Entity.Elder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Elderrepository extends JpaRepository<Elder, Long> {
    

}
