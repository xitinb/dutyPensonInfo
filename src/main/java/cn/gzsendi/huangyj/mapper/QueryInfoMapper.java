package cn.gzsendi.huangyj.mapper;

import java.util.List;
import java.util.Map;

/**
 * @author H
 */
public interface QueryInfoMapper {

    List<Map> hasPenson(String beginTime, String endTime, String province);

    List<Map> HasMajorOrTechnologyTag();

}
