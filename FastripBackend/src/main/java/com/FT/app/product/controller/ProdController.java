package com.FT.app.product.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.FT.app.product.service.ProdService;
import com.FT.app.product.vo.ProdVO;

@RestController
@RequestMapping(value = "api/product/*")
public class ProdController {
	@Autowired
	private ProdService prodService;

	@GetMapping("/all")
	public String test() {
		return "상품 테스트";
	}
	
	@PostMapping("/add")
	public void send(@RequestBody String send) {
		System.out.println(send);
	}
	
	@GetMapping("/productDetail/{productno}")
	public ProdVO getProductList(@PathVariable("productno") int productno) throws Exception {
		return prodService.getProductList(productno);
	}

	@GetMapping("/productDetail/all")
	public List<ProdVO> getAllProductList() throws Exception {
		return prodService.getProductList();
	}

	@GetMapping("/productDetail/bestImage/{productno}")
	public List<ProdVO> getAllProductImageList(@PathVariable("productno") int productno) throws Exception {
		return prodService.getProductImageList(productno);
	}

	@PostMapping("/upload/img/{productno}")
	public void productImageUpload(@PathVariable("productno") int productno,
			@RequestParam("fileList") List<MultipartFile> fileList) {
		File file = new File("./src/main/resources/images/product/" + productno + "/");

		boolean directoryCreated = file.mkdir();
		System.out.println(productno);
		try {
			for (MultipartFile multipartFile : fileList) {
				FileOutputStream writer = new FileOutputStream(
						"./src/main/resources/images/product/" + productno + "/" + multipartFile.getOriginalFilename());
				System.out.println(multipartFile.getOriginalFilename());
				writer.write(multipartFile.getBytes());
				writer.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}
