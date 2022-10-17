package com.FT.app.myPage;

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

import com.FT.app.myPage.domain.ResList;
import com.FT.app.myPage.domain.Seat;
import com.FT.app.myPage.domain.Way;
import com.FT.app.myPage.mapper.ResListMapper;
import com.FT.app.myPage.repository.ResRepository;

@RestController
@RequestMapping("/res/*")
public class ResController {
	@Autowired
	private ResRepository resRepository;
	private ResListMapper mapper;
	
	@GetMapping("/{id}")
	public ResList detail(@PathVariable int id){
		ResList resList = resRepository.findById(id).orElseThrow(()-> {
					return new IllegalArgumentException("없는 정보 입니다.");	
		});
		return resList;
	}
	
	@PostMapping("/test")
	public void resListTest(@RequestBody ResList resList) {
		System.out.println(resList.getEmail());		
		System.out.println(resList.getFromArea());
		System.out.println(resList.getToArea());
		System.out.println(resList.getRes_date());
		
		resList.setWay(Way.왕복);
		resList.setSeat(Seat.일반석);
		resRepository.save(resList);
		System.out.println(resList);
	}
	

	
	//ResList 보관함에 저장
	@PostMapping("/resList/upload")
	public int upload(
			@RequestParam("email") String email,
			@RequestParam("res_date") @DateTimeFormat(iso = ISO.DATE_TIME) LocalDateTime res_date,
			@RequestParam("seat") String seat,
			@RequestParam("fromArea") String fromArea,
			@RequestParam("toArea") String toArea,
			HttpServletRequest request)throws IOException {
		System.out.println("email : " + email + "res_date : " + res_date + " seat : " + seat + "fromArea : " + fromArea + "toArea : " + toArea);
		return mapper.insertResListContent(email,res_date,seat,fromArea,toArea);
	}
	
	//voice_trans 보관함 아이템 불러오기 email 기준 전부
	@GetMapping("/resList/list/{email}")
	public List<ResList> getList(
			@PathVariable("email") String email){
		return mapper.getResListByEmail(email);
	}
	
}
