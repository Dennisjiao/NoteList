package com.soundsystem;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.soundsystem.BlankDisc;

public class EnvironmentInjectionTest {

  @RunWith(SpringJUnit4ClassRunner.class)
  @ContextConfiguration(classes=EnvironmentConfig.class)
  public static class InjectFromProperties {
  
    @Autowired
    private BlankDisc blankDisc;
    
    @Test
    public void assertBlankDiscProperties() {
      assertEquals("The Beatles", blankDisc.getArtist());
      assertEquals("Sgt. Peppers Lonely Hearts Club Band", blankDisc.getTitle());
    }
    
  }
  
  @RunWith(SpringJUnit4ClassRunner.class)
  @ContextConfiguration(classes=EnvironmentConfigWithDefaults.class)
  public static class InjectFromPropertiesWithDefaultValues {
  
    @Autowired
    private BlankDisc blankDisc;
    
    @Test
    public void assertBlankDiscProperties() {
      assertEquals("U2", blankDisc.getArtist());
      assertEquals("Rattle and Hum", blankDisc.getTitle());
    }
    
  }

  public static class InjectFromPropertiesWithRequiredProperties {
  
	//如果测试该方法时产生一个BeanCreationException的异常，则表示测试通过    
	@SuppressWarnings("resource")
	@Test(expected=BeanCreationException.class)
    public void assertBlankDiscProperties() {
      //使用AnnotationConfigApplicationContext可以实现基于Java的配置类加载Spring的应用上下文。
      //避免使用application.xml进行配置。相比XML配置，更加便捷。
      new AnnotationConfigApplicationContext(EnvironmentConfigWithRequiredProperties.class);
    }
    
  }

  @RunWith(SpringJUnit4ClassRunner.class)
  @ContextConfiguration("classpath:placeholder-config.xml")
  public static class InjectFromProperties_XMLConfig {
  
    @Autowired
    private BlankDisc blankDisc;
    
    @Test
    public void assertBlankDiscProperties() {
      assertEquals("The Beatles", blankDisc.getArtist());
      assertEquals("Sgt. Peppers Lonely Hearts Club Band", blankDisc.getTitle());
    }
    
  }

}