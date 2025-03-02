package com.example.yukyuman.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.yukyuman.entity.UseDataEntity;
import com.example.yukyuman.entity.VacationData;
import com.example.yukyuman.model.UseData;
import com.example.yukyuman.repositry.UseDataRepositry;
import com.example.yukyuman.repositry.VacationListRepositry;

@Service
public class UseService {
    @Autowired
    private VacationListRepositry vacationListRepositry;
    
    @Autowired
    private UseDataRepositry useDataRepositry;

    public void updateDatabase(UseData useData) {
        // POSTされたデータから値を取得してDB用にEntityを作成
        UseDataEntity useDataEntity = new UseDataEntity();
        useDataEntity.setVacationData(useData.getVacationGetDate());
        useDataEntity.setVacationType(useData.getVacationType());
        useDataEntity.setVacationSection(useData.getVacationSection());
        useDataEntity.setVacationGetNote(useData.getVacationGetNote());
        
        // DBに登録
        useDataRepositry.saveAndFlush(useDataEntity);
        
        // 有給休暇一覧DBから現在の値を取得
        List<VacationData> vacations = vacationListRepositry.findAll();
        
        /*
            更新するレコードの条件
            1.休暇取得が同じ
            2.有効期限内である
            3.残り日数がある
            4.上記の中で一番古いもの1件分 ※あとで実装
         */
        VacationData matchRecord = null;
        for (VacationData vacationData : vacations) {
            String vacationType = vacationData.getVacationType();
            String vacationDeadline = vacationData.getVacationDeadline();
            
            // 休暇取得が同じでない場合はスキップ
            if (!vacationType.equals(useData.getVacationType())) {
                continue;
            }
            // 有効期限外の場合はスキップ
            if (useData.getVacationGetDate().compareTo(vacationDeadline) > 0) {
                continue;
            }
            // 残り日数がない場合はスキップ
            if (!(vacationData.getDaysRemaining() > 0)) {
                continue;
            }
            matchRecord = vacationData;
            break;
        }
        
        // todo:
        // matchRecord はリストにして、一番古い日付のレコード1件分を抽出する実装をする
        
        // この段階で null の場合はDB登録処理をスキップ
        if (matchRecord == null) {
            return;
        }
        
        // Entity上で値を更新
        int currentDay = matchRecord.getDaysRemaining();
        matchRecord.setDaysRemaining(currentDay - 1);
        
        // 更新したEntityをDBに登録
        vacationListRepositry.saveAndFlush(matchRecord);
    };
    
}
