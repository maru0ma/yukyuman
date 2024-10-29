package com.example.yukyuman.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.yukyuman.model.UseData;
import com.example.yukyuman.service.UseService;


@Controller
public class UseController {

    @Autowired
    private UseService useService;

    @GetMapping("/use")
    public ModelAndView use(ModelAndView mav) {
        mav.setViewName("use");
        return mav;
    } 

    @PostMapping("/use")
    public ModelAndView postUse(ModelAndView mav, @ModelAttribute UseData useData) {
        // HTMLのファイル名を設定
        mav.setViewName("use");
        
        // 登録ボタン押下時にメッセージを表示
        mav.addObject("message","登録しました");

        useService.updateDatabase(useData);

        return mav;
    }

}
