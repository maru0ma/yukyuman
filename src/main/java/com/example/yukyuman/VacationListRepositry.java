package com.example.yukyuman;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VacationListRepositry extends JpaRepository<VacationData, Long> {
    
}
