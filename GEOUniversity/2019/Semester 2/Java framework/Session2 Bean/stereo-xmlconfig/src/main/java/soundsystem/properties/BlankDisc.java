package soundsystem.properties;

import java.util.List;

import soundsystem.CompactDisc;

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
	// 专辑列表
	private List<String> tracks;

	public void setTitle(String title) {
		this.title = title;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public void setTracks(List<String> tracks) {
		this.tracks = tracks;
	}

	// 播放歌曲
	public void play() {
		System.out.println("Playing " + title + " by " + artist);
		for (String track : tracks) {
			System.out.println("-Track: " + track);
		}
	}

}
