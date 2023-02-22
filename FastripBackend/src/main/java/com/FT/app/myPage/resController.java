package com.FT.app.myPage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

import javax.persistence.EntityManager;
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
import com.FT.app.Repo.ResRepository2;
import com.FT.app.domain.KakaoProfile;
import com.FT.app.domain.Seat;
import com.FT.app.domain.User;
import com.FT.app.domain.Way;
import com.FT.app.myPage.domain.ResList;
import com.FT.app.myPage.domain.ResList2;
import com.FT.app.myPage.mapper.ResListMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonParser;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@RestController
@RequestMapping("/res/*")
public class ResController {
	@Autowired
	private ResRepository resRepository;

	@Autowired
	private ResRepository2 resRepository2;

	// Test
	@GetMapping("/JPATest")
	public void JPATest() {
		System.out.println("시작");			
		
		ResList2 resList2 = new ResList2();

		System.out.println(resList2);
		
		Map<String, String> addAdult = resList2.getAddAdult();
		addAdult.put("p2", "v2");
		
		System.out.println(addAdult);
		System.out.println(resList2);
		
		resRepository2.save(resList2);
		
		System.out.println("종료");		
	}
	
	// 예약 내역 전부 조회
	@GetMapping("/all")
	public List<ResList> all() {
		return resRepository.findAll();
	}

	// id(res_no) 기준으로 예약내역(승객 정보) 조회
	@GetMapping("/{id}")
	public ResList detail(@PathVariable int id) {
		ResList resList = resRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("없는 정보 입니다.");
		});
		return resList;
	}

	// 로그인 email 기준 예약 내역 전부 조회
	@GetMapping("/resList/{email}")
	public List<ResList> getResList(@PathVariable("email") String email) {
		return resRepository.findByEmail(email);
	}

	// 예약 내역 저장
	@PostMapping("/resPost2")
	public void Test(@RequestBody ResList resList) {
		System.out.println(resList);
		resRepository.save(resList);
	}

	@PostMapping("/resPost")
	public void getResList(@RequestBody HashMap<String, Object> resList) throws IOException {
		// HashMap<String, Object> rtnMap = new HashMap<String, Object>();
		// rtnMap.put("text", requestJsonHashMap.get("data1"));
		// String tts = (String) rtnMap.get("text");

		System.out.println(resList.getClass().getName());
		

		String list = resList.toString();

		System.out.println(list.getClass().getName());// String

		
		ResList2 resList2 = new ResList2();

		System.out.println(resList2);
		
		//추가 승객 (성인)
		Map<String, String> addAdult = resList2.getAddAdult();
		addAdult.put("addAdult", list);
		
		System.out.println(addAdult);
		System.out.println(resList2);
		
		resRepository2.save(resList2);
		
		
//		ResList2 q = (ResList2) rtnMap.get("text");

		// ResList2 r = (ResList2) q.getAddAdult();

//		System.out.println(q);
//		System.out.println(r);
//		
//		System.out.println("=====");
//		
//		System.out.println(q.getClass().getName());
//		System.out.println(r.getClass().getName());
//		

		// resRepository2.save(q);

		/*
		 * for(String key : resList.keySet()){ String value =
		 * resList.get(key).toString(); System.out.println(key+" : "+value); }
		 */

		// Object a = resList.get("addAdult");
		// Object b = resList.get("addChild");
		// Object c = resList.get("addInfant");

		// System.out.println(a);
		// System.out.println(b);
		// System.out.println(c);

		// resRepository.save(resList);

		// resList.remove("addAdult");
		// resList.remove("addChild");
		// resList.remove("addInfant");

	}

	// id(res_no) 기준으로 예약내역(승객 정보) 삭제
	@PostMapping("/remove/{id}")
	public void detailDelete(@PathVariable int id) {
		resRepository.deleteById(id);
		System.out.println(id + "번째 예약 정보가 삭제되었습니다.");
	}

	// 다중 삭제
	@PostMapping("/remove")
	public void selectAllDelete(@RequestBody String id) {

		// resRepository.deleteById(id);
		System.out.println("test : " + id);

		System.out.println(id + "번째 예약 정보가 삭제되었습니다.");
	}

}
