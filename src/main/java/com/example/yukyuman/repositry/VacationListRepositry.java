package com.example.yukyuman.repositry;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.yukyuman.entity.VacationData;

@Repository
public interface VacationListRepositry extends JpaRepository<VacationData, Long> {
    
}
