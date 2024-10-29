package com.example.yukyuman.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.yukyuman.model.VacationEntry;
import com.example.yukyuman.service.EntryService;


@Controller
public class EntryController {

    @Autowired
    private EntryService entryService;

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
        
        entryService.storeVacationEntry(vacationEntry);

        return mav;
    }

}
