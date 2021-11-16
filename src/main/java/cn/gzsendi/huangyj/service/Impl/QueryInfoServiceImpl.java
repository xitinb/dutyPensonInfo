package cn.gzsendi.huangyj.service.Impl;

import cn.gzsendi.huangyj.constant.ProvinceConstant;
import cn.gzsendi.huangyj.mapper.QueryInfoMapper;
import cn.gzsendi.huangyj.pojo.DutyPensonInfo;
import cn.gzsendi.huangyj.service.QueryInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class QueryInfoServiceImpl implements QueryInfoService {


    @Resource
    private QueryInfoMapper queryInfoMapper;


    @Override
    public List<DutyPensonInfo> hasInfo(String date, String province) {

        Map<String, String> provinceChMap = getProvinceChMap();

        //查制定当前月的范围 例如 2021-10-01 到 2010-11-01（不包括） 为一个月时间
        String beginTime = date + "-01";
        String endTime = getEndTime(beginTime);
        List<Map> dutyPeson = queryInfoMapper.hasPenson(beginTime, endTime, provinceChMap.get(province));

        //存放返回值
        List<DutyPensonInfo> dutyPensonInfos = new ArrayList<>();

        //存放需要二次查询标签的数据
        List<DutyPensonInfo> hasInfo = new ArrayList<>();

        //先将没有人员信息的值班人员分出来
        dutyPeson.stream().forEach(penson -> {
            DutyPensonInfo dutyPensonInfo = new DutyPensonInfo();
            dutyPensonInfo.setDate(date);
            dutyPensonInfo.setMobile(penson.containsKey("mobile") ? (String) penson.get("mobile") : null);
            dutyPensonInfo.setName((String) penson.get("name"));
            dutyPensonInfo.setProvince(province);

            //值班人员存在人员表中，需要进行标签判断
            if (penson.containsKey("mobile2") && penson.containsKey("name2")) {
                dutyPensonInfo.setHasInfo(true);
                hasInfo.add(dutyPensonInfo);
            } else {
                //不存在人员信息表的值班人员直接放入需要返回的结果集
                dutyPensonInfo.setHasInfo(false);
                dutyPensonInfo.setHasMajorOrTechnologyTag(false);
                dutyPensonInfos.add(dutyPensonInfo);
            }
        });

        //先将人员编码表中，有技术类标签的人员编码全部查出来，然后再进行比对
        List<Map> notNull2 = queryInfoMapper.HasMajorOrTechnologyTag();
        for (DutyPensonInfo dutyPensonInfo : hasInfo) {
            boolean flag = false;
            for (Map map : notNull2) {
                //                判断人员是否在标签库里
                if (dutyPensonInfo.getMobile().equals(map.get("mobile")) && dutyPensonInfo.getName().equals(map.get("name"))) {
                    flag = true;
                    dutyPensonInfo.setHasMajorOrTechnologyTag(true);
                    break;
                }
            }
            if (flag == false) {
                dutyPensonInfo.setHasMajorOrTechnologyTag(false);
            }
            dutyPensonInfos.add(dutyPensonInfo);
        }

        return dutyPensonInfos;
    }

    //省份编码
    private Map<String, String> getProvinceChMap() {
        Map<String, String> map = new HashMap<>();
        for (String key : ProvinceConstant.getLongCodes()) {
            map.put(ProvinceConstant.getCh(key), key);
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
