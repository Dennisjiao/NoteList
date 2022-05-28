package model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Country entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "country", catalog = "test")

public class Country implements java.io.Serializable {

	// Fields

	private Integer cid;
	private String cname;
	private Set<Minister> ministers = new HashSet<Minister>(0);

	// Constructors

	/** default constructor */
	public Country() {
	}

	/** minimal constructor */
	public Country(Integer cid) {
		this.cid = cid;
	}
	
	public Country(Integer cid, String cname) {
		this.cid = cid;
		this.cname = cname;		
	}
	
	/** full constructor */
	public Country(Integer cid, String cname, Set<Minister> ministers) {
		this.cid = cid;
		this.cname = cname;
		this.ministers = ministers;
	}

	// Property accessors
	@Id

	@Column(name = "cid", unique = true, nullable = false)

	public Integer getCid() {
		return this.cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	@Column(name = "cname", length = 45)

	public String getCname() {
		return this.cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	//mappedBy = "country"相当于inverse=true,表明外键的维护权交给拥有方minister的country属性
		/*
		 * 1.只有OneToOne，OneToMany，ManyToMany上才有mappedBy属性，ManyToOne不存在该属性；
		 * 2.mappedBy标签一定是定义在被拥有方的(一的一方)，他指向拥有方（多的一方）；
		 * 3.mappedBy的含义，应该理解为，拥有方能够自动维护跟被拥有方的关系，当然，如果从被拥有方，通过手工强行来维护拥有方的关系也是可以做到的；
		 * 4.mappedBy跟joinColumn/JoinTable总是处于互斥的一方
		 * 5.mappedBy的值一定是多方的关系属性，不能是一方的属性
		 * 6.在@OneToMany里加入mappedBy属性可以避免生成一张中间表。
		 * 如果不加mappedby，也不加joincolumn时，hibernate会自动帮你生成一张中间表,帮你实现表之间的联系。通过添
		 * 加mappedby或joincolumn可以避免生成中间表。但mappedby或joincolumn不能同时出现。
		 */
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "country")
	//用来指定与所操作实体或实体集合相关联的数据库表中的列字段
	//@JoinColumn(name="cid")
	public Set<Minister> getMinisters() {
		return this.ministers;
	}

	public void setMinisters(Set<Minister> ministers) {
		this.ministers = ministers;
	}

}