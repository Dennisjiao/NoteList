package Movie;

import java.util.*;
public class Movie {

	private String title,type;
	private Vector<String>actors;
	public String getTitle(){
		return title;
	}
	public String getType(){
		return type;
	}
	public Vector<String>getActors(){
		return actors;
	}
	public void setTitle(String aTitle){
		title=aTitle;
	}
	public void setType(String aType){
		type=aType;
	}
	public Movie(){
		this("???","???");
	}
	public Movie(String aTitle,String aType){
		title=aTitle;
		type=aType;
		actors=new Vector<String>();
	}
	@Override
	public String toString(){
		return ("Movie:"+"\""+title+"\"");
	}
	public void addActor(String anActor){
		actors.add(anActor);
	}
}
