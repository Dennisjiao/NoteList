package com.eshore.service;

import java.util.List;

import com.eshore.dao.ProductDao;
import com.eshore.dao.ProductDaoImpl;
import com.eshore.pojo.Product;
import com.eshore.util.DBConnection;

public class ProductService implements ProductDao {

	private DBConnection conn=null;
	private ProductDao dao=null;
	
	public ProductService() throws Exception{
		this.conn=new DBConnection();
		this.dao=new ProductDaoImpl(this.conn.getConnection());
	}
	
	@Override
	public boolean addProduct(Product product) throws Exception {
		boolean flag=false;
		try{
			if(this.dao.findByProductId(product.getProductId())==null){
				flag=this.dao.addProduct(product);
			}
		}
		catch(Exception e){
			throw e;
		}
		finally{
			this.conn.close();
		}
		
		return flag;
	}

	@Override
	public List<Product> findAll(String productName) throws Exception {

		List<Product> all=null;
		try{
			all=this.dao.findAll(productName);
		}
		catch(Exception e){
			throw e;
		}
		finally{
			this.conn.close();
		}
		return all;
	}

	@Override
	public Product findByProductId(String productId) throws Exception {
		Product product=null;
		try{
			product=this.dao.findByProductId(productId);
		}
		catch(Exception e){
			throw e;
		}
		finally{
			this.conn.close();
		}
		return product;
	}

}
