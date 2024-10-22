package com.example.yukyuman.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import com.example.yukyuman.service.TopService;

@Controller
public class TopController {
    
    @Autowired
    private TopService topService;

    @GetMapping("/")
    public ModelAndView index(ModelAndView mav) {
        // HTMLのファイル名を設定
        mav.setViewName("index");

        int daysRemainingSum = topService.getDaysRemainingSum();

        // タイムリーフにセットする
        mav.addObject("restDays", daysRemainingSum);
        return mav;
    }

}
