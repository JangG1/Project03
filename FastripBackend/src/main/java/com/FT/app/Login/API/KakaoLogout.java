package com.FT.app.login.API;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.FT.app.domain.KakaoProfile;
import com.FT.app.login.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import lombok.RequiredArgsConstructor;

public class KakaoLogout {

	//인가코드 받은 후 유저정보 및 토큰 전달
	public  ResponseEntity<String> KakaoLogout() { //redNum = redirectNumber
		System.out.println("1");
		RestTemplate rt = new RestTemplate();

		// HttpBody 오브젝트 생성
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("client_id", "");
		params.add("logout_redirect_uri", "http://localhost:8080/Test");
		System.out.println("2");
		// HttpHeader와 HttpBody를 하나의 오브젝트에 담기
		HttpEntity<MultiValueMap<String, String>> kakaoLogoutRequest = new HttpEntity<>(params);
		System.out.println("3");
		// Http 요청하기 -> POST방식 -> response 변수의 응답 받음.
		ResponseEntity<String> response = rt.exchange("https://kauth.kakao.com/oauth/logout", HttpMethod.GET,
				kakaoLogoutRequest, String.class);
		System.out.println("4");
		System.out.println(response);
		
		return response;		
	}
}