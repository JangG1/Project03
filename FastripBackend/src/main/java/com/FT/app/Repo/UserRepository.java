package com.FT.app.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.FT.app.domain.User;
import com.FT.app.myPage.domain.ResList;

public interface UserRepository extends JpaRepository<User, Long> {

	 List<MemberMapping> findByEmail(String email);
	 
	 List<MemberMapping> findAllBy();
}