package sia.knights;

/**
 * 执行营救少女任务的骑士类，该骑士类只能执行营救少女任务
 * 
 * @author lhzxx
 *
 */
public class DamselRescuingKnight implements Knight {

	// 定义营救少女任务对象
	private RescueDamselQuest quest;

	public DamselRescuingKnight() {
		this.quest = new RescueDamselQuest();
	}

	// 执行探险任务，即营救少女任务
	public void embarkOnQuest() {
		quest.embark();
	}

	public static void main(String[] args) {
		
		// 生成执行营救少女任务的骑士对象
		DamselRescuingKnight knight = new DamselRescuingKnight();
		
		// 执行探险任务，即营救少女任务
		knight.embarkOnQuest();
	}

}
