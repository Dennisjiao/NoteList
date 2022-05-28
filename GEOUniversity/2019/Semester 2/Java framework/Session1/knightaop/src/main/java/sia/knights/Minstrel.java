package sia.knights;

import java.io.PrintStream;

/**
 * 定义游吟诗人类，用于传颂骑士的事迹
 * 
 * @author lhzxx
 *
 */
public class Minstrel {

	// 定义一个输出对象，相当于System.out
	private PrintStream stream;

	// 构造函数
	public Minstrel(PrintStream stream) {
		this.stream = stream;
	}

	// 探险任务前传颂
	public void singBeforeQuest() {
		stream.println("Fa la la, the knight is so brave!");
	}

	// 探险任务后传颂
	public void singAfterQuest() {
		stream.println("Tee hee hee, the brave knight " + "did embark on a quest!");
	}

}
