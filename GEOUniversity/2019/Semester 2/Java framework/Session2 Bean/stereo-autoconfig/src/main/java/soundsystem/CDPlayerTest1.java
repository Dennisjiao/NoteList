package soundsystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
@Component
public class CDPlayerTest1 {
	@Autowired
	private CompactDisc cd;	
	public void cdShouldNotBeNull()
	{
		if(cd!=null)
			cd.play();
		else
			System.out.println("cd is null");		
	}	
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext(
				soundsystem.CDPlayerConfig.class);	
		CDPlayerTest1 test=(CDPlayerTest1)context.getBean(CDPlayerTest1.class);		
		test.cdShouldNotBeNull();
		context.close();
	}
}
