package com.FT.app.Login.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.RedirectView;

import com.FT.app.Repo.KakaoUserRepository;
import com.FT.app.Repo.NaverUserRepository;
import com.FT.app.domain.KakaoUser;
import com.FT.app.domain.NaverUser;

import lombok.RequiredArgsConstructor;

// 스프링이 컴포넌트 스캔을 통해서 Bean에 등록을 해줌. IoC를 해준다.
@Service
@RequiredArgsConstructor
public class UserService {

	private final KakaoUserRepository kakaoUserRepository;	
	private final NaverUserRepository naverUserRepository;
	
	@Transactional(readOnly = true)
	public KakaoUser 카카오회원찾기(Long id) {		
		KakaoUser user = kakaoUserRepository.findByLoginId(id).orElseGet(()->{
			return new KakaoUser();
		});		
		return user;
	}
	
	@Transactional(readOnly = true)
	public NaverUser 네이버회원찾기(String id) {		
		NaverUser user = naverUserRepository.findByLoginId(id).orElseGet(()->{
			return new NaverUser();
		});		
		return user;
	}
	
	@Transactional
	public int 카카오회원가입(KakaoUser user) {
		try {
			kakaoUserRepository.save(user);
			return 1;
		} catch (Exception e) {
			return -1;
		}
		
	}
	
	@Transactional
	public int 네이버회원가입(NaverUser user) {
		try {
			naverUserRepository.save(user);
			return 1;
		} catch (Exception e) {
			return -1;
		}
		
	}

}
