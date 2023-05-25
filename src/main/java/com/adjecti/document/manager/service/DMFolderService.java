package com.adjecti.document.manager.service;

import java.io.File;
import java.util.List;

import org.springframework.data.repository.query.Param;

import com.adjecti.document.manager.model.DMFolder;

public interface DMFolderService {

	public DMFolder create(DMFolder folder,String path);
	
	public void delete(long id);
	
	public DMFolder update(long id,DMFolder folder,String path);
	
	public List<DMFolder> getAll();
	
	public DMFolder getById(long id);
	
	public List<DMFolder> getByParentId(long parentId);
	
	public DMFolder createParentFolder(DMFolder dmFolder,String path);
	
	public byte[] downloadFolder(long id);


}
