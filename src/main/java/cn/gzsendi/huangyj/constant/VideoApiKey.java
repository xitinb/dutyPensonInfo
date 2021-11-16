package cn.gzsendi.huangyj.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yang
 * @version 1.0
 * @date 2021/7/19
 */
public class VideoApiKey {

    private static final Map<String, String> apiMap = new HashMap<>();


    public static String getByApiKey(String api) {
        return apiMap.get(api);
    }

    public static boolean hasApiKey(String api) {
        return apiMap.containsKey(api);
    }

    public static void putApiKeys(Map<String, String> apiMap) {
        VideoApiKey.apiMap.putAll(apiMap);
    }

    public static void putApiKey(String key, String value) {
        VideoApiKey.apiMap.put(key, value);
    }
}
