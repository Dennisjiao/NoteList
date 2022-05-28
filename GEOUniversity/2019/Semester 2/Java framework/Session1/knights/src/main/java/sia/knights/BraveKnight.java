package sia.knights;

/**
 * 骑士实现类，勇敢的骑士
 * 
 * @author lhzxx
 *
 */
public class BraveKnight implements Knight {

	// 探险任务,这里是个接口，以便接受不同的探险任务
	private Quest quest;

	//勇敢的骑士的构造方法
	public BraveKnight(Quest quest) {
		this.quest = quest;
	}
	//骑士接口文件中探险任务接口的实现
	public void embarkOnQuest() {
		quest.embark();
	}

}
