package com.FT.app.Repo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.FT.app.myPage.domain.AddPassenger;

// 자동으로 bean등록이 된다.
// @Repository 생략 가능.
public interface ResRepository2 extends JpaRepository<AddPassenger, Integer>{

	void save(Map<String, String> addAdult);


}
