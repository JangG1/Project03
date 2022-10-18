package com.FT.app.product.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.function.Supplier;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.FT.app.Repo.ProdRepository;
import com.FT.app.Repo.ResRepository;
import com.FT.app.domain.Seat;
import com.FT.app.domain.Way;
import com.FT.app.myPage.domain.ResList;
import com.FT.app.myPage.mapper.ResListMapper;
import com.FT.app.product.domain.ProdList;
import com.FT.app.product.mapper.ProdListMapper;

@RestController
@RequestMapping("/prod/*")
public class ProdController {
	@Autowired
	private ProdRepository prodRepository;
	private ProdListMapper mapper;
	
	@GetMapping("/all")
	public List<ProdList> all(){
		return prodRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public ProdList detail(@PathVariable int id){
		ProdList resList = prodRepository.findById(id).orElseThrow(()-> {
					return new IllegalArgumentException("없는 정보 입니다.");	
		});
		return resList;
	}
	
	@PostMapping("/test")
	public void prodListTest(@RequestBody ProdList prodList) {
		
	}
	
	
	//voice_trans 보관함 아이템 불러오기 email 기준 전부
	@GetMapping("/resList/list/{email}")
	public List<ResList> getList(
			@PathVariable("email") String email){
		return mapper.getResListByEmail(email);
	}
	
}
