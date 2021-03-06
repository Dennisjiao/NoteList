package main.java.configs;

import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import main.java.aop.TrackCounter;
import main.java.bean.BlankDisc;
import main.java.bean.CompactDisc;
import java.util.ArrayList;
import java.util.List;
 
@Configuration
@EnableAspectJAutoProxy
//@ComponentScan(basePackages="java.bean")
public class TrackCounterConfig {
    @Bean
    public CompactDisc sgtPeppers() {
        BlankDisc cd = new BlankDisc();
        cd.setTitle("Sgt. Pepper's Lonely Hearts Club Band");
        cd.setArtist("The Beatles");
        List<String> tracks = new ArrayList<String>();
        tracks.add("Sgt. Pepper's Lonely Hearts Club Band");
        tracks.add("With a Little Help from My Friends");
        tracks.add("Lucky in the Sky with Diamonds");
        tracks.add("Getting Better");
        tracks.add("Fixing a Hole");
        tracks.add("zzzz");
        tracks.add("justinniu");
        cd.setTracks(tracks);
        return cd;
    }
 
    @Bean
    public TrackCounter trackCounter() {
        return new TrackCounter();
    }

}