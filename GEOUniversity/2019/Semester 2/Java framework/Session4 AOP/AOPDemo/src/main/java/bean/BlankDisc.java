package bean;

import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class BlankDisc implements CompactDisc {

	private String title;
	private String artist;
	private List<String> tracks;

	public BlankDisc()
	{
		
	}

	public BlankDisc(String artist, String title, List<String> tracks) {
		this.artist = artist;
		this.title = title;
		this.tracks = tracks;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public List<String> getTracks() {
		return tracks;
	}

	public void setTracks(List<String> tracks) {
		this.tracks = tracks;
	}

	public void play() {
		System.out.println("Playing " + title + " by " + artist);
		for (String track : tracks) {
			System.out.println("-Track: " + track);
		}
	}

	public void playTrack(int num) {
		System.out.println("-Track(" +num+"):"+ tracks.get(num));		
	}
	/**
	 * 因为除数为0，这个方法会抛出异常
	 */
	public float throwExceptionTest()
	{
		 return 100/0;
	}
}