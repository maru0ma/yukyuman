package com.example.yukyuman.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.yukyuman.entity.VacationData;
import com.example.yukyuman.repositry.VacationListRepositry;

@Service
public class TopService {
    
    @Autowired
    private VacationListRepositry vacationListRepositry;
    
    public int getDaysRemainingSum() {
        // DBからデータをとってくる
        List<VacationData> vacationDataList = vacationListRepositry.findAll();
 
        // ここで計算をする
        int daysRemainingSum = 0;
        for (VacationData vacationData : vacationDataList) {
            daysRemainingSum += vacationData.getDaysRemaining();
        }
        
        return daysRemainingSum;
    }
    
}
