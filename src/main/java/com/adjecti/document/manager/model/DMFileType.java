package com.adjecti.document.manager.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class DMFileType {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String dmFileType;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getDmFileType() {
		return dmFileType;
	}
	public void setDmFileType(String dmFileType) {
		this.dmFileType = dmFileType;
	}
	public DMFileType(long id, String dmFileType) {
		super();
		this.id = id;
		this.dmFileType = dmFileType;
	}
	public DMFileType() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	
}
