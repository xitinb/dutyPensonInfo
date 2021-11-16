package cn.gzsendi.constant;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.map.MapUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author yzq
 * @version 1.0
 * @date 2021/03/30 09:32
 */
public class ProvinceConstant {

    private static final Map<String, String> LONG_CODE_TO_CH_MAP = new HashMap<>();
    private static final Map<String, String> CH_TO_LONG_CODE_MAP = new HashMap<>();

    public static final String getLongCode(String provinceCh) {
        return MapUtil.getStr(CH_TO_LONG_CODE_MAP, provinceCh);
    }

    public static Set<String> getLongCodes() {
        return LONG_CODE_TO_CH_MAP.keySet();
    }

    public static final String getCh(String provinceCh) {
        return MapUtil.getStr(LONG_CODE_TO_CH_MAP, provinceCh);
    }

    public static void addLongCode2Ch(Map<String, Object> codeMap) {
        Assert.notEmpty(codeMap, "code can not be empty");
        LONG_CODE_TO_CH_MAP.putAll(MapUtil.get(codeMap, "longCodeToCh", Map.class));
    }

    public static void addCh2LongCode(Map<String, String> codeMap) {
        Assert.notEmpty(codeMap, "code can not be empty");
        CH_TO_LONG_CODE_MAP.putAll(codeMap);
    }
}
