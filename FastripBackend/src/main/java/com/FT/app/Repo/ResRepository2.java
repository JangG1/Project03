package com.FT.app.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.FT.app.myPage.domain.ResList2;

// 자동으로 bean등록이 된다.
// @Repository 생략 가능.
public interface ResRepository2 extends JpaRepository<ResList2, Integer>{//Integer 프라이머리 키 

}
