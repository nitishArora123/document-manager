package com.adjecti.document.manager.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.adjecti.document.manager.model.DMFile;
import com.adjecti.document.manager.service.DMFileService;
import com.fasterxml.jackson.databind.ObjectMapper;

@CrossOrigin("*")
@RequestMapping("/api/v1/documentManager")
@RestController
public class DMFileController {

	@Autowired
	private DMFileService fileService;

	@Value("${file.upload-dir}")
	private String path;

	@PostMapping("/upload-multiple")
	public ResponseEntity<List<DMFile>> uploadMultipleDocuments(@RequestParam("documentImage") MultipartFile[] files)
			throws IOException {
		List<DMFile> uploadedDocuments = fileService.uploadMultiple(path, files);
		return new ResponseEntity<>(uploadedDocuments, HttpStatus.OK);
	}

	@PostMapping("/upload")
	public ResponseEntity<DMFile> uploadDocument(@RequestParam("documentImage") MultipartFile file) throws IOException {
		System.out.println("in controller" + file);
		return new ResponseEntity<DMFile>(fileService.upload(path, file), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<DMFile> create(@RequestBody Map<Object, Object> documentManager)
			throws ClassNotFoundException {

//		DocumentManager docM=new DocumentManager();

//		ObjectMapper objMap=new ObjectMapper();
//		
//		DocumentManager docM=objMap.convertValue(documentManager,DocumentManager.class);

		System.out.println(documentManager);
		return new ResponseEntity<DMFile>(fileService.create(documentManager), HttpStatus.OK);
//		return null;		
	}

	@PostMapping("/uploadInFolder")
	public ResponseEntity<List<DMFile>> uploadFilesInFolder(@RequestParam("folderPath") String folderPath,
			@RequestParam("files") List<MultipartFile> files) {
		try {
			List<DMFile> uploadedFiles = fileService.uploadFilesInFolder(folderPath, files);
			return ResponseEntity.ok(uploadedFiles);
		} catch (IOException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GetMapping("/download/{documentName}")
	public ResponseEntity<ByteArrayResource> downloadDocument(@PathVariable String documentName) throws IOException {
		byte[] fileBytes = fileService.downloadDocument(documentName);
		System.out.println("file download --->"+fileBytes);
		ByteArrayResource resource = new ByteArrayResource(fileBytes);
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + documentName)
				.contentType(MediaType.APPLICATION_OCTET_STREAM).body(resource);
	}

	@GetMapping
	public List<DMFile> getAll() {
		return fileService.getAll();
	}

	@GetMapping("{id}")
	public Optional<DMFile> getById(@PathVariable("id") long id) {
		return fileService.getById(id);
	}

	@DeleteMapping("{id}")
	public void delete(@PathVariable("id") long id) {
		fileService.delete(id);
	}

	@PutMapping("{id}")
	public DMFile update(@PathVariable("id") long id, @RequestBody DMFile docManager) {
		return fileService.update(docManager, id);
	}
}
