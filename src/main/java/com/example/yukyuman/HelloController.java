package com.example.yukyuman;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
 
        return mav;
    } 


    
}
