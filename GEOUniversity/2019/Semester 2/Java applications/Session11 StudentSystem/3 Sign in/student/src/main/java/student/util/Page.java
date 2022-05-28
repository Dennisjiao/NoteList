package student.util;

import java.util.List;

/**
 * 页面类，保存分页相关信息
 * @author lhzxx
 *
 */
public class Page {
	private int pageSize;				// 每页显示的记录数
	private int totalPage;				// 总页数
	private long rowCount;				// 总记录数
	private int currentPage;			// 当前页
	private int prePage;				// 上一页
	private int nextPage;				// 下一页
	private boolean hasNextPage;		// 是否有下一页
	private boolean hasPreviousPage;	// 是否有前一页
	private List<?> list;				//保存当前页面中要显示的对象列表
	 //构造函数，初始化页面显示记录数
	public Page()
	{
		this.pageSize =10;
	}
	
	public Page(Integer page,Integer limit)
	{
		this.currentPage=page;
		this.pageSize=limit;
	}
	
	public Page(Integer page,Integer limit,long rowCount)
	{
		this.currentPage=page;
		this.pageSize=limit;
		this.rowCount=rowCount;
		if (rowCount % limit == 0) {
			this.totalPage=(int)rowCount / limit;
		} else {
			this.totalPage=(int)(1 + (rowCount / limit));
		}
		if(page>this.totalPage)
			this.currentPage=this.totalPage;
	}
	
	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	
	public List<?> getList() {
	   return list;
	}

	public void setList(List<?> list) {
	   this.list = list;
	}

	public int getNextPage() {
		return nextPage;
	}

	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPrePage() {
		return prePage;
	}

	public void setPrePage(int prePage) {
		this.prePage = prePage;
	}

	public long getRowCount() {
		return rowCount;
	}

	public void setRowCount(long rowCount) {
		this.rowCount = rowCount;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public boolean isHasNextPage() {
		return hasNextPage;
	}

	public void setHasNextPage(boolean hasNextPage) {
		this.hasNextPage = hasNextPage;
	}

	public boolean isHasPreviousPage() {
		return hasPreviousPage;
	}

	public void setHasPreviousPage(boolean hasPreviousPage) {
		this.hasPreviousPage = hasPreviousPage;
	}
}