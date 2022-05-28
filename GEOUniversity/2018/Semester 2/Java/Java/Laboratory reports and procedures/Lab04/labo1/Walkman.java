package jodt;

public class Walkman implements Soundable{
	@Override
	public void increaseVolume()
	{
		System.out.println("增大随心听音量");
	}
	@Override
	public void decreaseVolume()
	{
		System.out.println("减小随身听音量");
	}
	@Override
	public void stopSound()
	{
		System.out.println("停止随心听");
	}
	@Override
	public void playSound()
	{
		System.out.println("开始播放随心听");
	}
}
