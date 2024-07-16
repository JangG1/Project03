package com.FT.app.Repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.FT.app.domain.KakaoUser;
import com.FT.app.domain.NaverUser;
import com.FT.app.myPage.domain.ResList;

public interface NaverUserRepository extends JpaRepository<NaverUser, Long> {
	 
	 Optional<NaverUser> findByLoginId(String id);
	 
}