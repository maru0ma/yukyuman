package com.example.yukyuman;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UseDataRepositry extends JpaRepository<UseDataEntity, Long> {
    
}
