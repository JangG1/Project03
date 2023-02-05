package com.FT.app.login.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.FT.app.Repo.MemberMapping;
import com.FT.app.Repo.UserRepository;
import com.FT.app.domain.User;

// 스프링이 컴포넌트 스캔을 통해서 Bean에 등록을 해줌. IoC를 해준다.
@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Transactional
	public List<MemberMapping> 회원찾기(String email) {
		List<MemberMapping> userEmail = userRepository.findByEmail(email);
		return userEmail;
	}
	
	@Transactional
	public int 회원가입(User user) {
		try {
			userRepository.save(user);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("UserService : 회원가입() : "+e.getMessage());
		}
		return -1;
	}
}
