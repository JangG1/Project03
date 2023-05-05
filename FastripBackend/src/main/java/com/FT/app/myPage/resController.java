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
import com.FT.app.domain.KakaoUser;
import com.FT.app.domain.Way;
import com.FT.app.myPage.domain.AddPassenger;
import com.FT.app.myPage.domain.ResList;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonParser;
import com.google.gson.JsonArray;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@RestController
public class ResController {
	@Autowired
	private ResRepository resRepository;

	@Autowired
	private AddPasRepository addPasRepository;
	
	// 예약 내역 전부 조회
	@GetMapping("/res/all")
	public List<ResList> all() {
		return resRepository.findAll();
	}
	
	// id(res_no) 기준으로 추가 승객 예약내역 조회
	@GetMapping("/res/addPas/{id}")
	public Map getAddPasDetail(@PathVariable int id) {
		AddPassenger addPasList = addPasRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("없는 정보 입니다.");
		});
	    
		//AddPassenger -> String 형변환
		String addAdultInfo = addPasList.getAddAdult().toString();
		String addChildInfo = addPasList.getAddChild().toString();
		String addInfantInfo = addPasList.getAddInfant().toString();
		
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
	@GetMapping("/res/resList/{email}")
	public List<ResList> getResList(@PathVariable("email") String email) {
		//List<ResList> a = resRepository.findByEmail(email);
		//예외처리 구현 필요
		
		return resRepository.findByEmail(email);
	}

	// 예약 내역 저장
	@PostMapping("/res/resPost")
	public void addResList(@RequestBody ResList resList) {
		System.out.println(resList);
		resRepository.save(resList);
	}
	
	@PostMapping("/res/resPost/addPas")//추가 승객(성인, 유아, 소아) 정보 저장
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
	
	// id(res_no) 기준으로 예약내역(승객 정보), 추가 승객 정보 삭제
	@PostMapping("/res/remove/{id}")
	public void detailDelete(@PathVariable int id) {
		resRepository.deleteById(id);
		addPasRepository.deleteById(id);
		System.out.println(id + "번째 예약 정보가 삭제되었습니다.");
	}
	
}
