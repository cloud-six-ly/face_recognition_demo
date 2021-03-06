package com.chengdu.utils;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.baidu.ai.aip.utils.GsonUtils;
import com.chengdu.ai.util.OkHttpClientUtil;
import com.dbgs.entity.CHENGDU;

/**
* 人脸注册
*/
@Component
public class FaceRegistry {
    
    public String regist(String userName, String imageBase64) {
        // 请求url
        String url = "http://182.150.35.182/registry";
        try {
            CHENGDU cd = new CHENGDU("DBGS", imageBase64);
            if(!StringUtils.isEmpty(userName))
            	cd.setUserName(userName);
            String json = GsonUtils.toJson(cd);
            String result = OkHttpClientUtil.sendJSON(url, json);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}