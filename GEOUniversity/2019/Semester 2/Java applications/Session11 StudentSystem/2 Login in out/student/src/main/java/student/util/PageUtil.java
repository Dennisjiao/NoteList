package student.util;

import java.util.List;

/**
 * 分页逻辑的实现类
 * @author lhzxx
 *
 */

public class PageUtil {	
	private Page page; 					//分页逻辑处理的对象是页面实体
	private int start; 				//start是点击“上一页”或“下一页”传递进来的页码		
	
	public PageUtil(int start,int pagesize)
	{		
		page = new Page();		// 初始化一个Page，即指定了pageSize=5，这个可以到Page类中自定义
		page.setPageSize(pagesize);
		this.start = start;		//初始化查询启始页		
	}

	private void setPreOrNextBoolean() { // 设置是否有“上一页”或者是否有下一页的boolean型标识
		if (page.getCurrentPage() <= 1) { // 第一页时，没有上一页，则上一页链接失效
			page.setHasPreviousPage(false);
		} else {
			page.setHasPreviousPage(true);
		}
		if (page.getCurrentPage()==0 || page.getCurrentPage() >= page.getTotalPage()) { // 最后一页时，没有下一页，则下一页链接失效
			page.setHasNextPage(false);
		} else {
			page.setHasNextPage(true);
		}
	}

	private void setCurrentPage() { // 设置当前页
		if (start < 1) {
			page.setCurrentPage(1);
		}
		if (start > page.getTotalPage()) {
			page.setCurrentPage(page.getTotalPage());
		}
		page.setCurrentPage(start);
	}

	private void setPrePage() { // 设置上一页
		page.setPrePage(page.getCurrentPage() - 1);
	}

	private void setNextPage() {
		page.setNextPage(page.getCurrentPage() + 1);
	}

	private void setTotalPage()
	{ // 设置总页数
		long rowCount = page.getRowCount();
		int pageSize = page.getPageSize();
		if(pageSize<=0)
			page.setTotalPage(1);
		else if (rowCount > pageSize) {
			if (rowCount % pageSize == 0) {
				page.setTotalPage((int)(rowCount / pageSize));
			} else {
				page.setTotalPage((int)(1 + (rowCount / pageSize)));
			}
		}
		else {
			page.setTotalPage(1);
		}
	}
		
	// 设置page的属性rowCount值，即该次查询的总记录数	
	public void setRowCount(int n)
	{ 
		page.setRowCount(n);
		setTotalPage(); 		// 设置总页数
		setCurrentPage(); 		// 设置当前页
		setPrePage(); 			// 设置上一页
		setNextPage(); 			// 设置下一页
		setPreOrNextBoolean(); 	// 设置是否有“上一页”或者是否有下一页的boolean型标识
	}
	//设置分页查询结果
	public void setPage(List<?> list)
	{
		page.setList(list);		
	}
	//获取分页信息
	public Page getPage()
	{
		return page;
	}
	
	
}