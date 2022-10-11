package com.FT.app.product.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.FT.app.product.vo.ProdVO;

@Mapper
public interface ProdMapper {
	@Select("select * from s_product")
	List<ProdVO> findAllProduct();

	@Select("select * from s_product where productno = #{productno}")
	ProdVO findProduct(int productno);

	@Select("select * from s_product where id = #{sellerid} order by regdate")
	List<ProdVO> findSalseList(String sellerid);

	@Select("select option1 from s_product where productno = #{productno}")
	String findOption1(int productno);

	@Select("select option2 from s_product where productno = #{productno}")
	String findOption2(int productno);

	@Select("select * from s_product left join s_seller on (s_seller.productno = s_product.productno) where s_product.id = (select id from s_seller where productno = #{productno}) and s_product.productno != #{productno} order by s_seller.totalsell desc")
	List<ProdVO> findAllProductImage(int productno);

	@Select("select prod_seq.currval from dual")
	int getProductNo();

	@Insert("insert into s_product(productno, productname, imagename, price, option1, option2, regdate, id, detailimagename) values (prod_seq.nextval, #{in.productname}, #{in.imagename}, #{in.price}, #{in.option1}, #{in.option2}, to_char(sysdate, 'yyyy.mm.dd hh24:mi'), #{in.id}, #{in.detailimagename})")
	@Options(useGeneratedKeys = true, keyProperty = "result.productno", keyColumn = "productno")
	int insertProduct(@Param("in") ProdVO in,@Param("result") ProdVO result);

}
