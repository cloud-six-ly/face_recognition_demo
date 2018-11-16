package com.baidu.utils;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.baidu.ai.aip.utils.Base64Util;
import com.baidu.ai.aip.utils.GsonUtils;
import com.baidu.ai.aip.utils.HttpUtil;

/**
* 人脸搜索 M:N 识别
*/
@Component
public class FaceMultiSearch {

    public String search(String image, String accessToken) {
        // 请求url
        String url = "https://aip.baidubce.com/rest/2.0/face/v3/multi-search";
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("image", image);
            map.put("image_type", "BASE64");
            map.put("liveness_control", "NONE");
            map.put("group_id_list", "DBGS_TEST_GROUP");
            map.put("quality_control", "NONE");
            map.put("max_face_num", "10");
            map.put("max_user_num", "20");
            map.put("match_threshold", "80");
            String param = GsonUtils.toJson(map);
            String result = HttpUtil.post(url, accessToken, "application/json", param);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public static String search(byte[] imagesBytes) {
        // 请求url
        String url = "https://aip.baidubce.com/rest/2.0/face/v3/multi-search";
        try {
            Map<String, Object> map = new HashMap<>();
            String image = Base64Util.encode(imagesBytes);
            map.put("image", image);
            map.put("image_type", "BASE64");
            map.put("liveness_control", "NONE");
            map.put("group_id_list", "DBGS_TEST_GROUP");
            map.put("quality_control", "NONE");
            map.put("max_face_num", "10");
            map.put("max_user_num", "20");
            map.put("match_threshold", "80");
            String param = GsonUtils.toJson(map);

            // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
            String accessToken = "24.b8cd3dae8ac564c7a3d4b4db79012818.2592000.1542784650.282335-14439847";
            //AuthService.getAuth();

            String result = HttpUtil.post(url, accessToken, "application/json", param);
            System.out.println(result);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}