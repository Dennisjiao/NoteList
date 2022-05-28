package jodt;
import java.util.Scanner;
public class jodt {
	public static void main(String args[])
	{
		Scanner in= new Scanner(System.in);
		System.out.println("你想用什么听？");
		System.out.println("0-手机 1-收音机 2-随声听");
		int choice;
		choice = in.nextInt();
		SampleDisplay sampledisplay = new SampleDisplay();
		if (choice == 0)
            sampledisplay.display(new Mobilephone());
        else if(choice == 1)
            sampledisplay.display(new Radio());
        else if(choice == 2)
            sampledisplay.display(new Walkman());
        else
            System.out.println("没有此设备");
        in.close();
	}

}
