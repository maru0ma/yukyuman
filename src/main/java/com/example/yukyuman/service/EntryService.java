package com.example.yukyuman.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.yukyuman.entity.VacationData;
import com.example.yukyuman.model.VacationEntry;
import com.example.yukyuman.repositry.VacationListRepositry;

@Service
public class EntryService {

    @Autowired
    private VacationListRepositry vacationListRepositry;

    public void storeVacationEntry(VacationEntry vacationEntry){
        // POSTされたデータから値を取得してDB用にEntityを作成
        VacationData vacationData = new VacationData();
        vacationData.setVacationType(vacationEntry.getVacationType());
        vacationData.setNumberOfDays(vacationEntry.getNumberOfDays());
        vacationData.setVacationDeadline(vacationEntry.getVacationDeadline());
        vacationData.setDaysRemaining(vacationEntry.getNumberOfDays());
        
        // DBに登録
        vacationListRepositry.saveAndFlush(vacationData);
    };

}
