package student.model;

public class Course {
	private String cid;
	private String cname;
	
	public Course()
	{
	}
	public Course(String cid,String cname)
	{
		this.cid=cid;
		this.cname=cname;
	}
	
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	
	

}
