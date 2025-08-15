package com.FT.app.Login.API;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.FT.app.Login.service.UserService;
import com.FT.app.domain.KakaoProfile;
import com.FT.app.domain.KakaoUser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class KakaoAPI {
	
	@Autowired
	private UserService userService;

    ConfigLoader configLoader = new ConfigLoader();
    
    String kakaoID = configLoader.getKakaoSID();
    String exIP = configLoader.getExIP();
    
	// 인가코드 받은 후 유저정보 및 토큰 전달
	public KakaoUser KakaoAPI(String code, String redNum) { // redNum = redirectNumber
		System.out.println("여기는 API : " + code);

		RestTemplate rt = new RestTemplate();
		
		// HttpHeader 오브젝트 생성
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
		// application/x-www-form-urlencoded는 HTML 폼 데이터를 서버로 보내는 데에 사용
		// key=value&key=value의 형태로 전달

		// HttpBody 오브젝트 생성
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("grant_type", "authorization_code");

		params.add("client_id", kakaoID);
		// local
		params.add("redirect_uri", "https://fastrip.store:8200/api/auth/kakaoLogin/main" + redNum);
		params.add("code", code);

		// HttpHeader와 HttpBody를 하나의 오브젝트에 담기
		HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest = new HttpEntity<>(params, headers);

		// Http 요청하기 -> POST방식 -> response 변수의 응답 받음.
		ResponseEntity<String> response = rt.exchange("https://kauth.kakao.com/oauth/token", HttpMethod.POST,
				kakaoTokenRequest, String.class);

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
		HttpEntity<MultiValueMap<String, String>> kakaoProfileRequest2 = new HttpEntity<>(headers2);

		// Http 요청하기 -> POST방식 -> response 변수의 응답 받음.
		ResponseEntity<String> response2 = rt2.exchange("https://kapi.kakao.com/v2/user/me", HttpMethod.POST,
				kakaoProfileRequest2, String.class);

		// Kakao Object
		ObjectMapper objectMapper2 = new ObjectMapper();
		KakaoProfile kakaoProfile = null;

		// 현재날짜, 시간 구하기(로그인 시간)
		LocalDateTime now = LocalDateTime.now();
		String formatedNow = now.format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH시 mm분 ss초"));

		try {
			kakaoProfile = objectMapper2.readValue(response2.getBody(), KakaoProfile.class);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		// KakaoProfile 정보 재정의
		KakaoUser kakaoUser = KakaoUser.builder().loginId(kakaoProfile.getId())
				.email(kakaoProfile.getKakao_account().getEmail()).name(kakaoProfile.getProperties().getNickname())
				.password("Fastrip123") // 임시 비밀번호
				.profile(kakaoProfile.getProperties().getProfile_image())
				.gender(kakaoProfile.getKakao_account().getGender())
				.birthday(kakaoProfile.getKakao_account().getBirthday()).access_token(access_token)
				.refresh_token(refresh_token).loginId(kakaoProfile.getId()).login_date(formatedNow).build();

		return kakaoUser;
	}
}
