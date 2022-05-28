package student.model;

import java.util.Date;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

public class StuInfo {
	@NotEmpty
	private String stuid;
	@NotEmpty
	private String stuname;
	
	private String gender;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date birthday;
	
	private String classname;
	
	private String address;
	
	private User user;
	
	
	public StuInfo() {
		super();
	}

	public StuInfo(String stuid, String stuname, String gender, Date birthday, String classname, String address,
			User user) {
		super();
		this.stuid = stuid;
		this.stuname = stuname;
		this.gender = gender;
		this.birthday = birthday;
		this.classname = classname;
		this.address = address;
		this.user = user;
	}

	public String getStuid() {
		return stuid;
	}

	public void setStuid(String stuid) {
		this.stuid = stuid;
	}

	public String getStuname() {
		return stuname;
	}

	public void setStuname(String stuname) {
		this.stuname = stuname;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getClassname() {
		return classname;
	}

	public void setClassname(String classname) {
		this.classname = classname;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "StuInfo [stuid=" + stuid + ", stuname=" + stuname + ", gender=" + gender + ", birthday=" + birthday
				+ ", classname=" + classname + ", address=" + address + ", user=" + user + "]";
	}

}
