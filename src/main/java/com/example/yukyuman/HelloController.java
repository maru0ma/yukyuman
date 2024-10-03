package com.example.yukyuman;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class HelloController {

    @Autowired
    VacationListRepositry vacationListRepositry;

    @Autowired
    UseDataRepositry useDataRepositry;
    
    @GetMapping("/")
    public ModelAndView index(ModelAndView mav) {
        mav.setViewName("index");
        mav.addObject("restDays",5);
        mav.addObject("exactlyGetDays",2);
        mav.addObject("disappearanceDays",1);
        List<VacationData> list = vacationListRepositry.findAll();
        System.out.println(list);
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
        
        VacationData vacationData = new VacationData();
        vacationData.setVacationType(vacationEntry.getVacationType());
        vacationData.setNumberOfDays(vacationEntry.getNumberOfDays());
        vacationData.setVacationDeadline(vacationEntry.getVacationDeadline());
        
        vacationListRepositry.saveAndFlush(vacationData);
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
        // System.out.println("登録しました");
        // System.out.println(useData.getVacationGetDate());
        // System.out.println(useData.getVacationType());
        // System.out.println(useData.getVacationSection());
        // System.out.println(useData.getVacationGetNote());
        mav.addObject("message","登録しました");

        // DBに登録するためのデータを組み立てる
        UseDataEntity useDataEntity = new UseDataEntity();
        useDataEntity.setVacationData(useData.getVacationGetDate());
        useDataEntity.setVacationType(useData.getVacationType());
        useDataEntity.setVacationSection(useData.getVacationSection());
        useDataEntity.setVacationGetNote(useData.getVacationGetNote());
        // DBに登録
        useDataRepositry.saveAndFlush(useDataEntity);

        // List<UseDataEntity> dbData = useDataRepositry.findAll();
        // System.out.println(dbData);

        return mav;
    }

    @GetMapping("/detailInf")
    public ModelAndView detailInf(ModelAndView mav) {
        mav.setViewName("detail_inf"); 
       
        List<VacationData> vacations = vacationListRepositry.findAll();
        mav.addObject("vacationList", vacations);

        List<UseDataEntity> dbData = useDataRepositry.findAll();
        System.out.println(dbData);
        return mav;
    } 

    
}
