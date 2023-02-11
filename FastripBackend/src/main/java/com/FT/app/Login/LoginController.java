package com.FT.app.login;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.json.JSONParser;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
import com.FT.app.Repo.UserRepository;
import com.FT.app.domain.KakaoProfile;
import com.FT.app.domain.User;
import com.FT.app.login.API.KakaoAPI;
import com.FT.app.login.service.UserService;
import com.FT.app.myPage.domain.ResList;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor // 의존성 주입 final 필요(02/07 Service 호출 시 NPE 발생)
@RequestMapping("/api/*")
public class LoginController {

	private final UserRepository userRepository;

	@Autowired
	private UserService userService;

	// 유저 정보 내역 전부 조회
	@GetMapping("/kakao/info")
	public List<User> all() {
		return userRepository.findAll();
	}
	
	//로그인 email 기준 예약 내역 전부 조회
		/*@GetMapping("/kakao/info/{email}")
		public String  getEmailList(@PathVariable("email") String email) {		
			return userRepository.회원찾기(email);
		}*/

	// Kakao 로그아웃
	@GetMapping("/kakao/logout/{access_token}")
	public String kakaoLogout(@PathVariable("access_token") String access_token) {
		System.out.println("Access Token : " + access_token);

		RestTemplate rt = new RestTemplate();

		// HttpHeader 오브젝트 생성
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Bearer " + access_token);

		// HttpHeader와 HttpBody를 하나의 오브젝트에 담기
		HttpEntity<MultiValueMap<String, String>> kakaoProfileRequest = new HttpEntity<>(headers);

		// Http 요청하기 -> POST방식 -> response 변수의 응답 받음.
		ResponseEntity<String> response = rt.exchange("https://kapi.kakao.com/v1/user/logout", HttpMethod.POST,
				kakaoProfileRequest, String.class);
 
		System.out.println("로그아웃 id : " + response.getBody());
		return "로그아웃 되었습니다.";           
	}          
  
	// Kakao User 정보 가져오기
	@GetMapping("/auth/kakao/callback")
	public @ResponseBody RedirectView kakaoCallback(String code) { // 프론트(Vue)에서 인가 코드 받는 즉시 code 변수 삽입
		System.out.println("인가 코드 : " + code);

		KakaoAPI kakaoAPI = new KakaoAPI();
		//KakaoAPI 인가 코드 전송 및 유저 정보 응답
		String redNum = ""; //redNum = redirectNumber
		
		User kakaoUser = (User) kakaoAPI.KakaoAPI(code,redNum);
		
		// 기존 회원 찾기(중복)
		User originUser = userService.회원찾기(kakaoUser.getEmail());

		// 기존 회원 아닐시 새로 등록
		if (originUser.getEmail() == null) {
			System.out.println("기존 회원이 아니기에 자동 회원가입을 진행합니다");
			userService.회원가입(kakaoUser);
		}

		System.out.println("자동 로그인을 진행합니다.");
		System.out.println("id : " + kakaoUser.getId());
		
		// 로그인 처리
//		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(kakaoUser.getEmail(),"Fastrip123"));
//		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		// 프론트로 리다이렉트
		RedirectView redirectView = new RedirectView();
		redirectView.setUrl("http://localhost:8080/");
		return redirectView;
	}

	// Kakao User 정보 가져오기(도착지 선택 페이지에서 결제 페이지 넘어갈시 로그인이 필요한 경우)
	@GetMapping("/auth/kakao/callback2")
	public @ResponseBody RedirectView kakaoCallback2(String code) { // 프론트(Vue)에서 인가 코드 받는 즉시 code 변수 삽입
		System.out.println("인가 코드 : " + code);

		KakaoAPI kakaoAPI2 = new KakaoAPI();
		//KakaoAPI 인가 코드 전송 및 유저 정보 응답		
		System.out.println("1");
		String redNum = "2"; //redNum = redirectNumber
		
		User kakaoUser2 = (User) kakaoAPI2.KakaoAPI(code, redNum); 
		System.out.println("2" + kakaoUser2);
		// 기존 회원 찾기(중복)
		User originUser = userService.회원찾기(kakaoUser2.getEmail());
		System.out.println("3");
		// 기존 회원 아닐시 새로 등록
		if (originUser.getEmail() == null) {
			System.out.println("기존 회원이 아니기에 자동 회원가입을 진행합니다");
			userService.회원가입(kakaoUser2);
		}

		System.out.println("자동 로그인을 진행합니다.");
		System.out.println("id : " + kakaoUser2.getId());

		// 프론트로 리다이렉트 
		RedirectView redirectView = new RedirectView();
		redirectView.setUrl("http://localhost:8080/Arrival");
		return redirectView;
	}
	
}
