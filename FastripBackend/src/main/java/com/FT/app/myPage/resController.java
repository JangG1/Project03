package com.FT.app.myPage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.FT.app.myPage.domain.resList;
import com.FT.app.myPage.mapper.resListMapper;

@RestController
@RequestMapping("/res")
public class resController {
	private resListMapper mapper;
	
	//resList 보관함에 저장
	@PostMapping("/resList/upload")
	public int upload(
			@RequestParam("email") String email,
			@RequestParam("res_date") @DateTimeFormat(iso = ISO.DATE_TIME) LocalDateTime trans_date,
			@RequestParam("kind") String kind,
			@RequestParam("input") String input,
			@RequestParam("output") String output,
			HttpServletRequest request)throws IOException {

		return mapper.insertResListContent(email,trans_date,kind,input,output);
	}
	
	//voice_trans 보관함 아이템 불러오기 email 기준 전부
	@GetMapping("/resList/list/{email}")
	public List<resList> getList(
			@PathVariable("email") String email){
		return mapper.getResListByEmail(email);
	}
	
}
