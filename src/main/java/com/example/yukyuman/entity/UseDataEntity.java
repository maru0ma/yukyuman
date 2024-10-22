package com.example.yukyuman.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "use_data")
public class UseDataEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private long vacationGetId;

    @Column(nullable = false)
    private String vacationData;

    @Column(nullable = false)
    private String vacationType;

    @Column(nullable = true)
    private String vacationSection;

    @Column(nullable = true)
    private String vacationGetNote;
    
}
