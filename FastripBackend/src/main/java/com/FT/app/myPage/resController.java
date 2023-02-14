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
import java.util.Map;
import java.util.function.Supplier;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.json.JSONParser;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.FT.app.Repo.ResRepository;
import com.FT.app.domain.Seat;
import com.FT.app.domain.Way;
import com.FT.app.myPage.domain.ResList;
import com.FT.app.myPage.mapper.ResListMapper;
import com.google.gson.JsonParser;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

@RestController
@RequestMapping("/res/*")
public class ResController {
	@Autowired
	private ResRepository resRepository;

	//예약 내역 전부 조회
	@GetMapping("/all")
	public List<ResList> all(){
		return resRepository.findAll();
	}
	
	//id(res_no) 기준으로 예약내역(승객 정보) 조회
	@GetMapping("/{id}")
	public ResList detail(@PathVariable int id){
		ResList resList = resRepository.findById(id).orElseThrow(()-> {
					return new IllegalArgumentException("없는 정보 입니다.");	
		});
		return resList;
	}
	
	//로그인 email 기준 예약 내역 전부 조회
	@GetMapping("/resList/{email}")
	public List<ResList> getResList(@PathVariable("email") String email) {		
		return resRepository.findByEmail(email);
	}
	
	//예약 내역 저장
	@PostMapping("/resPost")
	public void Test(@RequestBody ResList resList) {
		System.out.println(resList);
		resRepository.save(resList);
	}
	
	//id(res_no) 기준으로 예약내역(승객 정보) 삭제
	@PostMapping("/remove/{id}")
	public void detailDelete(@PathVariable int id){
		resRepository.deleteById(id);
		System.out.println(id + "번째 예약 정보가 삭제되었습니다.");
	}

	//다중 삭제
	@PostMapping("/remove")
	public void selectAllDelete(@RequestBody String id){

		//resRepository.deleteById(id);
	    System.out.println("test : " + id);
	    
		System.out.println(id + "번째 예약 정보가 삭제되었습니다.");
	}
	
}
