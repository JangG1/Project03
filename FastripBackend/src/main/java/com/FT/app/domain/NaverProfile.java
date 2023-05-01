package com.FT.app.domain;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.DynamicInsert;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class NaverProfile {
	public String resultcode;
	public String message;
	public String success;
	public Response response;
	
	@Data
	public class Response {
		public String id;
		public String profile_image;
		public String gender;
		public String email;
		public String name;
		public String birthday;
		public String birthyear;
	}
}
