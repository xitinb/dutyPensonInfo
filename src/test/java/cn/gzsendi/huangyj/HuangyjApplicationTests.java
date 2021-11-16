package cn.gzsendi.huangyj;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


class HuangyjApplicationTests {

    @Test
    void contextLoads() throws ParseException {

//        Date nowDate = new Date(); // 获取系统当前时间
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");// 设置日期格式
//        String sDate = format.format(nowDate);  //日期转字符串
        Date classDate = format.parse("2021-10-01");   //字符串转日期
        Calendar calendar = Calendar.getInstance(); // 使用Calendar日历类对日期进行加减
        calendar.setTime(classDate);
        calendar.add(Calendar.MONTH, 1); //月份+1
        //...同理。
        String preMonth = format.format(calendar.getTime());
        System.out.println(preMonth);



    }

}
