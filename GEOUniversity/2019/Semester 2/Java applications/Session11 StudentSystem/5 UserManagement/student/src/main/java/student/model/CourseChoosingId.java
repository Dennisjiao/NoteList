package student.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * CourseChoosingId entity. @author MyEclipse Persistence Tools
 */
@Embeddable

public class CourseChoosingId implements java.io.Serializable {

	// Fields

	private String stuid;
	private String cid;

	// Constructors

	/** default constructor */
	public CourseChoosingId() {
	}

	/** full constructor */
	public CourseChoosingId(String stuid, String cid) {
		this.stuid = stuid;
		this.cid = cid;
	}

	// Property accessors

	@Column(name = "stuid", nullable = false, length = 20)

	public String getStuid() {
		return this.stuid;
	}

	public void setStuid(String stuid) {
		this.stuid = stuid;
	}

	@Column(name = "cid", nullable = false, length = 32)

	public String getCid() {
		return this.cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof CourseChoosingId))
			return false;
		CourseChoosingId castOther = (CourseChoosingId) other;

		return ((this.getStuid() == castOther.getStuid()) || (this.getStuid() != null && castOther.getStuid() != null
				&& this.getStuid().equals(castOther.getStuid())))
				&& ((this.getCid() == castOther.getCid()) || (this.getCid() != null && castOther.getCid() != null
						&& this.getCid().equals(castOther.getCid())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getStuid() == null ? 0 : this.getStuid().hashCode());
		result = 37 * result + (getCid() == null ? 0 : this.getCid().hashCode());
		return result;
	}

}