package com.FT.app.Login;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.persistence.Column;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import com.FT.app.Repo.KakaoUserRepository;
import com.FT.app.domain.KakaoProfile;
import com.FT.app.domain.KakaoUser;
import com.FT.app.Login.API.ConfigLoader;
import com.FT.app.Login.API.KakaoAPI;
import com.FT.app.Login.API.KakaoAPI2;
import com.FT.app.Login.service.UserService;
import com.FT.app.myPage.domain.ResList;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import lombok.RequiredArgsConstructor;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;

@RestController
@RequiredArgsConstructor //클래스에 선언된 final 변수들, 필드들을 매개변수로 하는 생성자를 자동으로 생성해주는 어노테이션
public class KakaoLoginController {

	//  private final > @Autowired
	// 순환참조 방지, 불변성을 얻을 수 있음
	private final KakaoUserRepository kakaoUserRepository;

	private final UserService userService;

    ConfigLoader configLoader = new ConfigLoader();
        
    String exIP = configLoader.getExIP();
    
	// Kakao User 정보 가져오기
	@GetMapping("/auth/kakaoLogin/main")
	public @ResponseBody RedirectView kakaoCallback(String code) {
	    System.out.println("인가 코드 : " + code);

	    KakaoAPI kakaoAPI = new KakaoAPI();
	    String redNum = ""; // redNum = redirectNumber

	    // KakaoAPI 인가 코드 전송 및 유저 정보 응답
	    KakaoUser kakaoUser = (KakaoUser) kakaoAPI.KakaoAPI(code, redNum);
	    System.out.println(kakaoUser);

	    // 기존 회원 찾기(중복)
	    KakaoUser originUser = userService.카카오회원찾기(kakaoUser.getLoginId());

	    if (originUser.getLoginId() != null) {
	        System.out.println(originUser.getName() + "님 환영합니다");

	        // 동의 항목 미체크시
	        originUser.setEmail(kakaoUser.getEmail() == null ? "" : kakaoUser.getEmail());
	        originUser.setGender(kakaoUser.getGender() == null ? "" : kakaoUser.getGender());
	        originUser.setBirthday(kakaoUser.getBirthday() == null ? "" : kakaoUser.getBirthday());

	        // 현재날짜, 시간 구하기(로그인 시간)
	        LocalDateTime now = LocalDateTime.now();
	        String formatedNow = now.format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH시 mm분 ss초"));

	        // 기존 정보 업데이트(자주 바뀌는 정보)
	        originUser.setProfile(kakaoUser.getProfile());
	        originUser.setAccess_token(kakaoUser.getAccess_token());
	        originUser.setRefresh_token(kakaoUser.getRefresh_token());
	        originUser.setLogin_date(formatedNow);
	        userService.카카오회원가입(originUser); // 기존 정보 수정 후 저장
	    } else {
	        System.out.println("기존 회원이 아니기에 자동 회원가입을 진행합니다");
	        kakaoUser.setEmail(kakaoUser.getEmail() == null ? "" : kakaoUser.getEmail());
	        kakaoUser.setGender(kakaoUser.getGender() == null ? "" : kakaoUser.getGender());
	        kakaoUser.setBirthday(kakaoUser.getBirthday() == null ? "" : kakaoUser.getBirthday());
	        userService.카카오회원가입(kakaoUser);
	    }

	    // JWT 생성
	    String jwt = Jwts.builder()
	            .claim("email", originUser.getEmail())
	            .claim("name", originUser.getName())
	            .claim("profile", originUser.getProfile())
	            .claim("gender", originUser.getGender())
	            .claim("birthday", originUser.getBirthday())
	            .claim("access_token", originUser.getAccess_token())
	            .claim("refreshtoken", originUser.getRefresh_token())
	            .claim("OAuth", "kakao")
	            .setIssuedAt(new Date())
	            .setExpiration(new Date(System.currentTimeMillis() + 180000)) // 3분 유효기간
	            .signWith(SignatureAlgorithm.HS256, "Fastrip_JWT_SK") //HS256(암호 알고리즘)
	            .compact();

	    // 프론트로 리다이렉트
	    RedirectView redirectView = new RedirectView();
	    //redirectView.setUrl(exIP + "/?token=" + jwt);	    //로컬용
	    redirectView.setUrl("https://fastrip.store/?token=" + jwt);
	    return redirectView;
	}

	// Kakao User 정보 가져오기(도착지 선택 페이지(Arrive)에서 결제 페이지 넘어갈시 로그인이 필요한 경우)
	@GetMapping("/auth/kakaoLogin/return")
	public @ResponseBody RedirectView kakaoCallback2(String code) {
	    System.out.println("인가 코드 : " + code);

	    KakaoAPI2 kakaoAPI2 = new KakaoAPI2();
	    String redNum = ""; // redNum = redirectNumber
	    
	    // KakaoAPI 인가 코드 전송 및 유저 정보 응답
	    KakaoUser kakaoUser = (KakaoUser) kakaoAPI2.KakaoAPI(code, redNum);
	    System.out.println("kakaoUser" + kakaoUser);

	    // 기존 회원 찾기(중복)
	    KakaoUser originUser = userService.카카오회원찾기(kakaoUser.getLoginId());

	    if (originUser.getLoginId() != null) {
	        System.out.println(originUser.getName() + "님 환영합니다");

	        originUser.setEmail(kakaoUser.getEmail() == null ? "" : kakaoUser.getEmail());
	        originUser.setGender(kakaoUser.getGender() == null ? "" : kakaoUser.getGender());
	        originUser.setBirthday(kakaoUser.getBirthday() == null ? "" : kakaoUser.getBirthday());

	        LocalDateTime now = LocalDateTime.now();
	        String formatedNow = now.format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH시 mm분 ss초"));

	        originUser.setProfile(kakaoUser.getProfile());
	        originUser.setAccess_token(kakaoUser.getAccess_token());
	        originUser.setRefresh_token(kakaoUser.getRefresh_token());
	        originUser.setLogin_date(formatedNow);

	        userService.카카오회원가입(originUser);
	    } else {
	        System.out.println("기존 회원이 아니기에 자동 회원가입을 진행합니다");

	        kakaoUser.setEmail(kakaoUser.getEmail() == null ? "" : kakaoUser.getEmail());
	        kakaoUser.setGender(kakaoUser.getGender() == null ? "" : kakaoUser.getGender());
	        kakaoUser.setBirthday(kakaoUser.getBirthday() == null ? "" : kakaoUser.getBirthday());

	        userService.카카오회원가입(kakaoUser);
	    }

	    // JWT 생성
	    String jwt = Jwts.builder()
	            .claim("email", originUser.getEmail())
	            .claim("name", originUser.getName())
	            .claim("profile", originUser.getProfile())
	            .claim("gender", originUser.getGender())
	            .claim("birthday", originUser.getBirthday())
	            .claim("access_token", originUser.getAccess_token())
	            .claim("refreshtoken", originUser.getRefresh_token())
	            .claim("OAuth", "kakao")
	            .setIssuedAt(new Date())
	            .setExpiration(new Date(System.currentTimeMillis() + 180000)) // 1일 유효기간
	            .signWith(SignatureAlgorithm.HS256, "secretkey")
	            .compact();

	    RedirectView redirectView = new RedirectView();	    
	    //redirectView.setUrl(exIP + "/Return?token=" + jwt);
	    redirectView.setUrl("https://fastrip.store/Return?token=" + jwt);
	    return redirectView;
	}

}