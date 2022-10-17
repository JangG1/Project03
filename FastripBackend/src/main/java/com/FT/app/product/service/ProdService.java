package com.FT.app.product.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.FT.app.product.domain.ProdList;
import com.FT.app.product.mapper.ProdMapper;

@Service
public class ProdService {
	private ProdMapper prodMapper;

	public ProdService(ProdMapper prodMapper) {
		this.prodMapper = prodMapper;
	}

	public List<ProdList> getProductList() {
		return prodMapper.findAllProduct();
	}

	public ProdList getProductList(int productno) {
		return prodMapper.findProduct(productno);
	}

	public List<ProdList> getProductImageList(int productno) {
		List<ProdList> list = prodMapper.findAllProductImage(productno).subList(0, 6);
		return list;
	}


		
}
