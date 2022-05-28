package Enum;

public enum UserEnum {
	
	SUCCESS(0,"SUCCESS"),
	USERNAME_PASSWORD_NULL(1,"用户名或密码不能为空"),
	USE_NOT_EXIST(2,"用户不存在"),
	PASSWORD_NOT_MATCH(3,"密码不正确");
	
	private int code;
	private String desc;
	
	UserEnum(int code,String desc)
	{
		this.code=code;
		this.desc=desc;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

}
