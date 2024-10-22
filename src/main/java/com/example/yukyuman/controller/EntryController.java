package com.example.yukyuman.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.yukyuman.entity.VacationData;
import com.example.yukyuman.model.VacationEntry;
import com.example.yukyuman.repositry.VacationListRepositry;


@Controller
public class EntryController {

    @Autowired
    private VacationListRepositry vacationListRepositry;

    @GetMapping("/entry")
    public ModelAndView entry(ModelAndView mav) {
        mav.setViewName("entry");
        mav.addObject("message","");
        return mav;
    } 

    @PostMapping("/entry")
    public ModelAndView postEntry(ModelAndView mav, @ModelAttribute VacationEntry vacationEntry) {
        // HTMLのファイル名を設定
        mav.setViewName("entry");
        
        // 登録ボタン押下時にメッセージを表示
        mav.addObject("message","登録しました");
        
        // POSTされたデータから値を取得してDB用にEntityを作成
        VacationData vacationData = new VacationData();
        vacationData.setVacationType(vacationEntry.getVacationType());
        vacationData.setNumberOfDays(vacationEntry.getNumberOfDays());
        vacationData.setVacationDeadline(vacationEntry.getVacationDeadline());
        vacationData.setDaysRemaining(vacationEntry.getNumberOfDays());
        
        // DBに登録
        vacationListRepositry.saveAndFlush(vacationData);
        return mav;
    }

}
