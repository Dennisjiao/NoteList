package soundsystem;

/**
 * 定义SgtPeppers专辑
 * 
 * @author lhzxx
 *
 */
public class SgtPeppers implements CompactDisc {

	// 专辑标题
	private String title = "Sgt. Pepper's Lonely Hearts Club Band";
	// 歌手
	private String artist = "The Beatles";

	// 播放专辑
	public void play() {
		System.out.println("Playing " + title + " by " + artist);
	}

}
