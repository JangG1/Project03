package com.FT.app.login;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

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

@RestController
@RequestMapping("/api/*")
public class LoginController {

	KakaoAPI kakaoApi = new KakaoAPI();
		
		@GetMapping(value = "/test")
		@ResponseBody
		public String Test() {
			return "test 중";
		}
		
		@GetMapping("/auth/kakao/callback")		
		public @ResponseBody String kakaoCallback(String code) {
			System.out.println(code);
			//String key = "89675f71eb67437191dff96a64831fe8";
			//String redirectURL = "http://localhost:8080/Test";
			
			RestTemplate rt = new RestTemplate();		
			
			//HttpHeader 오브젝트 생성
			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-type","application/x-www-form-urlencoded;charset=utf-8");
			
			//HttpBody 오브젝트 생성
			MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
			params.add("grant_type", "authorization_code");
			params.add("client_id", "89675f71eb67437191dff96a64831fe8");
			params.add("redirect_uri", "http://localhost:8080/Test");
			params.add("code", code);			
			
			//HttpHeader와 HttpBody를 하나의 오브젝트에 담기
			HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest =
					new HttpEntity<>(params, headers);
			
			// Http 요청하기 - POST방식으로 - 그리고 response 변수의 응답 받음.
			ResponseEntity<String> response = rt.exchange(
					"https://kauth.kakao.com/oauth/token",
					HttpMethod.POST,
					kakaoTokenRequest,
					String.class
			);
			
			System.out.println(response.getBody());
			
			return response.getBody();
		}
		
		@PostMapping("/login/test")
		public void loginTest(@RequestBody String  loginTest) {
			System.out.println(loginTest);

		}
	
	@RequestMapping(value="/login")
	public ModelAndView login(@RequestParam("code") String code, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		// 1번 인증코드 요청 전달
		String accessToken = kakaoApi.getAccessToken(code);
		// 2번 인증코드로 토큰 전달
		
		HashMap<String, Object> userInfo = kakaoApi.getUserInfo(accessToken);
		
		System.out.println("login info : " + userInfo.toString());
		
		if(userInfo.get("email") != null) {
			session.setAttribute("userId", userInfo.get("email"));
			session.setAttribute("accessToken", accessToken);
		}
		mav.addObject("userId", userInfo.get("email"));
		mav.setViewName("index");
		return mav;
	}
	
	@RequestMapping(value="/logout")
	public ModelAndView logout(HttpSession session) {
		ModelAndView mav = new ModelAndView();
		
		kakaoApi.kakaoLogout((String)session.getAttribute("accessToken"));
		session.removeAttribute("accessToken");
		session.removeAttribute("userId");
		mav.setViewName("index");
		return mav;
	}
	

	
}