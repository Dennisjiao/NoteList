package com.eshore.factory;

import com.eshore.dao.ProductDao;
import com.eshore.service.ProductService;

public class DAOFactory {
	public static ProductDao getIEmpDAOInstance() throws Exception{
		return new ProductService();
	}

}
