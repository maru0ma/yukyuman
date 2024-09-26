package com.example.yukyuman;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "vacation_list")
public class VacationData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private long vacationId;

    @Column(nullable = false)
    private String vacationType;

    @Column(nullable = false)
    private int numberOfDays;

    @Column(nullable = false)
    private int daysRemaining;

    @Column(nullable = false)
    private String vacationDeadline;
    
}
