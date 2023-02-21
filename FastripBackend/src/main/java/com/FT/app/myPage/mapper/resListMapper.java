package com.FT.app.myPage.mapper;

import java.time.LocalDateTime;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.FT.app.myPage.domain.ResList2;

@Mapper
public interface ResListMapper {
//	@Select("select count(*) from res")
//	int getCountResList();

//	@Select("select * from res")
//	List<ResList> getResList();
//	
	@Select("select * from res where email = #{email} order by res_no desc")
	List<ResList2> getResListByEmail(@Param("email") String email);
//	
//	@Select("select * from res where email = #{email} and res_no = #{res_no}")
//	ResList getResList(
//			@Param("email") String email,
//			@Param("res_no") int res_no);
//	
	@Insert("insert into res(email,res_date,seat,fromArea,toArea) "
			+"values(#{email},#{res_date},#{seat},#{fromArea},#{toArea,jdbcType=VARCHAR})")
	int insertResListContent(
			@Param("email") String email,
			@Param("res_date") @DateTimeFormat(iso = ISO.DATE_TIME) LocalDateTime res_date,
			@Param("seat") String kind,
			@Param("fromArea") String input,			
			@Param("Date") String date,
			@Param("toArea") String output);
	
//	
//	@Delete("delete from res where email = #{email} and res_no = #{res_no}")
//	int deleteResListContent(
//     		@Param("email") String email,
//			@Param("res_no") int res_no);
	
}
