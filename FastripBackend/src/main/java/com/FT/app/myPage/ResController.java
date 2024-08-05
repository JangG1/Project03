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
import com.FT.app.Login.service.UserService;
import com.FT.app.myPage.domain.Passenger;
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
	
	//@Autowired 생략 가능
	// 생성자 주입으로 NullPointerException 방지 가능
	private final ResRepository resRepository;
	
	// 로그인 email 기준 예약 내역 전부 조회
	@GetMapping("/res/resList/{email}")
	public List<ResList> getResList(@PathVariable("email") String email) {
		return resRepository.findByEmail(email);
	}
	
	// 예약 정보 저장(+승객 정보 포함)
	 @PostMapping("/res/resPost")
	    public void addResList(@RequestBody Map<String, Object> resAllList) {
		 System.out.println(resAllList);
		 
	        ResList resList = ResList.builder()
	                .email((String) resAllList.get("email"))
	                .seat(Seat.valueOf((String) resAllList.get("seat")))
	                .seatClass1((String) resAllList.get("seatClass1"))
	                .seatClass2((String) resAllList.get("seatClass2"))
	                .way(Way.valueOf((String) resAllList.get("way")))
	                .flight1((String) resAllList.get("flight1"))
	                .flight2((String) resAllList.get("flight2"))
	                .fromArea((String) resAllList.get("fromArea"))
	                .toArea((String) resAllList.get("toArea"))
	                .oneWayArea((String) resAllList.get("oneWayArea"))
	                .startDate((String) resAllList.get("startDate"))
	                .returnDate((String) resAllList.get("returnDate"))
	                .adultCount((int) resAllList.get("adultCount"))
	                .childCount((int) resAllList.get("childCount"))
	                .infantCount((int) resAllList.get("infantCount"))
	                .startTime1((String) resAllList.get("startTime1"))
	                .arriveTime1((String) resAllList.get("arriveTime1"))
	                .startTime2((String) resAllList.get("startTime2"))
	                .arriveTime2((String) resAllList.get("arriveTime2"))
	                .build();

	        //하나의 요소(성인, 아동, 유아 중 1)은 혼자 혹은 여러명을 가지기에 각 타입을 Map으로 여러명을 한 데 모으고
	        //모여진 하나의 Map을 List에 하나의 요소로 추가
	        //ex) 하나의 List(Passenger)에 요소 3가지(성인=Map1, 아동=Map2, 유아=Map3) 추가
	        List<Map<String, String>> addAdults = (List<Map<String, String>>) resAllList.get("addAdult");
	        for (Map<String, String> addAdult : addAdults) { // 혼자 혹은 여러멍이기에 반복문
	        	Passenger passenger = Passenger.builder()
	                    .type("ADULT")	                    
	                    .korName(addAdult.get("korName"))
	                    .engFirstName(addAdult.get("engFirstName"))
	                    .engLastName(addAdult.get("engLastName"))
	                    .gender(addAdult.get("gender"))	                    
	                    .birthday(addAdult.get("birthday"))
	                    .build();
	            resList.Passenger(passenger);
	        }

	        List<Map<String, String>> addChildren = (List<Map<String, String>>) resAllList.get("addChild");
	        for (Map<String, String> addChild : addChildren) {// 혼자 혹은 여러멍이기에 반복문
	        	Passenger passenger = Passenger.builder()
	                    .type("CHILD")	                    
	                    .korName(addChild.get("korName"))
	                    .engFirstName(addChild.get("engFirstName"))
	                    .engLastName(addChild.get("engLastName"))
	                    .gender(addChild.get("gender"))
	                    .birthday(addChild.get("birthday"))
	                    .build();
	            resList.Passenger(passenger);
	        }

	        List<Map<String, String>> addInfants = (List<Map<String, String>>) resAllList.get("addInfant");
	        for (Map<String, String> addInfant : addInfants) {// 혼자 혹은 여러멍이기에 반복문
	        	Passenger passenger = Passenger.builder()
	                    .type("INFANT")
	                    .korName(addInfant.get("korName"))
	                    .engFirstName(addInfant.get("engFirstName"))
	                    .engLastName(addInfant.get("engLastName"))
	                    .gender(addInfant.get("gender"))
	                    .birthday(addInfant.get("birthday"))
	                    .build();
	            resList.Passenger(passenger);
	        }

	        resRepository.save(resList);
	    }
	
	
	// id(res_no) 기준으로 예약내역(승객 정보), 추가 승객 정보 삭제
	@PostMapping("/res/remove/{id}")
	public void detailDelete(@PathVariable int id) {
		resRepository.deleteById(id);
		System.out.println(id + "번째 예약 정보가 삭제되었습니다.");
	}
	
}
