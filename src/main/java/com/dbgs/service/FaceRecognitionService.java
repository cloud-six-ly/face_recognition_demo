package com.dbgs.service;

public interface FaceRecognitionService {
	
	String regist(String user_id, String user_info, String imageBase64, String accessToken);
	
	String searchFace(String img_base64, String accessToken);
	
	String registry(String userName, String img_base64);
	
	String FaceVerify(String img_base64, String accessToken);
	
	void task(String caller, String path, String accessToken);
	
	String find(String img_base64);
}
