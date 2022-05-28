package sia.knights.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import sia.knights.BraveKnight;
import sia.knights.Knight;
import sia.knights.Quest;
import sia.knights.SlayDragonQuest;

/**
 * 定义配置文件，作用相当于knight.xml
 * 
 * @author lhzxx
 *
 */
@Configuration
public class KnightConfig {

	// 定义骑士bean,用于依赖生成骑士对象
	@Bean
	public Knight knight() {
		return new BraveKnight(quest());
	}

	// 定义探险任务bean,用于依赖生成探险任务
	@Bean
	public Quest quest() {
		return new SlayDragonQuest(System.out);
	}

}
