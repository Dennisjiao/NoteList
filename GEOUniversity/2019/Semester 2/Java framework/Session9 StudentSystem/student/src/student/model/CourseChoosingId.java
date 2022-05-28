package student.model;

public class CourseChoosingId {
	
	private String stuid;
	private String cid;
	
	public CourseChoosingId() {
		super();
	}

	public CourseChoosingId(String stuid, String cid) {
		super();
		this.stuid = stuid;
		this.cid = cid;
	}

	public String getStuid() {
		return stuid;
	}

	public void setStuid(String stuid) {
		this.stuid = stuid;
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}
	
	

}
