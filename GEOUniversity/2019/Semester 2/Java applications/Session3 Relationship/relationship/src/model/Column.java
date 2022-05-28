package model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Column entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "column", catalog = "test")

public class Column implements java.io.Serializable {

	// Fields

	private Integer id;
	private Column column;
	private String name;
	private Set<Column> columns = new HashSet<Column>(0);

	// Constructors

	/** default constructor */
	public Column() {
	}

	/** minimal constructor */
	public Column(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public Column(Integer id, Column column, String name, Set<Column> columns) {
		this.id = id;
		this.column = column;
		this.name = name;
		this.columns = columns;
	}

	// Property accessors
	@Id

	@javax.persistence.Column(name = "id", unique = true, nullable = false)

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cid")

	public Column getColumn() {
		return this.column;
	}

	public void setColumn(Column column) {
		this.column = column;
	}

	@javax.persistence.Column(name = "name", length = 45)

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "column")

	public Set<Column> getColumns() {
		return this.columns;
	}

	public void setColumns(Set<Column> columns) {
		this.columns = columns;
	}

}