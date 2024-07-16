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
@Data // getter, setter, equals, hashCode, toString 등의 메서드를 자동으로 생성
@NoArgsConstructor // 파라미터 없는 기본 생성자를 자동으로 생성
@AllArgsConstructor // 모든 필드를 파라미터로 받는 생성자를 자동으로 생성
@Builder // 빌더 패턴을 자동으로 생성해주며, 객체를 생성하고 초기화하는 코드를 간결하게 작성할 수 있다.
@DynamicInsert //  Hibernate에서 사용되는 어노테이션으로, INSERT SQL문을 동적으로 생성하여 불필요한 데이터를 제외시키는 기능을 제공
public class ResList {
	
	@Id // Primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 프로젝트에서 제작된 DB의 넘버링을 따라간다.
	private int res_no; // auto_increment
	
	@Column(nullable = false, length = 50)
	private String email;		
	
	@Enumerated(EnumType.STRING)
	private Seat seat; //Enum 사용 (일반석, 프레스티지석, 일등석)
	
	@Column(nullable = false, length = 50)
	private String seatClass1;
	
	@Column(length = 50)
	private String seatClass2;
	
	@Enumerated(EnumType.STRING)
	private Way way; //Enum 사용 (왕복,편도)
	
	@Column(nullable = false, length = 50)
	private String flight1;
	
	@Column(length = 50)
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
	
	@Column(length = 10)
	private String startTime2;
	
	@Column(length = 10)
	private String arriveTime2;
	
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
	
	
	@CreationTimestamp // 값을 비워두고 insert해도 시간이 자동으로 입력
	private Timestamp res_date;

	public ResList orElseThrow(Object object) {
		return null;
	}

}
