package jodt;
import java.util.Scanner;
public class jodt {
	public static void main(String args[])
	{
		Scanner in= new Scanner(System.in);
		System.out.println("������ʲô����");
		System.out.println("0-�ֻ� 1-������ 2-������");
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
            System.out.println("û�д��豸");
        in.close();
	}

}
