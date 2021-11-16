package cn.gzsendi.huangyj.mapper;

import java.util.List;
import java.util.Map;

/**
 * @author H
 */
public interface QueryingPersonnelMapper {

    List<Map> isNotNull1(String beginTime, String endTime, String province);

    List<Map> isNotNull2(String name ,String mobile);

}
