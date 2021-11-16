package cn.gzsendi.huangyj.listener;

import cn.gzsendi.huangyj.constant.ProvinceConstant;
import cn.gzsendi.huangyj.constant.VideoApiKey;
import cn.gzsendi.huangyj.constant.ZygxQuotaConstant;
import cn.hutool.core.io.FastByteArrayOutputStream;
import cn.hutool.core.io.IoUtil;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;

import java.io.InputStream;
import java.util.Map;

/**
 * @author yzq
 * @version 1.0
 * @date 2021/03/30 11:08
 */
@Slf4j
@Component
public class ApplicationStartedListener implements ApplicationListener<ContextRefreshedEvent> {


    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        InputStream jsonStream = ClassUtils.getDefaultClassLoader().getResourceAsStream("json/provinceLongCode.json");
        FastByteArrayOutputStream read = IoUtil.read(jsonStream);
        Map<String, Object> json = JSONUtil.toBean(read.toString("utf-8"), Map.class);
        ProvinceConstant.addLongCode2Ch(json);

        jsonStream = ClassUtils.getDefaultClassLoader().getResourceAsStream("json/zygxQuota.json");
        read = IoUtil.read(jsonStream);
        Map<String, Object> quota = JSONUtil.toBean(read.toString("utf-8"), Map.class);
        ZygxQuotaConstant.addAll(quota);

        jsonStream = ClassUtils.getDefaultClassLoader().getResourceAsStream("json/cloudVideoApiKeys.json");
        read = IoUtil.read(jsonStream);
        VideoApiKey.putApiKeys(JSONUtil.toBean(read.toString("utf-8"), Map.class));
    }
}
