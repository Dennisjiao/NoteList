package student.util;


/**
 * 页面类，保存分页相关信息
 * @author lhzxx
 *
 */
public class Page {
	private int limit;					// 每页显示的记录数
	private int totalPage;				// 总页数
	private int rowCount;				// 总记录数
	private int page;					// 当前页
	private int prePage;				// 上一页
	private int nextPage;				// 下一页
	private boolean hasNextPage;		// 是否有下一页
	private boolean hasPreviousPage;	// 是否有前一页
	
	 //构造函数，初始化页面显示记录数
	public Page()
	{
		this.limit =5;
	}
	public Page(int page,int limit,int rowcount)
	{
		this.page=page;
		this.limit=limit;
		this.rowCount=rowcount;
		Init();
	}
	public void Init()
	{
		setTotalPage(); 		// 设置总页数
		setPage(); 				// 设置当前页
		setPrePage(); 			// 设置上一页
		setNextPage(); 			// 设置下一页
		setPreOrNextBoolean(); 	// 设置是否有“上一页”或者是否有下一页的boolean型标识
	}
	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}
	
	public int getNextPage() {
		return nextPage;
	}

	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public int getPrePage() {
		return prePage;
	}

	public void setPrePage(int prePage) {
		this.prePage = prePage;
	}

	public int getRowCount() {
		return rowCount;
	}

	public void setRowCount(int rowCount) {
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
	public void setPreOrNextBoolean() { // 设置是否有“上一页”或者是否有下一页的boolean型标识
		if (getPage() <= 1) { // 第一页时，没有上一页，则上一页链接失效
			setHasPreviousPage(false);
		} else {
			setHasPreviousPage(true);
		}
		if (getPage()==0 || getPage() >= getTotalPage()) { // 最后一页时，没有下一页，则下一页链接失效
			setHasNextPage(false);
		} else {
			setHasNextPage(true);
		}
	}

	public void setPage() { // 设置当前页
		if (getPage() < 1) {
			setPage(1);
		}
		if (getPage() > getTotalPage()) {
			setPage(getTotalPage());
		}		
	}

	public void setPrePage() { // 设置上一页
		setPrePage(getPage() - 1);
		if(getPrePage()<1)
			setPrePage(1);
	}

	public void setNextPage() {
		setNextPage(getPage() + 1);
		if(getNextPage()>getTotalPage())
			setNextPage(getTotalPage());
	}

	public void setTotalPage()
	{ // 设置总页数
		int rowCount = getRowCount();
		int pageSize = getLimit();
		if(pageSize<=0)
			setTotalPage(1);
		else if (rowCount > pageSize) {
			if (rowCount % pageSize == 0) {
				setTotalPage(rowCount / pageSize);
			} else {
				setTotalPage(1 + (rowCount / pageSize));
			}
		}
		else {
			setTotalPage(1);
		}
	}
}