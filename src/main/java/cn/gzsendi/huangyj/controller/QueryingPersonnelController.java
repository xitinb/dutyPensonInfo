package cn.gzsendi.huangyj.controller;

import cn.gzsendi.huangyj.pojo.DutyPensonInfo;
import cn.gzsendi.huangyj.service.QueryingPersonne;
import cn.gzsendi.huangyj.util.FilePortUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

@RestController
public class QueryingPersonnelController {
    @Autowired
    private QueryingPersonne queryingPersonne;

    @GetMapping("/judgement")
    public ResponseEntity isNotNull(@RequestParam("date") String date, @RequestParam("province") String province, HttpServletResponse response){
        //从数据库查询出来的结果集
        List<DutyPensonInfo> notNull = queryingPersonne.isNotNull1(date,province);

        return ResponseEntity.ok(notNull);
    }

    @GetMapping("/exportExcel")
    public void exportExcel(@RequestParam("date") String date, @RequestParam("province") String province, HttpServletResponse response){
        //导出的表格名称
        String title = province + "_" + date;
        //表中第一行表头字段
        String [] headers ={"月份","省份","名称","号码","是否有人员信息","是否有专业类或技术类的标签"};

        //从数据库查询出来的结果集
        List<DutyPensonInfo> notNull = queryingPersonne.isNotNull1(date,province);

        //具体需要写入excel需要的那些字段，这些字段从PprojectSalary类中拿，也就是上面的实际数据结果集的泛型
        List<String> list = Arrays.asList("date","province","name","mobile","hasInfo","hasMajorOrTechnologyTag");

        try {
            FilePortUtil.exportExcel(response,title,headers,notNull,list);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
