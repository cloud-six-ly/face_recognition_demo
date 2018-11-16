package com.dbgs.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.baidu.utils.FaceAdd;
import com.baidu.utils.FaceSearch;

@Controller
public class SingleFileUpload {
	@RequestMapping("/upload")
	public String singleFileUpload(@RequestParam("file") MultipartFile file,
	                               RedirectAttributes redirectAttributes) {
	    if (file.isEmpty()) {
	        redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
	        return "redirect:uploadfail.html";
	    }

	    try {
	        byte[] bytes = file.getBytes();
	        //保存到本地
	        Path path = Paths.get("E:\\photo_test\\" + file.getOriginalFilename());
	        Files.write(path, bytes);
	        //上传到百度
	        FaceAdd.add("APPLE_002", "Tim Cook", bytes);
	        FaceSearch.search(bytes);
	        redirectAttributes.addFlashAttribute("message",
	                "You successfully uploaded '" + file.getOriginalFilename() + "'");

	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	    return "redirect:/uploadSuccess.html";
	}
}
