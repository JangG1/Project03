package com.FT.app.login;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.FT.app.Repo.ResRepository;
import com.FT.app.Repo.UserRepo;
import com.FT.app.domain.KakaoProfile;
import com.FT.app.domain.User;
import com.FT.app.myPage.domain.ResList;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@RestController
@RequestMapping("/api/*")
public class LoginController {
	@Autowired
	private UserRepo userRepository;
	
	//유저 정보 내역 전부 조회 
	@GetMapping("/kakao/info")
	public List<User> all(){
		return userRepository.findAll();
	}

	// Kakao accessToken 가져오기
	/*@GetMapping("/auth/kakao/accessToken")
	public @ResponseBody String kakaoAccessToken(String code // 인가 코드 code 변수 삽입
			,HttpServletRequest request) { 
		System.out.println(code);

		RestTemplate rt = new RestTemplate();

		// HttpHeader 오브젝트 생성
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

		// HttpBody 오브젝트 생성
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("grant_type", "authorization_code");
		params.add("client_id", "89675f71eb67437191dff96a64831fe8");
		params.add("redirect_uri", "http://localhost:8200/api/auth/kakao/accessToken");
		params.add("code", code);

		// HttpHeader와 HttpBody를 하나의 오브젝트에 담기
		HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest = new HttpEntity<>(params, headers);

		// Http 요청하기 - POST방식으로 - 그리고 response 변수의 응답 받음.
		ResponseEntity<String> response = rt.exchange("https://kauth.kakao.com/oauth/token", HttpMethod.POST,
				kakaoTokenRequest, String.class);

		JsonParser jParser = new JsonParser();

		JsonObject jObject1 = (JsonObject) jParser.parse(response.getBody()); // json 전체 파싱
		// jObejct1는 json 전체가 파싱됨
		String access_token = jObject1.get("access_token").getAsString();

		return access_token;
	}*/

	// Kakao User 정보 가져오기
	@GetMapping("/auth/kakao/callback")
	public @ResponseBody RedirectView kakaoCallback(String code) { // 프론트(Vue)에서 인가 코드 받는 즉시 code 변수 삽입
		System.out.println(code);

		RestTemplate rt = new RestTemplate();

		// HttpHeader 오브젝트 생성
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

		// HttpBody 오브젝트 생성
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("grant_type", "authorization_code");
		params.add("client_id", "89675f71eb67437191dff96a64831fe8");
		params.add("redirect_uri", "http://localhost:8200/api/auth/kakao/callback");
		params.add("code", code);

		// HttpHeader와 HttpBody를 하나의 오브젝트에 담기
		HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest = new HttpEntity<>(params, headers);

		// Http 요청하기 - POST방식으로 - 그리고 response 변수의 응답 받음.
		ResponseEntity<String> response = rt.exchange("https://kauth.kakao.com/oauth/token", HttpMethod.POST,
				kakaoTokenRequest, String.class);

		JsonParser jParser = new JsonParser();

		JsonObject jObject1 = (JsonObject) jParser.parse(response.getBody()); // json 전체 파싱
		// jObejct1는 json 전체가 파싱됨
		String access_token = jObject1.get("access_token").getAsString();

		System.out.println(access_token);

		ObjectMapper objectMapper = new ObjectMapper();

		RestTemplate rt2 = new RestTemplate();

		// HttpHeader 오브젝트 생성
		HttpHeaders headers2 = new HttpHeaders();
		headers2.add("Authorization", "Bearer " + access_token);
		headers2.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

		// HttpHeader와 HttpBody를 하나의 오브젝트에 담기
		HttpEntity<MultiValueMap<String, String>> kakaoProfileRequest2 = new HttpEntity<>(headers2);

		// Http 요청하기 - POST방식으로 - 그리고 response 변수의 응답 받음.
		ResponseEntity<String> response2 = rt2.exchange("https://kapi.kakao.com/v2/user/me", HttpMethod.POST,
				kakaoProfileRequest2, String.class);

		// Kakao Object
		ObjectMapper objectMapper2 = new ObjectMapper();
		KakaoProfile kakaoProfile = null;

		try {
			kakaoProfile = objectMapper2.readValue(response2.getBody(), KakaoProfile.class);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		// User 오브젝트들 : username, email
		System.out.println("카카오 아이디 : " + kakaoProfile.getId());
		System.out.println("카카오 이메일 : " + kakaoProfile.getKakao_account().getEmail());
		System.out.println("카카오 이름 : " + kakaoProfile.getProperties().getNickname());
		System.out.println("카카오 프로필 : " + kakaoProfile.getProperties().getProfile_image());
		System.out.println("카카오 성별 : " + kakaoProfile.getKakao_account().getGender());
		System.out.println("카카오 생일 : " + kakaoProfile.getKakao_account().getBirthday());
		
		System.out.println("Fastrip 유저네임 : " + kakaoProfile.getKakao_account().getEmail()+"_"+kakaoProfile.getId());
		System.out.println("Fastrip 이메일 : " + kakaoProfile.getKakao_account().getEmail());

		System.out.println("카카오 이메일 : " + kakaoProfile.getKakao_account().getEmail());
		System.out.println("카카오 이름 : " + kakaoProfile.getProperties().getNickname());
		System.out.println("카카오 프로필 : " + kakaoProfile.getProperties().getProfile_image());
		System.out.println("카카오 성별 : " + kakaoProfile.getKakao_account().getGender());
		System.out.println("카카오 생일 : " + kakaoProfile.getKakao_account().getBirthday());
		
		//KakaoProfile 정보 재정의
		User kakaoUser = User.builder()
				.email(kakaoProfile.getKakao_account().getEmail())
				.name(kakaoProfile.getProperties().getNickname())
				.profile(kakaoProfile.getProperties().getProfile_image())
				.gender(kakaoProfile.getKakao_account().getGender())
				.birthday(kakaoProfile.getKakao_account().getBirthday())
				.access_token(access_token)
				.build();
		
		//카카오 회원 정보 저장  
		System.out.println(kakaoUser);
		
		userRepository.save(kakaoUser);
		
		// 가입자 혹은 비가입자 체크	 
		
		System.out.println(response2.getBody());

		JsonParser jParser2 = new JsonParser();

		JsonObject jObject2 = (JsonObject) jParser2.parse(response2.getBody()); // json 전체 파싱
		// jObejct1는 json 전체가 파싱됨
		JsonElement properties = jObject2.get("properties");
		JsonElement kakao_account = jObject2.get("kakao_account");
		// String name = jObject2.get("nickname").getAsString();
		// String profile_image = jObject2.get("profile_image").getAsString();

		System.out.println("=========================");
		System.out.println(properties);
		System.out.println(kakao_account);
		// System.out.println(profile_image);

		//프론트 되돌아가기 
		 RedirectView redirectView = new RedirectView();
	       redirectView.setUrl("http://localhost:8080/Test");
	       return redirectView;
	}


	@PostMapping("/login/test")
	public void loginTest(@RequestBody String loginTest) {
		System.out.println(loginTest);
	}
	 

}