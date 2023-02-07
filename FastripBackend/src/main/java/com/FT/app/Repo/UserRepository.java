package com.FT.app.Repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.FT.app.domain.User;
import com.FT.app.myPage.domain.ResList;

public interface UserRepository extends JpaRepository<User, Long> {
	 
	 
	 Optional<User> findByEmail(String email);

	//String 회원찾기(String email);
	 
	 //Optional<User> findByAccessToken(String accessToken);
}