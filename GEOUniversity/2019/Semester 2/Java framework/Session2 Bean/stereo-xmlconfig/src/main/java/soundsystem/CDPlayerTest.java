package soundsystem;
//package soundsystem.properties;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CDPlayerTest {

	public static void main(String[] args) {
//		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
//	            "ConstructorArgValueTest-context.xml");
//		CDPlayer player=(CDPlayer)context.getBean(CDPlayer.class);
		
//		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
//	            "ConstructorArgCollectionTest-context.xml");
//		CDPlayer player=(CDPlayer)context.getBean(CDPlayer.class);	
		
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
	            "PropertyValueTest-context.xml");		
		soundsystem.properties.CDPlayer player=(soundsystem.properties.CDPlayer)context.getBean(soundsystem.properties.CDPlayer.class);
		
		player.play();
		context.close();
	}

}
