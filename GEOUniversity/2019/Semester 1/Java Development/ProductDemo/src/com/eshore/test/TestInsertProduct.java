package com.eshore.test;

import com.eshore.factory.DAOFactory;
import com.eshore.pojo.Product;

public class TestInsertProduct {

	public static void main(String[] args) {
		Product product=null;
		try{
			for(int i=0;i<5;i++){
				product=new Product();
				product.setProductId("100"+i);
				product.setProductName("水杯"+i);
				product.setPrice(100.0+i);
				product.setInfo("这是一个精美的杯子"+i);
				DAOFactory.getIEmpDAOInstance().addProduct(product);
			}
			System.out.println("插入成功！");
			
		}
		catch(Exception e){
			e.printStackTrace();
		}


	}

}
