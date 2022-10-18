package com.FT.app.product.mapper;

import java.time.LocalDateTime;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.FT.app.myPage.domain.ResList;

@Mapper
public interface ProdListMapper {
//	@Select("select count(*) from res")
//	int getCountResList();

//	@Select("select * from res")
//	List<ResList> getResList();
//	
	@Select("select * from prod where email = #{email} order by res_no desc")
	List<ResList> getResListByEmail(@Param("email") String email);
//	
//	@Select("select * from res where email = #{email} and res_no = #{res_no}")
//	ResList getResList(
//			@Param("email") String email,
//			@Param("res_no") int res_no);
//	
	@Insert("insert into prod(seat,way,fromArea,toArea,price) "
			+"values(#{email},#{seat},#{way},#{fromArea},#{toArea},#{price,jdbcType=VARCHAR})")
	int insertResListContent(
			@Param("email") String email,
			@Param("res_date") @DateTimeFormat(iso = ISO.DATE_TIME) LocalDateTime res_date,
			@Param("seat") String kind,
			@Param("fromArea") String input,
			@Param("toArea") String output);
//	
//	@Delete("delete from res where email = #{email} and res_no = #{res_no}")
//	int deleteResListContent(
//     		@Param("email") String email,
//			@Param("res_no") int res_no);
	
}
