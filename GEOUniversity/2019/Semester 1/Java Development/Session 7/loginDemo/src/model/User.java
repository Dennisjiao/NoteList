package model;

import java.util.HashMap;
import java.util.Map;

public class User {
	private Map<String,String> userMap=new HashMap<String,String>();
	
	private static User user=null;
	private User(){
		userMap.put("lhz","111111##lhz@sian.com");
		userMap.put("john","222222##john@163.com");
	}
	
	public static User getInstance(){
		if(user==null){
			user=new User();
		}
		return user;
	}

	public Map<String, String> getUserMap() {
		return userMap;
	}

	public void setUserMap(Map<String, String> userMap) {
		this.userMap = userMap;
	}
	
	
}
