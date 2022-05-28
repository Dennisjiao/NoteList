package spittr.config;

import java.util.regex.Pattern;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.core.type.filter.RegexPatternTypeFilter;

import spittr.config.RootConfig.WebPackage;

//声明本文件为一个配置文件
@Configuration
// 把配置文件DataConfig.class导入到本类中
@Import(DataConfig.class)
// 在main.java.spittr目录下开启组件扫描，FilterType.CUSTOM 自定义过滤方式
@ComponentScan(basePackages = { "spittr" }, excludeFilters = {
		@Filter(type = FilterType.CUSTOM, value = WebPackage.class) })

public class RootConfig {

	//自定义过滤类："main.java.spittr.web"目录下的所有类都不在扫描范围内。"\\."代表对字符.的转义
	public static class WebPackage extends RegexPatternTypeFilter {
		public WebPackage() {
			super(Pattern.compile("spittr\\.web"));
		}
	}
}
