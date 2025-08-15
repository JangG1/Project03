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
public class KakaoProfile {
	public Long id;
	public String connected_at;
	public Properties properties; 
	public KakaoAccount kakao_account;
	
	@Data
	public static class Properties {
		public String nickname;
		public String profile_image;
		public String thumbnail_image;
	}
	
	@Data
	public static class KakaoAccount {
		public Boolean profile_nickname_needs_agreement;
		public Boolean profile_image_needs_agreement;
		public Profile profile;
		public Boolean has_email;
		public Boolean email_needs_agreement;
		public Boolean is_email_valid;
		public Boolean is_email_verified;
		public String email;
		public Boolean has_birthday;
		public Boolean birthday_needs_agreement;
		public String birthday;
		public String birthday_type;
		public Boolean is_leap_month;
		public Boolean has_gender;
		public Boolean gender_needs_agreement;
		public String gender;
		
		@Data
		public static class Profile {
			public String nickname;
			public String thumbnail_image_url;
			public String profile_image_url;
			public Boolean is_default_image;
			public Boolean is_default_nickname; //기존 API 응답에서 추가된 항목. (추가 인식 전 매핑 오류 발생)
		}
	}
}
