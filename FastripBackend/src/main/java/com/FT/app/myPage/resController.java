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
import java.util.Map;
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

import com.FT.app.Repo.ResRepository;
import com.FT.app.Repo.ResRepository2;
import com.FT.app.Repo.StudentRepository;
import com.FT.app.domain.Seat;
import com.FT.app.domain.Way;
import com.FT.app.myPage.domain.AdditionalData;
import com.FT.app.myPage.domain.ResList;
import com.FT.app.myPage.domain.ResList2;
import com.FT.app.myPage.domain.Student;
import com.FT.app.myPage.mapper.ResListMapper;

@RestController
@RequestMapping("/res/*")
public class ResController {
	@Autowired
	private ResRepository resRepository;
	private ResRepository2 resRepository2;
	private ResListMapper mapper;
	private StudentRepository  studentRepository;
	
	@GetMapping("/all")
	public List<ResList> all(){
		return resRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public ResList detail(@PathVariable int id){
		ResList resList = resRepository.findById(id).orElseThrow(()-> {
					return new IllegalArgumentException("없는 정보 입니다.");	
		});
		return resList;
	}
	
	@PostMapping("/resPostTest")
	public void resTest(@RequestBody ResList2  resList2) {
		System.out.println(resList2.getEmail());
		System.out.println(resList2.getName());
		System.out.println(resList2.getGender());
		System.out.println(resList2.getBirthday());		
		
		System.out.println(resList2);				
		System.out.println("연결 성공");
		
		resRepository2.save(resList2);
	}
	
	@PostMapping("/student")
	public void student(@RequestBody Student  student) {

		System.out.println(student);				
		System.out.println("연결 성공");
		
	}

	
	@PostMapping("/resPost")
	public void Test(@RequestBody ResList resList) {
		System.out.println(resList.getEmail());
		System.out.println(resList.getAddPassKorName());
		System.out.println(resList.getAddPassEngName());
		System.out.println(resList.getAddPassGender());
		System.out.println(resList.getAddPassBirthday());
				
		System.out.println(resList.getKorName());
		System.out.println(resList.getEngName());

		System.out.println(resList.getGender());
		System.out.println(resList.getBirthday());
		System.out.println(resList.getSeat());
		System.out.println(resList.getSeatClass1());
		System.out.println(resList.getSeatClass2());
		System.out.println(resList.getWay());
		System.out.println(resList.getFlight1());
		System.out.println(resList.getFlight2());
		System.out.println(resList.getFromArea());
		System.out.println(resList.getToArea());
		System.out.println(resList.getOneWayArea());
		System.out.println(resList.getStartDate());
		System.out.println(resList.getReturnDate());
		System.out.println(resList.getAdultCount());
		System.out.println(resList.getChildCount());
		System.out.println(resList.getInfantCount());
		System.out.println(resList.getStartTime1());
		System.out.println(resList.getArriveTime1());
		System.out.println(resList.getStartTime2());
		System.out.println(resList.getArriveTime2());
		
		System.out.println(resList.getRes_date());
		
		resRepository.save(resList);
		System.out.println(resList);
	}
	
	/*@PostMapping("/test")
	public void resListTest(@RequestBody ResList resList) {
		System.out.println(resList.getEmail());		
		System.out.println(resList.getFromArea());
		System.out.println(resList.getToArea());
		System.out.println(resList.getRes_date());
		
		resRepository.save(resList);
		System.out.println(resList);
	}*/
	
	/*@PostMapping("/test")
	public void resListTest(@RequestBody ResList resList) {
		System.out.println(resList.getEmail());
		System.out.println(resList.getSeat());
		System.out.println(resList.getDate());
		System.out.println(resList.getWay());		
		System.out.println(resList.getFromArea());
		System.out.println(resList.getToArea());
		System.out.println(resList.getAdultCount());
		System.out.println(resList.getChildCount());
		System.out.println(resList.getInfantCount());
		System.out.println(resList.getRes_date());
				
		resRepository.save(resList);
		System.out.println(resList);
	}*/
	
	//voice_trans 보관함 아이템 불러오기 email 기준 전부
	@GetMapping("/resList/list/{email}")
	public List<ResList> getList(
			@PathVariable("email") String email){
		return mapper.getResListByEmail(email);
	}
	
}
