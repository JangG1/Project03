package com.FT.app.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.FT.app.domain.KakaoProfile;
import com.FT.app.domain.User;

public interface UserRepo extends JpaRepository<User, Long> {

	void save(UserRepo user);
	//User findByEmail(String email);

}