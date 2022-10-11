package com.FT.app.product.vo;

public class ProdVO {
	private int productno;
	private String productname;
	private String imagename;
	private int price;
	private String option1;
	private String option2;
	private String regdate;
	private String id;
	private String detailimagename;
	private String onsale;

	public ProdVO() {
	}

	public ProdVO(int productno, String productname, String imagename, int price, String option1, String option2,
			String regdate, String id, String detailimagename, String onsale) {
		super();
		this.productno = productno;
		this.productname = productname;
		this.imagename = imagename;
		this.price = price;
		this.option1 = option1;
		this.option2 = option2;
		this.regdate = regdate;
		this.id = id;
		this.detailimagename = detailimagename;
		this.onsale = onsale;
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

	public String getOption1() {
		return option1;
	}

	public void setOption1(String option1) {
		this.option1 = option1;
	}

	public String getOption2() {
		return option2;
	}

	public void setOption2(String option2) {
		this.option2 = option2;
	}

	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDetailimagename() {
		return detailimagename;
	}

	public void setDetailimagename(String detailimagename) {
		this.detailimagename = detailimagename;
	}

	public String getOnsale() {
		return onsale;
	}

	public void setOnsale(String onsale) {
		this.onsale = onsale;
	}

}
