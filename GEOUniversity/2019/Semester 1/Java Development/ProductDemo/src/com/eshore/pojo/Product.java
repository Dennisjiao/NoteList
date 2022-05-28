package com.eshore.pojo;

public class Product {
	
	private String productId;
	private String productName;
	private Double price;
	private String info;
	
	public Product()
	{
		
	}
	public Product(String productId,String productName,Double price,String info){
		this.productId=productId;
		this.productName=productName;
		this.price=price;
		this.info=info;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}

}
