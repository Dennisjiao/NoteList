package com.habuma.restfun;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class MagicExistsCondition implements Condition {

  @Override
  public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
	//检测环境中是否有magic属性
    Environment env = context.getEnvironment();
    return env.containsProperty("magic");
  }
  
}
