package com.myapp;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
//excludeFilters：指定不适合组件扫描的类型，即不扫描注解为@Configuration的类
@ComponentScan( basePackages="com.myapp",
				excludeFilters={@Filter(type=FilterType.ANNOTATION, value=Configuration.class)})
public class ComponentScannedConfig {

}
