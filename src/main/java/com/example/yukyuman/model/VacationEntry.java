package com.example.yukyuman.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VacationEntry {
    private String vacationType;
    private int numberOfDays;
    private String vacationDeadline;
}
