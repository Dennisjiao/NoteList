package jodt;

public class People {
	public void display(Soundable soundable)
	{
		soundable.increaseVolume();
		soundable.decreaseVolume();
		soundable.stopSound();
		soundable.playSound();
	}
}
