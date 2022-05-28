package test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner; 
import static org.junit.Assert.assertEquals;
import main.java.aop.TrackCounter;
import main.java.bean.BlankDisc;
import main.java.bean.CompactDisc;
import main.java.configs.TrackCounterConfig;
 
 
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TrackCounterConfig.class)
public class TrackCounterTest {
 
    @Autowired
    private CompactDisc cd;
 
    @Autowired
    private TrackCounter counter;
 
    public void setCounter(TrackCounter counter) {
        this.counter = counter;
    }
 
    public TrackCounter getCounter() {
        return counter;
    }
 
    public void setCd(BlankDisc cd) {
        this.cd = cd;
    }
 
    public CompactDisc getCd() {
        return cd;
    }
    
    @Test
    public void testTrackCounter() {
        cd.playTrack(0);
        cd.playTrack(1);
        cd.play();
        cd.throwExceptionTest();
        
    }
 
}
