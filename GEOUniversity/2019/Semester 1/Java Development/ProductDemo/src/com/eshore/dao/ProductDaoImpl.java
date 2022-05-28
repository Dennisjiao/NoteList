package com.eshore.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.eshore.pojo.Product;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class ProductDaoImpl implements ProductDao {

	private Connection conn=null;
	private PreparedStatement pstmt=null;
	
	public ProductDaoImpl(Connection conn){
		this.conn=conn;
	}
	
	@Override
	public boolean addProduct(Product product) throws Exception {

		boolean flag=false;
		String sql="insert into product(product_id,product_name,price,info) values(?,?,?,?)";
		this.pstmt=(PreparedStatement) this.conn.prepareStatement(sql);
		this.pstmt.setString(1, product.getProductId());
		this.pstmt.setString(2, product.getProductName());
		this.pstmt.setDouble(3, product.getPrice());
		this.pstmt.setString(4, product.getInfo());
		if(this.pstmt.executeUpdate()>0){
			flag=true;
		}
		this.pstmt.close();
		return false;
	}

	@Override
	public List<Product> findAll(String productName) throws Exception {

		List<Product> list=new ArrayList<Product>();
		String sql="select product_id,product_name,price,info from product";
		if(productName!=null && !"".equals(productName)){
			sql +=" where product_name like ?";
			this.pstmt=(PreparedStatement) this.conn.prepareStatement(sql);
			this.pstmt.setString(1, "%"+productName+"%");
		}
		else{
			this.pstmt=(PreparedStatement) this.conn.prepareStatement(sql);
		}
		ResultSet rs= this.pstmt.executeQuery();
		Product product=null;
		while(rs.next()){
			product=new Product();
			product.setProductId(rs.getString("product_id"));
			product.setProductName(rs.getString("product_name"));
			product.setInfo(rs.getString("info"));
			product.setPrice(rs.getDouble("price"));
			list.add(product);
		}
		this.pstmt.close();
		return list;
	}

	@Override
	public Product findByProductId(String productId) throws Exception {

		String sql="select product_id,product_name,price,info from product where product_id=?";
		this.pstmt=(PreparedStatement) this.conn.prepareStatement(sql);
		this.pstmt.setString(1, productId);
		ResultSet rs= this.pstmt.executeQuery();
		Product product=new Product();
		if(rs.next()){
			product.setProductId(rs.getString("product_id"));
			product.setProductName(rs.getString("product_name"));
			product.setInfo(rs.getString("info"));
			product.setPrice(rs.getDouble("price"));
		}
		else{
			product=null;
		}
		this.pstmt.close();
		return product;
	}

}
