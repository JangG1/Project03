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
public class ResList2 {
	
	@Id // Primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 프로젝트에서 제작된 DB의 넘버링을 따라간다.
	private int res_no; // 시퀀스, auto_increment
	
	@ElementCollection
	@CollectionTable(
			name="res_list2_add_adult",
			joinColumns = @JoinColumn(name = "res_no")
	)
	@MapKeyColumn(name = "add_adult_key")
	@Column(name = "add_adult_value")
	private Map<String, String> addAdult = new HashMap<>();
	
	public ResList2 orElseThrow(Object object) {
		return null;
	}


}
