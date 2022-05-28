package com.eshore.dao;

import java.util.List;

import com.eshore.pojo.Product;

public interface ProductDao {
	
	public boolean addProduct(Product product) throws Exception;
	
	public List<Product> findAll(String productName) throws Exception;
	
	public Product findByProductId(String productId) throws Exception;

}
