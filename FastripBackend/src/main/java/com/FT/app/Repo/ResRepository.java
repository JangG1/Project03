package com.FT.app.Repo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.FT.app.myPage.domain.ResList;

// EntityManager나 SessionFactory와 같은 기술을 사용하면 더 많은 기능을 제어 가능하고 
// 이런 스프링 데이터 JPA 인터페이스는  CRUD중점으로 간단하게 사용이 가능.
// extends JpaRepository<> 를 통해 자동으로 bean이 등록이 돼서 @Repository 생략 가능.
public interface ResRepository extends JpaRepository<ResList, Integer>{//Integer 프라이머리 키 
	 List<ResList> findByEmail(String email);
	 
	 void deleteByEmail(String email);

	 void save(Map<String, Object> resList);

	
}
