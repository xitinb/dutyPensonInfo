package cn.gzsendi.huangyj.constant;

import cn.hutool.core.map.MapUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yzq
 * @version 1.0
 * @date 2021/03/30 10:04
 */
public class ZygxQuotaConstant {

    private static final Map<String, Object> QUOTAS = new HashMap<>();
    public static Map<String, Object> getQuotas(String key) {
        return MapUtil.get(QUOTAS, key, Map.class);
    }

    public static void addAll(Map<String, Object> quotaMap) {
        QUOTAS.putAll(quotaMap);
    }

    public static int getMinCycle(String type) {
        return MapUtil.getInt(MapUtil.get(QUOTAS, "cycle", Map.class), type);
    }

    public static Map<String, Integer> getMinCycles() {
        return MapUtil.get(QUOTAS, "cycle", Map.class);
    }
}
