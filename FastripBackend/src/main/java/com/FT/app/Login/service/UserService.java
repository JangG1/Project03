package com.FT.app.login.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.RedirectView;

import com.FT.app.Repo.UserRepository;
import com.FT.app.domain.User;

import lombok.RequiredArgsConstructor;

// 스프링이 컴포넌트 스캔을 통해서 Bean에 등록을 해줌. IoC를 해준다.
@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;	
	
	
	@Transactional(readOnly = true)
	public User 회원찾기(String email) {		
		User user = userRepository.findByEmail(email).orElseGet(()->{
			return new User();
		});		
		return user;
	}
	
	/*@Transactional(readOnly = true)
	public User 토큰찾기(String accessToken) {		
		User at = userRepository.findByAccessToken(accessToken).orElseGet(()->{
			return new User();
		});		
		return at;
	}*/
	
	@Transactional
	public int 회원가입(User user) {
		try {
			userRepository.save(user);
			return 1;
		} catch (Exception e) {
			return -1;
		}
		
	}


	

}
