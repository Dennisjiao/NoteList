package soundsystem;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * 媒体播放器实现类
 * 
 * @author lhzxx
 *
 */
public class CDPlayer implements MediaPlayer {

	// 定义一张空的专辑
	private CompactDisc cd;

	// 构造函数依赖注入
	@Autowired
	public CDPlayer(CompactDisc cd) {
		this.cd = cd;
	}

	// 播放专辑
	public void play() {
		cd.play();
	}

}
