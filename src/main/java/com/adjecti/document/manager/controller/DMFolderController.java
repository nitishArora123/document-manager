package com.adjecti.document.manager.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLConnection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.adjecti.document.manager.model.DMFile;
import com.adjecti.document.manager.model.DMFolder;
import com.adjecti.document.manager.service.DMFolderService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@CrossOrigin("*")
@RequestMapping("api/v1/folder")
@RestController
public class DMFolderController {

	
	@Value("${folder.create-dir}")
	private String path;
	
	@Autowired 
	private DMFolderService folderService;
	
	@PostMapping
	public DMFolder create(@RequestBody DMFolder folder) {
		System.out.println("create");
	    return folderService.create(folder, path);
	}
	 
	@DeleteMapping("{id}")
	public void delete(@PathVariable("id") long id) {
		folderService.delete(id);
	}
	
	@GetMapping
	public List<DMFolder> getAll(){
		return folderService.getAll();
	}
	
    @GetMapping("{id}")
	public DMFolder getById(@PathVariable("id") long id) {
    	return folderService.getById(id);
    }
    
	@PutMapping("{id}")
    public DMFolder update(@PathVariable("id") long id,@RequestBody DMFolder folder) {
		System.out.println("update");
		return folderService.update(id,folder, path);
	}

	@PostMapping("/test")
	public DMFolder createFolder(@RequestBody DMFolder folder) {
		System.out.println("create folder"); 
		folderService.create( folder, path);
	    return folder;
	}

	@GetMapping("/p/{parentId}")
	public List<DMFolder> getByParentId(@PathVariable ("parentId") long parentId){
		return folderService.getByParentId(parentId);
	}
	
	@GetMapping("/download/{id}")
	public ResponseEntity<byte[]> downloadFolder(@PathVariable("id") long id) {
	    
		System.out.println(path);
		DMFolder folder = folderService.getById(id);
	    if (folder == null) {
	        return ResponseEntity.notFound().build();
	    }

	    byte[] folderData = folderService.downloadFolder(id);
	    if (folderData == null) {
	        return ResponseEntity.notFound().build();
	    }else {

	    String folderName = folder.getName();
	    System.out.println(folder);
	    String fileName = folderName + ".zip";

	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
	    headers.setContentDispositionFormData("attachment", fileName);
	    
	    return ResponseEntity.ok().headers(headers).body(folderData);
	    }
	}
}
