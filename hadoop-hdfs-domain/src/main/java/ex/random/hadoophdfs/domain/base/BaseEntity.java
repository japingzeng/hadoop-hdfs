package ex.random.hadoophdfs.domain.base;

import java.io.Serializable;
import java.util.Date;

public class BaseEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private Date createdTime;
	private Date modifiedTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public BaseEntity setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
		return this;
	}

	public Date getModifiedTime() {
		return modifiedTime;
	}

	public BaseEntity setModifiedTime(Date modifiedTime) {
		this.modifiedTime = modifiedTime;
		return this;
	}

}
