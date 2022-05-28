package com.eshore;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
/*
 * 当在session中添加、移除或更改属性值时会触发相应的事件。
 */
@WebListener
public class MyHttpSessionAttributeListener implements HttpSessionAttributeListener{

	public void attributeAdded(HttpSessionBindingEvent se) {
		System.out.println("有对象加入session中"); 
		
	}

	public void attributeRemoved(HttpSessionBindingEvent se) {
		System.out.println("有对象从session中移除"); 
		
	}

	public void attributeReplaced(HttpSessionBindingEvent se) {
		System.out.println("属性值改变");
		
	}

}
