package com.FT.app.product.vo;

public class ProdVO {
	private int productno;
	private String productname;
	private String imagename;
	private int price;


	public ProdVO() {
	}

	public ProdVO(int productno, String productname, String imagename, int price) {
		super();
		this.productno = productno;
		this.productname = productname;
		this.imagename = imagename;
		this.price = price;
	}

	public int getProductno() {
		return productno;
	}

	public void setProductno(int productno) {
		this.productno = productno;
	}

	public String getProductname() {
		return productname;
	}

	public void setProductname(String productname) {
		this.productname = productname;
	}

	public String getImagename() {
		return imagename;
	}

	public void setImagename(String imagename) {
		this.imagename = imagename;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

}
