package com.example.demo.repository;

import com.example.demo.Entity.Elder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author zx328
 */
@Repository
public interface ElderRepository extends JpaRepository<Elder, Long> {
    

}
