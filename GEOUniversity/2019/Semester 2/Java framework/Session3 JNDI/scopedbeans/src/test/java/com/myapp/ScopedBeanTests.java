package com.myapp;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.myapp.Notepad;
import com.myapp.UniqueThing;

public class ScopedBeanTests {

  /*
   * 组件扫描模式测试
   */
  @RunWith(SpringJUnit4ClassRunner.class)
  @ContextConfiguration(classes=ComponentScannedConfig.class)
  public static class ComponentScannedScopedBeanTest {
    
    @Autowired
    private ApplicationContext context;
    
    //原型范围，不是同一个bean
    @Test
    public void testProxyScope() {
      Notepad notepad1 = context.getBean(Notepad.class);
      Notepad notepad2 = context.getBean(Notepad.class);
      assertNotSame(notepad1, notepad2);
    }
    
    //单例范围，是同一个bean
    @Test
    public void testSingletonScope() {
      UniqueThing thing1 = context.getBean(UniqueThing.class);
      UniqueThing thing2 = context.getBean(UniqueThing.class);
      assertSame(thing1, thing2);
    }
    
  }
  
  /*
   * 显式java配置测试
   */
  @RunWith(SpringJUnit4ClassRunner.class)
  @ContextConfiguration(classes=ExplicitConfig.class)
  public static class ExplicitConfigScopedBeanTest {
    
    @Autowired
    private ApplicationContext context;
    
    @Test
    public void testProxyScope() {
      Notepad notepad1 = context.getBean(Notepad.class);
      Notepad notepad2 = context.getBean(Notepad.class);
      assertNotSame(notepad1, notepad2);
    }
    
    @Test
    public void testSingletonScope() {
      UniqueThing thing1 = context.getBean(UniqueThing.class);
      UniqueThing thing2 = context.getBean(UniqueThing.class);
      assertSame(thing1, thing2);
    }
    
  }

  /*
   * 显示xml配置测试
   */
  @RunWith(SpringJUnit4ClassRunner.class)
  @ContextConfiguration("classpath:scoped-beans.xml")
  public static class XMLConfigScopedBeanTest {
    
    @Autowired
    private ApplicationContext context;
    
    @Test
    public void testProxyScope() {
      Notepad notepad1 = context.getBean(Notepad.class);
      Notepad notepad2 = context.getBean(Notepad.class);
      assertNotSame(notepad1, notepad2);
    }
    
    @Test
    public void testSingletonScope() {
      UniqueThing thing1 = context.getBean(UniqueThing.class);
      UniqueThing thing2 = context.getBean(UniqueThing.class);
      assertSame(thing1, thing2);
    }
    
  }


}
