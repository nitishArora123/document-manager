package com.adjecti.document.manager.model;

import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

@Entity
public class DMFolder {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String name;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "parent_id")
	private List<DMFile> dmFile;

	private Date createdDate;
	private Date updatedDate;
	private long parentId;
	private long createdBy;
	private long updatedBy;
	private String systemPath;
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
	public List<DMFile> getDmFile() {
		return dmFile;
	}
	public void setDmFile(List<DMFile> dmFile) {
		this.dmFile = dmFile;
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
	public long getParentId() {
		return parentId;
	}
	public void setParentId(long parentId) {
		this.parentId = parentId;
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
	public DMFolder(long id, String name, List<DMFile> dmFile, Date createdDate, Date updatedDate, long parentId,
			long createdBy, long updatedBy, String systemPath) {
		super();
		this.id = id;
		this.name = name;
		//this.dmFile = dmFile;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
		this.parentId = parentId;
		this.createdBy = createdBy;
		this.updatedBy = updatedBy;
		this.systemPath = systemPath;
	}
	public DMFolder() {
		super();
		// TODO Auto-generated constructor stub
	}

}
