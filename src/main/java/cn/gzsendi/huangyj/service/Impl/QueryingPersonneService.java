package cn.gzsendi.huangyj.service.Impl;

import cn.gzsendi.huangyj.constant.ProvinceConstant;
import cn.gzsendi.huangyj.mapper.QueryingPersonnelMapper;
import cn.gzsendi.huangyj.pojo.DutyPensonInfo;
import cn.gzsendi.huangyj.service.QueryingPersonne;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static java.util.stream.Collectors.groupingBy;

@Service
public class QueryingPersonneService implements QueryingPersonne {


    @Resource
    private QueryingPersonnelMapper queryingPersonnelMapper;


    @Override
    public List<DutyPensonInfo> isNotNull1(String date, String province) {

         Map<String,String> provinceChMap=getProvinceChMap();

         //查制定当前月的范围 例如 2021-10-01 到 2010-11-01（不包括） 为一个月时间

        String beginTime = date + "-01";
        String endTime = getEndTime(beginTime);
        System.out.println(beginTime    +  endTime);
        List<Map> dutyPeson = queryingPersonnelMapper.isNotNull1(beginTime,endTime,provinceChMap.get(province));

        //存放返回值
        List<DutyPensonInfo> dutyPensonInfos = new ArrayList<>();

        //存放需要二次查询标签的数据
        List<DutyPensonInfo> hasInfo = new ArrayList<>();

        dutyPeson.stream().forEach( penson ->{
            DutyPensonInfo dutyPensonInfo = new DutyPensonInfo();
            dutyPensonInfo.setDate(date);
            dutyPensonInfo.setMobile(penson.containsKey("mobile") ? (String) penson.get("mobile") : null);
            dutyPensonInfo.setName((String) penson.get("name"));
            dutyPensonInfo.setProvince(province);
            if(penson.containsKey("mobile2") && penson.containsKey("name2")){
                dutyPensonInfo.setHasInfo(true);
                hasInfo.add(dutyPensonInfo);
            }else {
                dutyPensonInfo.setHasInfo(false);
                dutyPensonInfo.setHasMajorOrTechnologyTag(false);
                dutyPensonInfos.add(dutyPensonInfo);
            }
        });

        System.out.println("第二步数据---------------------");
        for (DutyPensonInfo dutyPensonInfo : hasInfo) {
            List<Map> notNull2 = queryingPersonnelMapper.isNotNull2(dutyPensonInfo.getName(),dutyPensonInfo.getMobile());
            if(notNull2.size()==0) {
//                System.out.println(dutyPensonInfo.getName() + "没有标签");
                dutyPensonInfo.setHasMajorOrTechnologyTag(false);
            }
            else {
                dutyPensonInfo.setHasMajorOrTechnologyTag(true);
            }
            dutyPensonInfos.add(dutyPensonInfo);
        }
        return  dutyPensonInfos;
    }


    private Map<String,String> getProvinceChMap(){
        Map<String,String> map=new HashMap<>();
        for (String key: ProvinceConstant.getLongCodes()){
            map.put(ProvinceConstant.getCh(key),key);
        }
        return map;
    }

    private String getEndTime(String beginTime) {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");// 设置日期格式
        // String sDate = format.format(nowDate);  //日期转字符串
        Date classDate = null;   //字符串转日期
        try {
            classDate = format.parse(beginTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar calendar = Calendar.getInstance(); // 使用Calendar日历类对日期进行加减
        calendar.setTime(classDate);
        calendar.add(Calendar.MONTH, 1); //月份+1
        //...同理。
        String endTime = format.format(calendar.getTime());

        return endTime;
    }
}
