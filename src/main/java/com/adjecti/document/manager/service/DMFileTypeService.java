package com.adjecti.document.manager.service;

import java.util.List;

import com.adjecti.document.manager.model.DMFileType;

public interface DMFileTypeService {

	public List<DMFileType> getAll();
	
	public DMFileType getById(long id);
	
	public DMFileType update (DMFileType docType , long id);
	
	public void delete(long id);
	
	public DMFileType create(DMFileType docType);
	
	
}
