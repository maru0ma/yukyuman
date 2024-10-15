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
        // HTMLのファイル名を設定
        mav.setViewName("index");
        
        // DBからデータをとってくる
        List<VacationData> vacationDataList = vacationListRepositry.findAll();
 
        // ここで計算をする
        int daysRemainingSum = 0;
        for (VacationData vacationData : vacationDataList) {
            daysRemainingSum += vacationData.getDaysRemaining();
        }
        
        // タイムリーフにセットする
        mav.addObject("restDays", daysRemainingSum);
        
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
            return mav;
        }
        
        // Entity上で値を更新
        int currentDay = matchRecord.getDaysRemaining();
        matchRecord.setDaysRemaining(currentDay - 1);
        
        // 更新したEntityをDBに登録
        vacationListRepositry.saveAndFlush(matchRecord);

        return mav;
    }

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
