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
import com.FT.app.product.domain.ProdList;

@Mapper
public interface ProdListMapper {
//	@Select("select count(*) from res")
//	int getCountProdList();

//	@Select("select * from res")
//	List<ProdList> getResList();
//	
	@Select("select * from prod where email = #{email} order by res_no desc")
	List<ProdList> getResListByEmail(@Param("email") String email);
//	
//	@Select("select * from res where email = #{email} and res_no = #{prod_no}")
//	ResList getProdList(
//			@Param("email") String email,
//			@Param("prod_no") int prod_no);
//	
	@Insert("insert into prod(seat,way,fromArea,toArea,price) "
			+"values(#{email},#{seat},#{way},#{area},#{price,jdbcType=VARCHAR})")
	int insertProdListContent(
			@Param("prod_date") @DateTimeFormat(iso = ISO.DATE_TIME) LocalDateTime prod_date,
			@Param("seat") String seat,
			@Param("area") String area,
			@Param("way") String way,
			@Param("price") String price);
//	
//	@Delete("delete from res where email = #{email} and prod_no = #{prod_no}")
//	int deleteProdListContent(
//     		@Param("email") String email,
//			@Param("prod_no") int prod_no);
	
}
