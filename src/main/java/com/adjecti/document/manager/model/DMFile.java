package com.adjecti.document.manager.model;

import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
@Entity
public class DMFile {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String name;
	private String description;
	private Date createdDate;
	private Date updatedDate;
	private long createdBy;
	private long updatedBy;
	private String systemPath;
	
	@OneToOne
	@JoinColumn(name = "dmfiletype_id")
	private DMFileType dmFileType;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	/*
	 * public long getParentId() { return parentId; } public void setParentId(long
	 * parentId) { this.parentId = parentId; }
	 */
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public DMFileType getDmFileType() {
		return dmFileType;
	}
	public void setDmFileType(DMFileType dmFileType) {
		this.dmFileType = dmFileType;
		}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public Date getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	public long getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(long createdBy) {
		this.createdBy = createdBy;
	}
	public long getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(long updatedBy) {
		this.updatedBy = updatedBy;
	}
	public String getSystemPath() {
		return systemPath;
	}
	public void setSystemPath(String systemPath) {
		this.systemPath = systemPath;
	}
	public DMFile(long id, String name, long parentId, String description, DMFileType dmFileType, Date createdDate,
			Date updatedDate, long createdBy, long updatedBy, String systemPath) {
		super();
		this.id = id;
		this.name = name;
		//this.parentId = parentId;
		this.description = description;
		//this.dmFileType = dmFileType;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
		this.createdBy = createdBy;
		this.updatedBy = updatedBy;
		this.systemPath = systemPath;
	}
	public DMFile() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
}
