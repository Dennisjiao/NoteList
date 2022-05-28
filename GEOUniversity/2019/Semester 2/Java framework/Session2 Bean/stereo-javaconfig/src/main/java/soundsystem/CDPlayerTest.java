package soundsystem;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class CDPlayerTest {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext(
				soundsystem.CDPlayerConfig.class);	
		CDPlayer player=(CDPlayer)context.getBean(CDPlayer.class);		
		player.play();
		context.close();
	}
}
