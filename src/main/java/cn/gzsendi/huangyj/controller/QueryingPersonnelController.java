package cn.gzsendi.huangyj.controller;

import cn.gzsendi.huangyj.pojo.DutyPensonInfo;
import cn.gzsendi.huangyj.service.QueryingPersonne;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class QueryingPersonnelController {
    @Autowired
    private QueryingPersonne queryingPersonne;

    @GetMapping("/judgement")
    public ResponseEntity isNotNull(@RequestParam("date") String date, @RequestParam("province") String province){


        List<DutyPensonInfo> notNull = queryingPersonne.isNotNull1(date,province);

        return ResponseEntity.ok(notNull);
    }



}
