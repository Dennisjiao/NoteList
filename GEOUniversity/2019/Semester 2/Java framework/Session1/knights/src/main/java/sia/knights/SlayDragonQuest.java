package sia.knights;

import java.io.PrintStream;

/**
 * 屠龙探险任务
 * 
 * @author lhzxx
 *
 */
public class SlayDragonQuest implements Quest {

	// 定义一个输出对象，相当于System.out
	private PrintStream stream;

	// 构造函数
	public SlayDragonQuest(PrintStream stream) {
		this.stream = stream;
	}

	// 探险任务方法实现
	public void embark() {
		stream.println("Embarking on quest to slay the dragon!");
	}

}
