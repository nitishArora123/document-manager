package com.adjecti.document.manager.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import com.adjecti.document.manager.model.DMFile;

public interface DMFileService {

	public DMFile upload(String path , MultipartFile file) throws IOException;	
	
	public List<DMFile> getAll();
	
	public void delete(long id);
	
	public DMFile update(DMFile document,long id);
	
	public List<DMFile> uploadMultiple(String path , MultipartFile[] file) throws IllegalStateException, IOException;	

	public DMFile getById(long id);

	DMFile create(Map<Object, Object> docManager) throws ClassNotFoundException;

	public List<DMFile> uploadFilesInFolder(String folderPath, List<MultipartFile> files) throws IOException;
	
	public ResponseEntity<byte[]> downloadFile(long id) throws IOException;

	public ResponseEntity<byte[]> previewFile(long id);
	
}
