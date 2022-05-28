package com.hero.service;

import java.util.List;

import com.hero.dao.HeroDao;
import com.hero.dao.HeroDaoImpl;
import com.hero.db.DBConnection;
import com.hero.pojo.Hero;

public class HeroService implements HeroDao {

	private DBConnection dbConn=null;
	private HeroDao dao=null;
	
	public HeroService() throws Exception{
		this.dbConn=new DBConnection();
		this.dao=new HeroDaoImpl(this.dbConn.getConnection());
	}
	@Override
	public boolean add(Hero hero) throws Exception {
		boolean flag=false;
		try{
			flag=this.dao.add(hero);
		}
		catch(Exception e){
			throw e;
		}
		finally{
			this.dbConn.close();
		}
		return flag;

	}

	@Override
	public boolean update(Hero hero) throws Exception {
		boolean flag=false;
		try{
			if(this.dao.findByHeroId(hero.getId())!=null){
				flag=this.dao.update(hero);
			}
		}
		catch(Exception e){
			throw e;
		}
		finally{
			this.dbConn.close();
		}
		return flag;
	}

	@Override
	public boolean delete(Integer heroId) throws Exception {
		boolean flag=false;
		try{
			flag=this.dao.delete(heroId);
		}
		catch(Exception e){
			throw e;
		}
		finally{
			this.dbConn.close();
		}
		return flag;
	}

	@Override
	public List<Hero> findAll() throws Exception {
		List<Hero> list=null;
		try{
			list=this.dao.findAll();
		}
		catch(Exception e){
			throw e;
		}
		finally{
			this.dbConn.close();
		}
		return list;
	}

	@Override
	public Hero findByHeroId(Integer id) throws Exception {
		Hero hero=null;
		try{
			hero=this.dao.findByHeroId(id);
		}
		catch(Exception e){
			throw e;
		}
		finally{
			this.dbConn.close();
		}
		return hero;
	}

	@Override
	public List<Hero> findByCriteria(Hero hero) throws Exception {
		List<Hero> list=null;
		try{
			list=this.dao.findByCriteria(hero);
		}
		catch(Exception e){
			throw e;
		}
		finally{
			this.dbConn.close();
		}
		return list;
	}

}
