package sia.knights;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class KnightMain {

  public static void main(String[] args) throws Exception {
	  
    ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("knight.xml");
	//AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext(sia.knights.config.KnightConfig.class);
    Knight knight = context.getBean(Knight.class);
    knight.embarkOnQuest();
    context.close();
  }

}
