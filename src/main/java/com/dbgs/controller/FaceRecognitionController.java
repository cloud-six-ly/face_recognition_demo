package com.dbgs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dbgs.entity.Result;
import com.dbgs.service.FaceRecognitionService;
import com.dbgs.utils.AuthService;
import com.dbgs.utils.InfoService;
import com.dbgs.utils.SwitchService;

@Controller
public class FaceRecognitionController {
	
	@Autowired
	private FaceRecognitionService faceRecognitionService;
	
	@Autowired
	private AuthService authService;
	
	@Autowired
	private SwitchService switchService;
	
	@Autowired
	private InfoService infoService;
	
	@RequestMapping("/regist")
	@ResponseBody
	public Result addFace(String img_base64, String user_info, String user_id) {
		Result result = new Result(switchService.getCaller(), null);
		switch (result.getCaller()) {
		case "BAIDU":
			result.setResult(faceRecognitionService.regist(user_id, user_info, img_base64, authService.getAuth()));
			break;
		case "CHENGDU":
			result.setResult(faceRecognitionService.registry(user_info, img_base64));
			break;
		default:
			result.setCaller("ERROR");
			break;
		}
		return result;
	}
	
	@RequestMapping("/search")
	@ResponseBody
	public Result searchFace(String img_base64) {
		Result result = new Result(switchService.getCaller(), null);
		switch (result.getCaller()) {
		case "BAIDU":
			result.setResult(faceRecognitionService.searchFace(img_base64, authService.getAuth()));
			break;
		case "CHENGDU":
			result.setResult(faceRecognitionService.find(img_base64));
			break;
		default:
			result.setCaller("ERROR");
			break;
		}
		return result;
	}
	
	@RequestMapping("/verify")
	@ResponseBody
	public String FaceVerify(String img_base64) {
		return faceRecognitionService.FaceVerify(img_base64, authService.getAuth());
	}
	
	//半分钟一次
	@Scheduled(fixedDelay = 30000)
	public void task() {
		faceRecognitionService.task(switchService.getCaller(), infoService.getValueByKey("Directory"), authService.getAuth());
	}
	
	@RequestMapping("/caller")
	public void modifyCaller(String caller) {
		switchService.updateCaller(caller);
	}
}
