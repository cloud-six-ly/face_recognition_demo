package com.baidu.utils;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.baidu.ai.aip.utils.Base64Util;
import com.baidu.ai.aip.utils.GsonUtils;
import com.baidu.ai.aip.utils.HttpUtil;

/**
* 人脸注册
*/
@Component
public class FaceAdd {

    public static String add(String user_id, String user_info, byte[] imagesBytes) {
        // 请求url
        String url = "https://aip.baidubce.com/rest/2.0/face/v3/faceset/user/add";
        try {
            Map<String, Object> map = new HashMap<>();
            String image = Base64Util.encode(imagesBytes);
            map.put("image", image);
            map.put("image_type", "BASE64");
            //测试账户开通的用户组ID，暂时写死且固定用这一个，建议按照每个用户进行分组
            //用户所有比对数据都存放在一个group中
            map.put("group_id", "DBGS_TEST_GROUP");
            map.put("user_id", user_id);
            map.put("user_info", user_info);
            //测试使用，放弃所有图片质控
            /*活体检测控制
			NONE: 不进行控制
			LOW:较低的活体要求(高通过率 低攻击拒绝率)
			NORMAL: 一般的活体要求(平衡的攻击拒绝率, 通过率)
			HIGH: 较高的活体要求(高攻击拒绝率 低通过率)
			默认NONE
			若活体检测结果不满足要求，则返回结果中会提示活体检测失败*/
            map.put("liveness_control", "NONE");
            /*图片质量控制
			NONE: 不进行控制
			LOW:较低的质量要求
			NORMAL: 一般的质量要求
			HIGH: 较高的质量要求
			默认 NONE
			若图片质量不满足要求，则返回结果中会提示质量检测失败*/
            map.put("quality_control", "NONE");

            String param = GsonUtils.toJson(map);
            String accessToken = "24.b8cd3dae8ac564c7a3d4b4db79012818.2592000.1542784650.282335-14439847";
            String result = HttpUtil.post(url, accessToken, "application/json", param);
            System.out.println(result);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public String regist(String user_id, String user_info, String imageBase64, String accessToken) {
        // 请求url
        String url = "https://aip.baidubce.com/rest/2.0/face/v3/faceset/user/add";
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("image", imageBase64);
            map.put("image_type", "BASE64");
            //测试账户开通的用户组ID，暂时写死且固定用这一个，建议按照每个用户进行分组
            //用户所有比对数据都存放在一个group中
            map.put("group_id", "DBGS_TEST_GROUP");
            map.put("user_id", user_id);
            map.put("user_info", user_info);
            //测试使用，放弃所有图片质控
            /*活体检测控制
			NONE: 不进行控制
			LOW:较低的活体要求(高通过率 低攻击拒绝率)
			NORMAL: 一般的活体要求(平衡的攻击拒绝率, 通过率)
			HIGH: 较高的活体要求(高攻击拒绝率 低通过率)
			默认NONE
			若活体检测结果不满足要求，则返回结果中会提示活体检测失败*/
            map.put("liveness_control", "NONE");
            /*图片质量控制
			NONE: 不进行控制
			LOW:较低的质量要求
			NORMAL: 一般的质量要求
			HIGH: 较高的质量要求
			默认 NONE
			若图片质量不满足要求，则返回结果中会提示质量检测失败*/
            map.put("quality_control", "NONE");

            String param = GsonUtils.toJson(map);

            String result = HttpUtil.post(url, accessToken, "application/json", param);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}