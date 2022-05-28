package soundsystem;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
/**
 * java配置文件
 * @author lhzxx
 *
 */
@Configuration
//@ComponentScan(basePackages="soundsystem")
public class CDPlayerConfig {  
  @Bean
  public CompactDisc compactDisc() {
    return new SgtPeppers();
  }  
  @Bean
  public CDPlayer cdPlayer(CompactDisc compactDisc) {
    return new CDPlayer(compactDisc);
  }
}
