package com.FT.app.product.domain;


import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

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
public class ProdList {
	
	@Id // Primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 프로젝트에서 제작된 DB의 넘버링을 따라간다.
	private int prod_no; // 시퀀스, auto_increment
	
	@Enumerated(EnumType.STRING)
	private Seat seat; //Enum 사용 (일반석, 이코노미, 비즈니스)
	
	@Enumerated(EnumType.STRING)
	private Way way; //Enum 사용 (왕복,편도)
	
	@Column(nullable = false, length = 50)
	private String area;
	
	@Column(nullable = false, length = 100)
	private String price;



	

	
}
