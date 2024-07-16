package com.FT.app.Repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.FT.app.domain.KakaoUser;
import com.FT.app.myPage.domain.ResList;

public interface KakaoUserRepository extends JpaRepository<KakaoUser, Long> {
	 
	 Optional<KakaoUser> findByEmail(String email);
	 
	 Optional<KakaoUser> findByLoginId(Long id);
	 
}