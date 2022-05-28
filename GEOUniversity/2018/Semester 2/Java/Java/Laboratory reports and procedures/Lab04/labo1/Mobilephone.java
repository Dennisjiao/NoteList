package jodt;

public class Mobilephone implements Soundable{
	@Override
	public void increaseVolume()
	{
		System.out.println("增大手机音量");
	}
	@Override
	public void decreaseVolume()
	{
		System.out.println("减小手机音量");
	}
	@Override
	public void stopSound()
	{
		System.out.println("停止手机");
	}
	@Override
	public void playSound()
	{
		System.out.println("开始播放手机");
	}

}
