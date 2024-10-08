package com.FT.app.Login;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
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
import org.springframework.http.MediaType;
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
import com.FT.app.Repo.NaverUserRepository;
import com.FT.app.domain.NaverUser;
import com.FT.app.domain.NaverUser;
import com.FT.app.Login.API.ConfigLoader;
import com.FT.app.Login.API.NaverAPI;
import com.FT.app.Login.API.NaverAPI2;
import com.FT.app.Login.service.UserService;
import com.FT.app.myPage.domain.ResList;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class NaverLoginController {

	//  private final > @Autowired
	// 순환참조 방지, 불변성을 얻을 수 있음
	private final NaverUserRepository naverUserRepository;

	private final UserService userService;

    ConfigLoader configLoader = new ConfigLoader();
    
    String exIP = configLoader.getExIP();
    
	// Naver User 정보 가져오기
	@GetMapping("/auth/naverLogin/main")
	public @ResponseBody RedirectView naverCallback(String code, String state) { // 프론트(Vue)에서 인가 코드 받는 즉시 code 변수 삽입
		System.out.println("인가 코드 : " + code);
		System.out.println("state : " + state);
		
		NaverAPI naverAPI = new NaverAPI();
		String redNum = ""; // redNum = redirectNumber

		// NaverAPI 인가 코드 전송 및 유저 정보 응답
		NaverUser naverUser = (NaverUser) naverAPI.NaverAPI(code, redNum, state);
		System.out.println("naverUser" + naverUser);

		// 기존 회원 찾기(중복)
		NaverUser originUser = userService.네이버회원찾기(naverUser.getLoginId());

		if (originUser.getLoginId() != null) {
			System.out.println(originUser.getName() + "님 환영합니다");

			// 현재날짜, 시간 구하기(로그인 시간)
			LocalDateTime now = LocalDateTime.now();
			String formatedNow = now.format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH시 mm분 ss초"));

			// 기존 정보 업데이트(자주 바뀌는 정보)
			originUser.setProfile(naverUser.getProfile());
			originUser.setAccess_token(naverUser.getAccess_token());
			originUser.setRefresh_token(naverUser.getRefresh_token());
			originUser.setLogin_date(formatedNow);

			userService.네이버회원가입(originUser); // 기존 정보 수정 후 저장
		}

		// 기존 회원 아닐시 새로 등록
		if (originUser.getLoginId() == null) {
			System.out.println("기존 회원이 아니기에 자동 회원가입을 진행합니다");

			userService.네이버회원가입(naverUser);
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
	            .claim("OAuth", "naver")
	            .setIssuedAt(new Date())
	            .setExpiration(new Date(System.currentTimeMillis() + 180000)) // 1일 유효기간
	            .signWith(SignatureAlgorithm.HS256, "secretkey")
	            .compact();

	    // 프론트로 리다이렉트
	    RedirectView redirectView = new RedirectView();
	    //redirectView.setUrl(exIP + "/?token=" + jwt);
	    redirectView.setUrl("http://fastrip.shop/?token=" + jwt);
	    return redirectView;
	}

	// Naver User 정보 가져오기(도착지 선택 페이지(Arrive)에서 결제 페이지 넘어갈시 로그인이 필요한 경우)
		@GetMapping("/auth/naverLogin/return")
		public @ResponseBody RedirectView naverCallback2(String code, String state) { // 프론트(Vue)에서 인가 코드 받는 즉시 code 변수 삽입
			System.out.println("인가 코드 : " + code);
			System.out.println("state : " + state);

			NaverAPI2 naverAPI2 = new NaverAPI2();
			String redNum = ""; // redNum = redirectNumber

			// NaverAPI 인가 코드 전송 및 유저 정보 응답
			NaverUser naverUser = (NaverUser) naverAPI2.NaverAPI2(code, redNum, state);
			System.out.println("naverUser" + naverUser);

			// 기존 회원 찾기(중복)
			NaverUser originUser = userService.네이버회원찾기(naverUser.getLoginId());

			if (originUser.getLoginId() != null) {
				System.out.println(originUser.getName() + "님 환영합니다");

				// 현재날짜, 시간 구하기(로그인 시간)
				LocalDateTime now = LocalDateTime.now();
				String formatedNow = now.format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH시 mm분 ss초"));

				// 기존 정보 업데이트(자주 바뀌는 정보)
				originUser.setProfile(naverUser.getProfile());
				originUser.setAccess_token(naverUser.getAccess_token());
				originUser.setRefresh_token(naverUser.getRefresh_token());
				originUser.setLogin_date(formatedNow);

				userService.네이버회원가입(originUser); // 기존 정보 수정 후 저장
			}

			// 기존 회원 아닐시 새로 등록
			if (originUser.getLoginId() == null) {
				System.out.println("기존 회원이 아니기에 자동 회원가입을 진행합니다");

				userService.네이버회원가입(naverUser);
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
		            .claim("OAuth", "naver")
		            .setIssuedAt(new Date())
		            .setExpiration(new Date(System.currentTimeMillis() + 180000)) // 1일 유효기간
		            .signWith(SignatureAlgorithm.HS256, "secretkey")
		            .compact();

		    // 프론트로 리다이렉트
		    RedirectView redirectView = new RedirectView();		    
		    //redirectView.setUrl(exIP + "/Return?token=" + jwt);
		    redirectView.setUrl("http://fastrip.shop/Return?token=" + jwt);
		    return redirectView;
		}

	

}
