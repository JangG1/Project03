package com.FT.app.Repo;

import java.util.HashMap;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.FT.app.myPage.domain.ResList;

// 자동으로 bean등록이 된다.
// @Repository 생략 가능.
public interface ResRepository extends JpaRepository<ResList, Integer>{//Integer 프라이머리 키 
	 List<ResList> findByEmail(String email);
	 
	 void deleteByEmail(String email);

	void save(String testValue);
	 
	//void save(HashMap<String, Object> resList);
}
