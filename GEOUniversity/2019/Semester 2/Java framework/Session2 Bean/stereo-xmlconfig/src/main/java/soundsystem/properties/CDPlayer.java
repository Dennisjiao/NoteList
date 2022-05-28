package soundsystem.properties;

import org.springframework.beans.factory.annotation.Autowired;

import soundsystem.CompactDisc;
import soundsystem.MediaPlayer;

/**
 * 媒体播放器实现类
 * 
 * @author lhzxx
 *
 */
public class CDPlayer implements MediaPlayer {
	// 定义一张空的专辑
	private CompactDisc compactDisc;

	// 属性依赖注入
	@Autowired
	public void setCompactDisc(CompactDisc compactDisc) {
		this.compactDisc = compactDisc;
	}

	// 播放专辑
	public void play() {
		compactDisc.play();
	}

}
