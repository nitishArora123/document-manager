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
import jakarta.persistence.ManyToOne;
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
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "folder_id")
	private DMFolder dmFolder;
	
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public DMFolder getDmFolder() {
		return dmFolder;
	}

	public void setDmFolder(DMFolder dmFolder) {
		this.dmFolder = dmFolder;
	}

	public DMFileType getDmFileType() {
		return dmFileType;
	}

	public void setDmFileType(DMFileType dmFileType) {
		this.dmFileType = dmFileType;
	}

	public DMFile(long id, String name, String description, Date createdDate, Date updatedDate, long createdBy,
			long updatedBy, String systemPath, DMFolder dmFolder, DMFileType dmFileType) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
		this.createdBy = createdBy;
		this.updatedBy = updatedBy;
		this.systemPath = systemPath;
		this.dmFolder = dmFolder;
		this.dmFileType = dmFileType;
	}

	public DMFile() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "DMFile [id=" + id + ", name=" + name + ", description=" + description + ", createdDate=" + createdDate
				+ ", updatedDate=" + updatedDate + ", createdBy=" + createdBy + ", updatedBy=" + updatedBy
				+ ", systemPath=" + systemPath + ", dmFolder=" + dmFolder + ", dmFileType=" + dmFileType + "]";
	}

	
	
}
