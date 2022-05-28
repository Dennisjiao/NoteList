package student.model;

import javax.validation.Valid;
import javax.validation.constraints.Size;

public class User {
	@Size(min=3,max=30)
	private String username;
	@Size(min=3,max=30)
	private String password;
	
	private Integer usertype;
	@Valid
	private StuInfo stuinfo;
	
	@Size(min=4,max=4)
	private String securityCode;

	public User() {
		super();
	}
	
	public User(String username, String password, Integer usertype) {
		super();
		this.username = username;
		this.password = password;
		this.usertype = usertype;		
		this.stuinfo=new StuInfo();
		this.stuinfo.setStuid(null);
	}

	public User(String username, String password, Integer usertype, StuInfo stuinfo, String securityCode) {
		super();
		this.username = username;
		this.password = password;
		this.usertype = usertype;
		this.stuinfo = stuinfo;
		this.securityCode = securityCode;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getUsertype() {
		return usertype;
	}

	public void setUsertype(Integer usertype) {
		this.usertype = usertype;
	}

	public StuInfo getStuinfo() {
		return stuinfo;
	}

	public void setStuinfo(StuInfo stuinfo) {
		this.stuinfo = stuinfo;
	}

	public String getSecurityCode() {
		return securityCode;
	}

	public void setSecurityCode(String securityCode) {
		this.securityCode = securityCode;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", usertype=" + usertype + ", stuinfo="
				+ stuinfo + ", securityCode=" + securityCode + "]";
	}
	/*
	空检查
	@Null       验证对象是否为null
	@NotNull    验证对象是否不为null, 无法查检长度为0的字符串.@NotNull(message="名字不能为空")
	@NotBlank 检查约束字符串是不是Null还有被Trim的长度是否大于0,只对字符串,且会去掉前后空格.
	@NotEmpty 检查约束元素是否为NULL或者是EMPTY.
	 
	Booelan检查
	@AssertTrue     验证 Boolean 对象是否为 true  
	@AssertFalse    验证 Boolean 对象是否为 false  
	 
	长度检查
	@Size(min=, max=) 验证对象（Array,Collection,Map,String）长度是否在给定的范围之内.@Size(min = 5, max = 10, message = "最大是 10，最小是 5，null元素被认定为有效")  
	@Length(min=, max=) Validates that the annotated string is between min and max included.
	日期检查
	@Past           验证 Date 和 Calendar 对象是否在当前时间之前  
	@Future     验证 Date 和 Calendar 对象是否在当前时间之后  
	@Pattern    验证 String 对象是否符合正则表达式的规则.@Pattern(regexp = "[1-7]{1}", message = "reason的类型值为1-7中的一个类型")
	 
	数值检查，建议使用在Stirng,Integer类型，不建议使用在int类型上，因为表单值为“”时无法转换为int，但可以转换为Stirng为"",Integer为null
	@Min            验证 Number 和 String 对象是否大等于指定的值  
	@Max            验证 Number 和 String 对象是否小等于指定的值.@Max(value=120,message="年龄最大不能查过120")  
	@DecimalMax 被标注的值必须不大于约束中指定的最大值. 这个约束的参数是一个通过BigDecimal定义的最大值的字符串表示.小数存在精度
	@DecimalMin 被标注的值必须不小于约束中指定的最小值. 这个约束的参数是一个通过BigDecimal定义的最小值的字符串表示.小数存在精度
	@Digits     验证 Number 和 String 的构成是否合法 .@Digits(integer = 4, fraction = 4, message = "整数部分最大4位，小数部分最大4位") 
	@Digits(integer=,fraction=) 验证字符串是否是符合指定格式的数字，interger指定整数精度，fraction指定小数精度。
	@Range(min=, max=) Checks whether the annotated value lies between (inclusive) the specified minimum and maximum.
	@Range(min=10000,max=50000,message="range.bean.wage")
	private BigDecimal wage;
	 
	@Valid 递归的对关联对象进行校验, 如果关联对象是个集合或者数组,那么对其中的元素进行递归校验,如果是一个map,则对其中的值部分进行校验.(是否进行递归验证)
	@CreditCardNumber信用卡验证
	@Email 验证是否是邮件地址，如果为null,不进行验证，算通过验证。@Email(message="邮箱格式错误")
	@ScriptAssert(lang= ,script=, alias=)
	@URL(protocol=,host=, port=,regexp=, flags=)

	*/

}
