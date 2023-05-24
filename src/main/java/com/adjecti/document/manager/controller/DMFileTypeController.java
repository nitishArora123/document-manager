package com.adjecti.document.manager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adjecti.document.manager.model.DMFileType;
import com.adjecti.document.manager.service.DMFileTypeService;

@CrossOrigin("*")
@RestController
@RequestMapping("api/v1/docType")
public class DMFileTypeController {

	@Autowired
	private DMFileTypeService docTypeService;
	
	@PostMapping
	public DMFileType create(@RequestBody DMFileType docType) {
		System.out.println(docType );
		return docTypeService.create(docType);
	}
	
	@GetMapping
	public List<DMFileType> getAll(){
		return docTypeService.getAll();
	}
	
	@GetMapping("{id}")
	public DMFileType getById(@PathVariable ("id") long id) {
		return docTypeService.getById(id);
	}
	
	@DeleteMapping("{id}")
	public void delete(@PathVariable ("id") long id) {
		docTypeService.delete(id);
	}
	
	@PutMapping("{id}")
	public DMFileType update(@RequestBody DMFileType docType, @PathVariable ("id") long id) {
		return docTypeService.update(docType, id);
	}
}
