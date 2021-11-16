package cn.gzsendi.huangyj.controller;

import cn.gzsendi.huangyj.pojo.DutyPensonInfo;
import cn.gzsendi.huangyj.service.QueryInfoService;
import cn.gzsendi.huangyj.util.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/DutyPersonInfo")
public class DutyPersonInfoController {

    @Autowired
    private QueryInfoService queryInfoService;


    @GetMapping("/hasInfo")
    public ResponseEntity hasDutyPensonInfo(@RequestParam("date") String date, @RequestParam("province") String province){
        try {
            //查询当前月份所有省份的值班人员，判断该人员是否有人员信息和标签
            List<DutyPensonInfo> dutyPensonInfos = queryInfoService.hasInfo(date,province);

            return ResponseEntity.SUCCESS(dutyPensonInfos);
        }catch (Exception e){
            return ResponseEntity.ERROR().message("查询失败");

        }
    }



}
