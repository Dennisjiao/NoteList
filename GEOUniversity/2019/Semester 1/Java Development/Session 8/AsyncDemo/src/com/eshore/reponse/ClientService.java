package com.eshore.reponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;

import javax.servlet.AsyncContext;


public class ClientService {
	
	//ConcurrentLinkedQueue是一个基于链接节点的无界线程安全队列，它采用先进先出的规则对节点进行排序
	private final Queue<AsyncContext> ASYNC_QUEUE = 
		new ConcurrentLinkedQueue<AsyncContext>();//异步Servlet上下文队列
	
	//LinkedBlockingQueue是一个阻塞的线程安全的队列，底层采用链表实现。
	private final BlockingQueue<String> INFO_QUEUE = 
		new LinkedBlockingQueue<String>();		//消息队列.
	
	//类被加载时，静态成员就被初始化了
	private static ClientService instance  = new ClientService();
 
	private ClientService() {
		 new Thread(this.notifyRunnable).start();
	}
	 
	public static ClientService getInstance() {
		return instance;
	}

	public void addAsyncContext(final AsyncContext asyncContext) {
		ASYNC_QUEUE.add(asyncContext);//添加异步Servlet上下文
	}

	public void removeAsyncContext(final AsyncContext asyncContext) {
		ASYNC_QUEUE.remove(asyncContext);//删除异步Servlet上下文
	}

	/**
	 * 
	 * 发送消息到异步线程，最终输出到http response 流 .<br>
	 * @param str 发送给客户端的消息.<br>
	 */

	public void callClient(final String str) {
		try {
			INFO_QUEUE.put(str);
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

	/**
	 * 当消息队列中有数据，则调用take()方法，
	 * 将数据发送到response流上 
	 */
	private Runnable notifyRunnable = new Runnable() {
		public void run() {
			boolean done = false;
			while (!done) {
				try {
					//检索和删除队列的头部元素
					final String script = INFO_QUEUE.take();
					System.out.println(script);
					for (AsyncContext ac : ASYNC_QUEUE) {
						try {
							PrintWriter writer = ac.getResponse().getWriter();
							writer.println(escapeHTML(script));
							writer.flush();
						} catch (IOException ex) {
							ASYNC_QUEUE.remove(ac);
							throw new RuntimeException(ex);
						}
					}
				}catch(InterruptedException e) {
					done = true;
					e.printStackTrace();
				}
			}
		}
	};

	/**
	 * 
	 * 删除多余的空格
	 */
	private String escapeHTML(String str) {

		return "<script type='text/javascript'>\n"
		+ str.replaceAll("\n", "").replaceAll("\r", "")
		+ "</script>\n";

	}

}
