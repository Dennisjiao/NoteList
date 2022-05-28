package com.hero.dao;

import java.util.List;

import com.hero.pojo.Hero;

public interface HeroDao {
	
	/**     
     * 添加好汉   
     */ 
    public boolean add(Hero hero)throws Exception ;
    /**     
     * 编辑好汉   
     */ 
    public boolean update(Hero hero)throws Exception ;
    /**     
     * 删除好汉   
     */ 
    public boolean delete(Integer heroId)throws Exception ;
     /**     
     * 查询全部好汉
     */ 
    public List<Hero> findAll()throws Exception;     
     /**     
     * 根据heroId查询好汉      
     */ 
    public Hero findByHeroId(Integer heroId)throws Exception;
    
    /**     
     * 根据条件查询好汉      
     */ 
    public List<Hero> findByCriteria(Hero hero)throws Exception;
}
