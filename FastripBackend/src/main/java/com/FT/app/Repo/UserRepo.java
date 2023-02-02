package com.FT.app.Repo;

import java.util.HashMap;

import org.springframework.data.jpa.repository.JpaRepository;

import com.FT.app.domain.KakaoProfile;
import com.FT.app.domain.User;

public interface UserRepo extends JpaRepository<User, Long> {

	void save(HashMap<String, Object> kakaoMap);
	//User findByEmail(String email);

}