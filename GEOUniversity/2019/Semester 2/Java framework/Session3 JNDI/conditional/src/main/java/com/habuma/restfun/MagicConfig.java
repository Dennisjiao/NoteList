package com.habuma.restfun;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MagicConfig {

  //MagicExistsCondition中的方法matches返回真才创建下面的bean
  @Bean
  @Conditional(MagicExistsCondition.class)
  public MagicBean magicBean() {
    return new MagicBean();
  }
  
}