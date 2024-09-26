package com.example.yukyuman;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class HelloController {
    
    @GetMapping("/")
    public ModelAndView index(ModelAndView mav) {
        mav.setViewName("index");
        mav.addObject("restDays",5);
        mav.addObject("exactlyGetDays",2);
        mav.addObject("disappearanceDays",1);
        return mav;
    }

    @GetMapping("/entry")
    public ModelAndView entry(ModelAndView mav) {
        mav.setViewName("entry");
        mav.addObject("message","");
        return mav;
    } 

    @PostMapping("/entry")
    public ModelAndView postEntry(ModelAndView mav, @ModelAttribute VacationEntry vacationEntry) {
        mav.setViewName("entry");
        System.out.println("登録しました");
        System.out.println(vacationEntry.getVacationType());
        System.out.println(vacationEntry.getNumberOfDays());
        System.out.println(vacationEntry.getVacationDeadline());
        mav.addObject("message","登録しました");
        return mav;
    }

    @GetMapping("/use")
    public ModelAndView use(ModelAndView mav) {
        mav.setViewName("use");
        return mav;
    } 

    @PostMapping("/use")
    public ModelAndView postUse(ModelAndView mav, @ModelAttribute UseData useData) {
        mav.setViewName("use");
        System.out.println("登録しました");
        System.out.println(useData.getVacationGetDate());
        System.out.println(useData.getVacationType());
        System.out.println(useData.getVacationSection());
        System.out.println(useData.getVacationGetNote());
        mav.addObject("message","登録しました");
        return mav;
    }

    @GetMapping("/detailInf")
    public ModelAndView detailInf(ModelAndView mav) {
        mav.setViewName("detail_inf"); 
        return mav;
    } 

    
}
