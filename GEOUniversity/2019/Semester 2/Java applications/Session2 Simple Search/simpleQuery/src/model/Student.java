package model;

/**
 * Student entity. @author MyEclipse Persistence Tools
 */

public class Student implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private Integer age;
	private double score;

	// Constructors

	/** default constructor */
	public Student() {
	}

	/** full constructor */
	public Student(String name, Integer age, double score) {
		this.name = name;
		this.age = age;
		this.score = score;
	}

	public Student(String name, Integer age) {
		this.name = name;
		this.age = age;		
	}
	
	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return this.age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public double getScore() {
		return this.score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", age=" + age + ", score=" + score + "]";
	}

}