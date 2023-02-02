package com.FT.app.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.FT.app.domain.KakaoProfile;
import com.FT.app.domain.User;

public interface KakaoRepository extends JpaRepository<KakaoProfile, Long> {
	//KakaoProfile findByEmail(String email);

}