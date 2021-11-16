package cn.gzsendi.huangyj.util;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author duanw
 * @description：Date工具类
 */
public class DateUtil {

    public static final DateTimeFormatter SHORT_DATETIME_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

    public static final DateTimeFormatter SHORT_DATETIME_LONG_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static final DateTimeFormatter SHORT_DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMddHH");



    /**
     * 解析字符串日期为Date
     *
     * @param dateStr 日期字符串
     * @param dateTimeFormatter 格式
     * @return Date
     */
    public static Date string2Date(String dateStr, DateTimeFormatter dateTimeFormatter) {
        LocalDateTime localDateTime = LocalDateTime.parse(dateStr, dateTimeFormatter);
        Instant instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant();
        return Date.from(instant);
    }

}
