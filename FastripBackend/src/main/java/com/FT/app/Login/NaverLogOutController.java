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

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class NaverLogOutController {

	//  private final > @Autowired
	// 순환참조 방지, 불변성을 얻을 수 있음
	private final NaverUserRepository naverUserRepository;

	private final UserService userService;

	  ConfigLoader configLoader = new ConfigLoader();

	    String naverSID = configLoader.getNaverSID();
	    String naverSPW = configLoader.getNaverSPW();   
	    
	// Naver 로그아웃
	@GetMapping("/naver/logout/main/{access_token}")
	public String naverLogout(@PathVariable("access_token") String access_token) {
	    String clientId = naverSID;
	    String clientSecret = naverSPW;
	    String url = "https://nid.naver.com/oauth2.0/token?grant_type=delete&client_id={clientId}&client_secret={clientSecret}&service_provider=NAVER&access_token={accessToken}";

	    RestTemplate rt = new RestTemplate();
	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);

	    HttpEntity<String> request = new HttpEntity<>(headers);

	    String result = rt.postForObject(url, request, String.class, clientId, clientSecret, access_token);
	    System.out.println(result);

	    return "로그아웃 되었습니다.";
	}

}
