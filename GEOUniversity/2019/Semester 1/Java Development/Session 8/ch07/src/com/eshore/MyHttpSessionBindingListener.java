package com.eshore;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;
/*
 * 当把该监听器保存到session中，session.setAttribute("user",user)时就会触发valueBound事件.
当该监听器从session中移除时即session.removeAttribute("user"),触发valueUnbound事件;session失效或超时
时也会触发valueUnbound事件。
 */
public class MyHttpSessionBindingListener implements HttpSessionBindingListener{

	public void valueBound(HttpSessionBindingEvent event) {
		System.out.println("触发绑定事件!");
		
	}

	public void valueUnbound(HttpSessionBindingEvent event) {
		System.out.println("解除和session的绑定");
		
	}

}
