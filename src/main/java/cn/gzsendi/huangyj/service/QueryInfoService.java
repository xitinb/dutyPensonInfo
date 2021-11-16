package cn.gzsendi.huangyj.service;

import cn.gzsendi.huangyj.pojo.DutyPensonInfo;

import java.util.List;

public interface QueryInfoService {
    List<DutyPensonInfo> hasInfo(String date, String province);
}
