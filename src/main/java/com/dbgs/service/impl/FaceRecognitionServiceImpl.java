package com.dbgs.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.imageio.stream.FileImageOutputStream;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Base64Utils;

import com.baidu.utils.FaceAdd;
import com.baidu.utils.FaceMultiSearch;
import com.baidu.utils.FaceVerify;
import com.chengdu.utils.FaceFind;
import com.chengdu.utils.FaceRegistry;
import com.dbgs.entity.RegistResult;
import com.dbgs.entity.SearchResult;
import com.dbgs.repository.RegistResultRepository;
import com.dbgs.repository.SearchResultRepository;
import com.dbgs.service.FaceRecognitionService;

@Service
@Transactional(rollbackFor=Exception.class)
public class FaceRecognitionServiceImpl implements FaceRecognitionService {

	@Autowired
	private RegistResultRepository registResultRepository;
	@Autowired
	private FaceAdd faceAdd;
	@Autowired
	private SearchResultRepository searchResultRepository;
	@Autowired
	private FaceMultiSearch faceMultiSearch;
	@Autowired
	private FaceRegistry faceRegistry;
	@Autowired
	private FaceVerify faceVerify;
	@Autowired
	private FaceFind faceFind;
	
	@Override
	public String regist(String user_id, String user_info, String imageBase64, String accessToken) {
		String result = faceAdd.regist(user_id, user_info, imageBase64, accessToken);
		RegistResult registResult = new RegistResult();
		registResult.setResult(result);
		registResult.setName(user_info);
		registResult.setNumber(user_id);
		//存储图片
		
		registResultRepository.save(registResult);
		return result;
	}

	@Override
	public String searchFace(String img_base64, String accessToken) {
		String result = faceMultiSearch.search(img_base64, accessToken);
		int errorCode = new JSONObject(result).getInt("error_code");
		//222202表示没有人脸
		if(222202 != errorCode) {
			String fileName = "D:\\face_images\\" + UUID.randomUUID().toString() + ".jpg";
			byte[] bytes = Base64Utils.decodeFromString(img_base64);
			try {
				FileImageOutputStream fios = new FileImageOutputStream(new File(fileName));
				fios.write(bytes, 0 , bytes.length);
				fios.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			SearchResult searchResult = new SearchResult();
			searchResult.setErrorCode(String.valueOf(errorCode));
			searchResult.setResult(result);
			searchResult.setImageUrl(fileName);
			searchResultRepository.save(searchResult);
		}
		return result;
	}

	@Override
	public String registry(String userName, String img_base64) {
		return faceRegistry.regist(userName, img_base64);
	}

	@Override
	public String FaceVerify(String img_base64, String accessToken) {
		String result = faceVerify.faceVerify(img_base64, accessToken);
		System.out.println(result);
		return result;
	}
	
	@Override
	public void task(String caller, String path, String accessToken) {
		/*List<File> fileList = DirectoryAndFileUtils.getFileSort(path);
		//最多处理20张图片（cause API的QPS限制 & 网络接口调用占用时长）
		for(int i = 0; i < 60 ; i++) {
			System.out.println("Task processing:" + fileList.get(i).getName() + ",by using " + caller + "technology");
			String base64 = null;
			try {
				base64 = Base64Util.encode(FileUtil.getFileBytes(fileList.get(i)));
			} catch (IOException e) {
				e.printStackTrace();
			}
			switch (caller) {
			case "BAIDU":
				searchFace(base64, accessToken);
				break;
			case "CHENGDU":
				find(base64);
				break;
			default:
				break;
			}
		}*/
	}

	@Async
	public void d() {
		
	}
	
	@Override
	public String find(String img_base64) {
		String result = faceFind.find(img_base64);
		return result;
	}
}
