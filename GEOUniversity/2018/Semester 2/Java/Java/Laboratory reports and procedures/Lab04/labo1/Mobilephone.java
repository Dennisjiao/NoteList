package jodt;

public class Mobilephone implements Soundable{
	@Override
	public void increaseVolume()
	{
		System.out.println("�����ֻ�����");
	}
	@Override
	public void decreaseVolume()
	{
		System.out.println("��С�ֻ�����");
	}
	@Override
	public void stopSound()
	{
		System.out.println("ֹͣ�ֻ�");
	}
	@Override
	public void playSound()
	{
		System.out.println("��ʼ�����ֻ�");
	}

}
