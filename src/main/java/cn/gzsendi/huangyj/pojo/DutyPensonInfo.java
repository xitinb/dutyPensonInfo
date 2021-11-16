package cn.gzsendi.huangyj.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class DutyPensonInfo {

    private String date;

    private String province;

    private String name;

    private String mobile;

    private Boolean hasInfo;

    private Boolean hasMajorOrTechnologyTag;
}
