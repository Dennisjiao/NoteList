package jodt;

public class Radio implements Soundable{
	@Override
	public void increaseVolume()
	{
		System.out.println("增大收音机音量");
	}
	@Override
	public void decreaseVolume()
	{
		System.out.println("减小收音机音量");
	}
	@Override
	public void stopSound()
	{
		System.out.println("停止收音机");
	}
	@Override
	public void playSound()
	{
		System.out.println("开始播放收音机");
	}
	
}
