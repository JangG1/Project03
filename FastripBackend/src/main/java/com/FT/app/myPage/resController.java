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
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

import com.FT.app.Repo.AddPasRepository;
import com.FT.app.Repo.ResRepository;
import com.FT.app.domain.KakaoProfile;
import com.FT.app.domain.Seat;
import com.FT.app.domain.User;
import com.FT.app.domain.Way;
import com.FT.app.myPage.domain.AddPassenger;
import com.FT.app.myPage.domain.ResList;
import com.FT.app.myPage.mapper.ResListMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonParser;
import com.google.gson.Gson;
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
	private AddPasRepository addPasRepository;

	// Test
	/*@GetMapping("/JPATest")
	public void JPATest() {
		System.out.println("시작");			
		
		AddPassenger resList2 = new AddPassenger();

		System.out.println(resList2);
		
		//추가 승객
		Map<String, String> addAdult = resList2.getAddAdult();
		Map<String, String> addChild = resList2.getAddChild();
		Map<String, String> addInfant = resList2.getAddInfant();
		addAdult.put("addAdult", "김민지");
		addChild.put("addChild", "팜하니");
		addInfant.put("addInfant", "강해린");				
		
		resRepository2.save(resList2);
		
		System.out.println("종료");		
	}*/
	
	// 예약 내역 전부 조회
	@GetMapping("/all")
	public List<ResList> all() {
		return resRepository.findAll();
	}

	// id(res_no) 기준으로 예약내역(승객 정보) 조회
	/*@GetMapping("/resList/{id}")
	public ResList detail(@PathVariable int id) {
		ResList resList = resRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("없는 정보 입니다.");
		});
		return resList;
	}*/
	
	// id(res_no) 기준으로 추가 승객 예약내역 조회
	@GetMapping("/addPas/{id}")
	public Map getAddPasDetail(@PathVariable int id) {
		AddPassenger addPasList = addPasRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("없는 정보 입니다.");
		});
	    
		//AddPassenger -> String 형변환
		String addAdultInfo = addPasList.getAddAdult().toString();
		String addChildInfo = addPasList.getAddChild().toString();
		String addInfantInfo = addPasList.getAddInfant().toString();

	    // 데이터중 특수문자 및 공백으로 인해 com.google.gson.stream.MalformedJsonException 발생
	    String tempStr = addAdultInfo.replace("", " ");

	    // 한글, 영어, 일부 특수문자를 제외한 문자 제거
	    String match = "[^\uAC00-\uD7A3xfe0-9a-zA-Z~!@#$%^&*()_+|<>?:{}]";
	    tempStr = tempStr.replaceAll(match, "");
		
		//String -> JsonObject 형변환
		JsonParser parser = new JsonParser();
		JsonObject obj1 = (JsonObject)parser.parse(addAdultInfo);
		JsonObject obj2 = (JsonObject)parser.parse(addChildInfo);
		JsonObject obj3 = (JsonObject)parser.parse(addInfantInfo);
		
		// 하나의 JsonObject로 모든 승객 정보(성인, 유아, 소아) 추가
		JsonObject allMemberList = new JsonObject();
		allMemberList.add("adult",obj1);
		allMemberList.add("child",obj2);
		allMemberList.add("infant",obj3);		
	    
		//JsonObject -> Hashmap 형변환
		Gson gson =new Gson();
		Map allMemberMap =new HashMap();		
		allMemberMap = (Map)gson.fromJson(allMemberList, allMemberMap.getClass());
		 
		return allMemberMap;
	}

	// 로그인 email 기준 예약 내역 전부 조회
	@GetMapping("/resList/{email}")
	public List<ResList> getResList(@PathVariable("email") String email) {
		return resRepository.findByEmail(email);
	}

	// 예약 내역 저장
	@PostMapping("/resPost")
	public void addResList(@RequestBody ResList resList) {
		System.out.println(resList);
		resRepository.save(resList);
	}
	
	/*@PostMapping("/resPost/addPas")
	public void getResList2(@RequestBody String resList) throws IOException {

		System.out.println(resList);
		System.out.println(resList.getClass().getName());
					
		//resRepository.save(resList);						
	}*/
	
	@PostMapping("/resPost/addPas")
	public void addPasList(@RequestBody HashMap<String, Object> resList) throws IOException {

		///////추가 승객 시작///////(정상작동 2/23 02:33)
		String addAdultInfo = resList.get("addAdult").toString();
		String addChildInfo = resList.get("addChild").toString();
		String addInfantInfo = resList.get("addInfant").toString();
		
		AddPassenger addPas = new AddPassenger();
		
		Map<String, String> addAdult = addPas.getAddAdult();
		Map<String, String> addChild = addPas.getAddChild();
		Map<String, String> addInfant = addPas.getAddInfant();
		addAdult.put("addAdult", addAdultInfo);
		addChild.put("addChild", addChildInfo);
		addInfant.put("addInfant", addInfantInfo);			
		
		addPasRepository.save(addPas);
		///////추가 승객 종료///////				
					

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
