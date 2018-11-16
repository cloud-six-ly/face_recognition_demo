package com.baidu.utils;

import java.util.HashMap;
import java.util.Map;

import com.baidu.ai.aip.utils.GsonUtils;
import com.baidu.ai.aip.utils.HttpUtil;

public class FaceDelete {

	public static String delete() {
		// 请求url
		String url = "https://aip.baidubce.com/rest/2.0/face/v3/faceset/face/delete";
		try {
			Map<String, Object> map = new HashMap<>();
			map.put("user_id", "DBGS32");
			map.put("group_id", "DBGS_TEST_GROUP");
			map.put("face_token", "711956e2bb66db4bd619574688fa4252");
			String param = GsonUtils.toJson(map);
			// 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
			String accessToken = "24.b8cd3dae8ac564c7a3d4b4db79012818.2592000.1542784650.282335-14439847";
			String result = HttpUtil.post(url, accessToken, "application/json", param);
			System.out.println(result);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String[] args) {
		delete();
	}
	
}
