package com.example.yukyuman.repositry;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.yukyuman.entity.UseDataEntity;

@Repository
public interface UseDataRepositry extends JpaRepository<UseDataEntity, Long> {
    
}
