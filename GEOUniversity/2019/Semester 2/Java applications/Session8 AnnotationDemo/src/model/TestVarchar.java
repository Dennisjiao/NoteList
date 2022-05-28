package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * TestVarchar entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "test_varchar", catalog = "test")

public class TestVarchar implements java.io.Serializable {

	// Fields

	private String id;
	private String name;

	// Constructors

	/** default constructor */
	public TestVarchar() {
	}

	/** full constructor */
	public TestVarchar(String name) {
		this.name = name;
	}

	// Property accessors
	
	@Id
	@GeneratedValue(generator = "generator")
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Column(name = "id", unique = true, nullable = false, length = 40)

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "name")

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}