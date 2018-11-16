package com.chengdu.utils;

import org.springframework.stereotype.Component;

import com.baidu.ai.aip.utils.GsonUtils;
import com.chengdu.ai.util.OkHttpClientUtil;
import com.dbgs.entity.CHENGDU;

/**
* 人脸搜索 1:N 查找
*/
@Component
public class FaceFind {

	public String find(String imageBase64) {
        // 请求url
        String url = "http://182.150.35.182/find";
        try {
            CHENGDU cd = new CHENGDU("DBGS", imageBase64);
            String json = GsonUtils.toJson(cd);
            String result = OkHttpClientUtil.sendJSON(url, json);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
	
}