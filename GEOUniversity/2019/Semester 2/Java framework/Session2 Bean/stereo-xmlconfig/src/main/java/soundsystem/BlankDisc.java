package soundsystem;

/**
 * 定义一个空的CD，CD接口实现类
 * 
 * @author lhzxx
 *
 */
public class BlankDisc implements CompactDisc {

	// 专辑标题
	private String title;
	// 歌手
	private String artist;

	// 构造函数
	public BlankDisc(String title, String artist) {
		this.title = title;
		this.artist = artist;
	}

	// 播放专辑
	public void play() {
		System.out.println("Playing " + title + " by " + artist);
	}

}
