package test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner; 
import bean.CompactDisc;
import configs.TrackCounterConfig;
 
 
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TrackCounterConfig.class)
public class TrackCounterTest {
 
    @Autowired
    private CompactDisc cd;
 
    @Test
    public void testTrackCounter() {
        cd.playTrack(0);
        cd.playTrack(1);
        cd.play();
        cd.throwExceptionTest();
        
    }
 
}
