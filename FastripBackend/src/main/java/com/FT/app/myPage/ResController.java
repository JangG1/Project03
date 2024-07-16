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

import com.FT.app.Repo.KakaoUserRepository;
import com.FT.app.Repo.ResRepository;
import com.FT.app.domain.KakaoProfile;
import com.FT.app.domain.Seat;
import com.FT.app.domain.KakaoUser;
import com.FT.app.domain.Way;
import com.FT.app.Login.service.UserService;
import com.FT.app.myPage.domain.ResList;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonParser;

import lombok.RequiredArgsConstructor;

import com.google.gson.JsonArray;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@RestController
@RequiredArgsConstructor //클래스에 선언된 final 변수들, 필드들을 매개변수로 하는 생성자를 자동으로 생성해주는 어노테이션
public class ResController {
	
	//@Autowired
	//private ResRepository resRepository;
	
	//@Autowired 생략 가능
	// 생성자 주입으로 NullPointerException 방지 가능
	private final ResRepository resRepository;
	
	// 로그인 email 기준 예약 내역 전부 조회
	@GetMapping("/res/resList/{email}")
	public List<ResList> getResList(@PathVariable("email") String email) {
		return resRepository.findByEmail(email);
	}
	
	// id(res_no) 기준으로 추가 승객 예약내역 조회
	@GetMapping("/res/addPas/{id}")
	public Map getAddPasDetail(@PathVariable int id) {
		ResList addPasList = resRepository.findById(id).orElseThrow(() -> {
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
	
	// 예약 내역 저장
	@PostMapping("/res/resPost")	
	public void addResList(@RequestBody HashMap<String, Object> resAllList) {
		ResList resList = new ResList();

		// 여행 정보
		resList.setEmail((String) resAllList.get("email"));
		String seatString = (String) resAllList.get("seat"); 
		resList.setSeat(Seat.valueOf(seatString));  // String을 직접 캐스팅할수 없기 때문에 .valueOf 으로 열거형 상수로 변환	
		resList.setSeatClass1((String) resAllList.get("seatClass1"));
		resList.setSeatClass2((String) resAllList.get("seatClass2"));
		String wayString = (String) resAllList.get("way");
		resList.setWay(Way.valueOf(wayString)); // String을 직접 캐스팅할수 없기 때문에 .valueOf 으로 열거형 상수로 변환	
		resList.setFlight1((String) resAllList.get("flight1"));
		resList.setFlight2((String) resAllList.get("flight2"));
		resList.setFromArea((String) resAllList.get("fromArea"));
		resList.setToArea((String) resAllList.get("toArea"));
		resList.setOneWayArea((String) resAllList.get("oneWayArea"));
		resList.setStartDate((String) resAllList.get("startDate"));
		resList.setReturnDate((String) resAllList.get("returnDate"));
		resList.setAdultCount((int) resAllList.get("adultCount"));
		resList.setChildCount((int) resAllList.get("childCount"));
		resList.setInfantCount((int) resAllList.get("infantCount"));
		resList.setStartTime1((String) resAllList.get("startTime1"));
		resList.setArriveTime1((String) resAllList.get("arriveTime1"));
		resList.setStartTime2((String) resAllList.get("startTime2"));
		resList.setArriveTime2((String) resAllList.get("arriveTime2"));
		
		// 추가 승객
		Map<String, String> addAdult = resList.getAddAdult();
		Map<String, String> addChild = resList.getAddChild();
		Map<String, String> addInfant = resList.getAddInfant();
		addAdult.put("addAdult", resAllList.get("addAdult").toString());
		addChild.put("addChild", resAllList.get("addChild").toString());
		addInfant.put("addInfant", resAllList.get("addInfant").toString());

		resRepository.save(resList);
	}
	
	// id(res_no) 기준으로 예약내역(승객 정보), 추가 승객 정보 삭제
	@PostMapping("/res/remove/{id}")
	public void detailDelete(@PathVariable int id) {
		resRepository.deleteById(id);
		System.out.println(id + "번째 예약 정보가 삭제되었습니다.");
	}
	
}
