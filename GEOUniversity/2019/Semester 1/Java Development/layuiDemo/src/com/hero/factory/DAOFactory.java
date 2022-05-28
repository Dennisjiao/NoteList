package com.hero.factory;

import com.hero.dao.HeroDao;
import com.hero.service.HeroService;

public class DAOFactory {
	
	public static HeroDao getDAOInstance() throws Exception{
		return new HeroService();
	}

}
