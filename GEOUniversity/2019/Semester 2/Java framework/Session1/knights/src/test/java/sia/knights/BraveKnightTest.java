package sia.knights;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Test;

public class BraveKnightTest {

	// 测试骑士是否执行了探险任务
	@Test
	public void knightShouldEmbarkOnQuest() {
		// 根据Quest接口生成一个假的探险任务
		Quest mockQuest = mock(Quest.class);
		// 生成一个骑士对象
		BraveKnight knight = new BraveKnight(mockQuest);
		// 执行探险任务
		knight.embarkOnQuest();
		// 检验探险任务方法是否被调用（embark方法被调用了1次）
		verify(mockQuest, times(1)).embark();
	}

}
