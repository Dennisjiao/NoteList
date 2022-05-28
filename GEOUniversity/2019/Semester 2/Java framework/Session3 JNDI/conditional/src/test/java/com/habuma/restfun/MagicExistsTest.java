package com.habuma.restfun;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.habuma.restfun.MagicConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=MagicConfig.class)
public class MagicExistsTest {

  /*
   * Spring有两个核心接口：BeanFactory和ApplicationContext，其中ApplicationContext是BeanFactory的子接口。
   * 他们都可代表Spring容器，Spring容器是生成Bean实例的工厂，并且管理容器中的Bean。
   */
  @Autowired
  private ApplicationContext context;
  
  /*
   * This test will fail until you set a "magic" property.
   * You can set this property as an environment variable, a JVM system property, by adding a @BeforeClass
   * method and calling System.setProperty() or one of several other options.
   */
  @Test
  public void shouldNotBeNull() {
	//判断Spring容器是否包含id为name的Bean实例
    assertTrue(context.containsBean("magicBean"));
  }
  
  //@BeforeClass：针对所有测试，只执行一次，且必须为static void 
  @BeforeClass  
  public static void beforeClass() {   
	  System.setProperty("magic","hello");  
  };
  
}
