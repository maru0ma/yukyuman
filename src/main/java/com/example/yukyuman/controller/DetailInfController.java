package com.example.yukyuman.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.yukyuman.entity.UseDataEntity;
import com.example.yukyuman.entity.VacationData;
import com.example.yukyuman.repositry.UseDataRepositry;
import com.example.yukyuman.repositry.VacationListRepositry;


@Controller
public class DetailInfController {

    @Autowired
    private VacationListRepositry vacationListRepositry;

    @Autowired
    private UseDataRepositry useDataRepositry;

    @GetMapping("/detailInf")
    public ModelAndView detailInf(ModelAndView mav) {
        // HTMLのファイル名を設定
        mav.setViewName("detail_inf"); 
       
        // DBからデータの一覧を取得して、ThymeleafでHTMLに設定
        List<VacationData> vacations = vacationListRepositry.findAll();
        mav.addObject("vacationList", vacations);

        // DBからデータの一覧を取得して、ThymeleafでHTMLに設定
        List<UseDataEntity> dbData = useDataRepositry.findAll();
        mav.addObject("useData", dbData);
        
        return mav;
    }
    
}
