package com.FT.app.myPage.domain;


import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyColumn;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.Type;

import com.FT.app.domain.Seat;
import com.FT.app.domain.Way;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity // ResList 클래스가 MySQL 테이블에 생성된다.
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@DynamicInsert
public class ResList {
	
	@Id // Primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 프로젝트에서 제작된 DB의 넘버링을 따라간다.
	private int res_no; // 시퀀스, auto_increment
	
	@Column(nullable = false, length = 50)
	private String email;
	
	@ElementCollection
	@CollectionTable(
			name="res_list_add_adult",
			joinColumns = @JoinColumn(name = "res_no")
	)
	@MapKeyColumn(name = "add_adult_key")
	@Column(name = "add_adult_value", length = 5000)
	private Map<String, String> addAdult = new HashMap<>();
	
	@ElementCollection
	@CollectionTable(
			name="res_list_add_child",
			joinColumns = @JoinColumn(name = "res_no")
	)
	@MapKeyColumn(name = "add_child_key")
	@Column(name = "add_child_value", length = 5000)
	private Map<String, String> addChild = new HashMap<>();
	
	@ElementCollection
	@CollectionTable(
			name="res_list_add_infant",
			joinColumns = @JoinColumn(name = "res_no")
	)
	@MapKeyColumn(name = "add_infant_key")
	@Column(name = "add_infant_value", length = 5000)
	private Map<String, String> addInfant = new HashMap<>();
	
	@Column(nullable = false, length = 50)
	private String korName;

	@Column(nullable = false, length = 50)
	private String engName;

	@Column(nullable = false, length = 50)
	private String gender;
	
	@Column(nullable = false, length = 50)
	private String birthday;
	
	@Enumerated(EnumType.STRING)
	private Seat seat; //Enum 사용 (일반석, 이코노미, 비즈니스)
	
	@Column(nullable = false, length = 50)
	private String seatClass1;
	
	@Column(nullable = false, length = 50)
	private String seatClass2;
	
	@Enumerated(EnumType.STRING)
	private Way way; //Enum 사용 (왕복,편도)
	
	@Column(nullable = false, length = 50)
	private String flight1;
	
	@Column(nullable = false, length = 50)
	private String flight2;
	
	@Column(nullable = false, length = 50)
	private String fromArea;
	
	@Column(nullable = false, length = 50)
	private String toArea;
	
	@Column(nullable = false, length = 50)
	private String oneWayArea;
	
	@Column(nullable = false, length = 50)
	private String startDate;
	
	@Column(nullable = false, length = 50)
	private String returnDate;
	
	@Column(nullable = false, length = 10)
	private int adultCount;
	
	@Column(nullable = false, length = 10)
	private int childCount;
	
	@Column(nullable = false, length = 10)
	private int infantCount;
	
	@Column(nullable = false, length = 10)
	private String startTime1;
	
	@Column(nullable = false, length = 10)
	private String arriveTime1;
	
	@Column(nullable = false, length = 10)
	private String startTime2;
	
	@Column(nullable = false, length = 10)
	private String arriveTime2;
	
	@CreationTimestamp // 값을 비워두고 insert해도 시간이 자동으로 입력
	private Timestamp res_date;

	public ResList2 orElseThrow(Object object) {
		return null;
	}

}
