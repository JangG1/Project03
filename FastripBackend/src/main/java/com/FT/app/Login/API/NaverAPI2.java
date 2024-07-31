package com.FT.app.Login.API;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.FT.app.domain.NaverProfile;
import com.FT.app.domain.NaverUser;
import com.FT.app.Login.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import lombok.RequiredArgsConstructor;

public class NaverAPI2 {
	
	@Autowired
	private UserService userService;
	
    ConfigLoader configLoader = new ConfigLoader();

    String naverSID = configLoader.getNaverSID();
    String naverSPW = configLoader.getNaverSPW();   
    String exIP = configLoader.getExIP();
    
	//인가코드 받은 후 유저정보 및 토큰 전달
	public  NaverUser NaverAPI2(String code, String redNum, String state) { //redNum = redirectNumber
		System.out.println("여기는 API : " + code);
		System.out.println("여기는 API : " + state);
		
		RestTemplate rt = new RestTemplate();

		// HttpHeader 오브젝트 생성
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

		// HttpBody 오브젝트 생성
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("grant_type", "authorization_code");
		params.add("client_id", naverSID);		
		params.add("client_secret", naverSPW);
		params.add("redirect_uri", exIP + ":8200/api/auth/naverLogin/return"+redNum);
		params.add("code", code);
		params.add("state", state);

		// HttpHeader와 HttpBody를 하나의 오브젝트에 담기
		HttpEntity<MultiValueMap<String, String>> naverTokenRequest = new HttpEntity<>(params, headers);

		// Http 요청하기 -> POST방식 -> response 변수의 응답 받음.
		ResponseEntity<String> response = rt.exchange("https://nid.naver.com/oauth2.0/token", HttpMethod.POST,
				naverTokenRequest, String.class);

		System.out.println("response" + response);
		
		JsonParser jParser = new JsonParser();

		JsonObject jObject1 = (JsonObject) jParser.parse(response.getBody()); // json 전체 파싱
		// jObejct1는 json 전체가 파싱됨
		String access_token = jObject1.get("access_token").getAsString();
		String refresh_token = jObject1.get("refresh_token").getAsString();
		
		System.out.println("ACCESS TOKEN : " + access_token);
		System.out.println("REFRESH TOKEN : " + refresh_token);

		ObjectMapper objectMapper = new ObjectMapper();

		RestTemplate rt2 = new RestTemplate();

		// HttpHeader 오브젝트 생성
		HttpHeaders headers2 = new HttpHeaders();
		headers2.add("Authorization", "Bearer " + access_token);
		headers2.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

		// HttpHeader와 HttpBody를 하나의 오브젝트에 담기
		HttpEntity<MultiValueMap<String, String>> naverProfileRequest2 = new HttpEntity<>(headers2);

		// Http 요청하기 -> POST방식 -> response 변수의 응답 받음.
		ResponseEntity<String> response2 = rt2.exchange("https://openapi.naver.com/v1/nid/me", HttpMethod.POST,
				naverProfileRequest2, String.class);

		// naver Object
		ObjectMapper objectMapper2 = new ObjectMapper();
		NaverProfile naverProfile = null;

		// 현재날짜, 시간 구하기(로그인 시간)
		LocalDateTime now = LocalDateTime.now();
		String formatedNow = now.format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH시 mm분 ss초"));

		try {
			naverProfile = objectMapper2.readValue(response2.getBody(), NaverProfile.class);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		// naverProfile 정보 재정의
		NaverUser naverUser = NaverUser.builder().loginId(naverProfile.getResponse().getId())
				.email(naverProfile.getResponse().getEmail())
				.name(naverProfile.getResponse().getName())
				.password("Fastrip123")
				.profile(naverProfile.getResponse().getProfile_image())
				.gender(naverProfile.getResponse().getGender())
				.birthday(naverProfile.getResponse().getBirthday())
				.access_token(access_token)
				.refresh_token(refresh_token)
				.loginId(naverProfile.getResponse().getId())
				.login_date(formatedNow).build();
				

		return naverUser;		
	}
}