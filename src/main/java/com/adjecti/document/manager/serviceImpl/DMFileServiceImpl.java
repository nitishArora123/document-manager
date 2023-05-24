package com.adjecti.document.manager.serviceImpl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.Clock;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.adjecti.document.manager.model.DMFile;
import com.adjecti.document.manager.model.DMFileType;
import com.adjecti.document.manager.repository.DMFileRepository;
import com.adjecti.document.manager.repository.DMFileTypeRepository;
import com.adjecti.document.manager.service.DMFileService;
import com.adjecti.document.manager.util.ReflectionBeanUtil;

@Service
public class DMFileServiceImpl implements DMFileService {

	@Autowired
	private DMFileRepository docRepo;

	@Autowired
	private DMFileTypeRepository docTypeRepo;

	/* private static final String UPLOAD_PATH = ""; */

	@Override
	public DMFile upload(String path, MultipartFile file) throws IOException {
		try {
			String fileName = file.getOriginalFilename();
			String fullPath = path + "/" + fileName;
			System.out.println(fullPath + "pathhhh");
			File f = new File(path);
			if (!f.exists()) {
				f.mkdir();
			}
			DMFile document = new DMFile();
			String nameWithoutExtension = fileName.substring(0, fileName.lastIndexOf('.'));
			document.setName(nameWithoutExtension);
			document.setCreatedDate(new Date());
			document.setSystemPath(fullPath);
			document.setDescription(document.getDescription());
			file.transferTo(new File(fullPath));
			return docRepo.save(document);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<DMFile> getAll() {
		// TODO Auto-generated method stub
		return docRepo.findAll();
	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub
		docRepo.deleteById(id);
	}

	@Override
	public DMFile update(DMFile document, long id) {
		Optional<DMFile> existingDocumentOptional = docRepo.findById(id);
		if (!existingDocumentOptional.isPresent()) {
			throw new RuntimeException("Document not found");
		}
		DMFile existingDocument = existingDocumentOptional.get();

		existingDocument.setName(document.getName());
		existingDocument.setDescription(document.getDescription());
		existingDocument.setCreatedDate(new Date());
		return docRepo.save(existingDocument);
	}

	@Override
	public List<DMFile> uploadMultiple(String path, MultipartFile[] files) throws IllegalStateException, IOException {
		List<DMFile> uploadedDocuments = new ArrayList<>();
		for (MultipartFile file : files) {
			String name = file.getOriginalFilename();
			String extension = name.substring(name.lastIndexOf("."));
			String[] nameArray = name.split(".pdf");
			String fileNameWithoutExtension = null;
			for (int i = 0; i < nameArray.length; i++) {
				fileNameWithoutExtension = nameArray[0];
			}
			Clock clock = Clock.systemDefaultZone();
			long milliseconds = clock.millis();
			String newFileName = fileNameWithoutExtension + milliseconds + extension;
			String fullPath = path + "/" + newFileName;
			File f = new File(path);
			if (!f.exists()) {
				f.mkdir();
			}
			DMFile documentManager = new DMFile();
			DMFileType docType = new DMFileType();
			documentManager.setName(newFileName);
			;
			documentManager.setCreatedDate(new Date());
			docType.setDmFileType(docType.getDmFileType());
			documentManager.setDescription(documentManager.getDescription());
			file.transferTo(new File(fullPath));
			uploadedDocuments.add(docRepo.save(documentManager));
		}
		return uploadedDocuments;
	}

	@Override
	public Optional<DMFile> getById(long id) {
		// TODO Auto-generated method stub
		return docRepo.findById(id);
	}

	@Override
	public DMFile create(Map<Object, Object> documentManager) throws ClassNotFoundException {
		// DMFileType docType = new DMFileType();

		long docId = Long.parseLong(documentManager.get("docId").toString());

		System.out.println("docId=--->" + docId);
		String name = documentManager.get("name").toString();
		String description = documentManager.get("description").toString();
		String documentType = documentManager.get("dmFileType").toString();
		DMFileType dmfile = docTypeRepo.findById(Long.parseLong(documentType)).get();
		// DMFile docManager1 = update(docManager, docId);
		DMFile docManager = docRepo.findById(docId).get();

		docManager.setName(name);
		docManager.setDescription(description);
		docManager.setDmFileType(dmfile);
		return update(docManager, docId);

	}

	@Override
	public List<DMFile> uploadFilesInFolder(String folderPath, List<MultipartFile> files) throws IOException {
		List<DMFile> uploadedDocuments = new ArrayList<>();
		System.out.println(folderPath + "folderpathhhhhh");
		System.out.println(files + "filesssssss");
		File folder = new File(folderPath);
		if (!folder.exists()) {
			folder.mkdirs();
		}

		for (MultipartFile file : files) {
			String fileName = file.getOriginalFilename();
			String fullPath = folderPath + File.separator + fileName;

			DMFile document = new DMFile();
			document.setName(fileName);
			document.setCreatedDate(new Date());
			document.setSystemPath(fullPath);
			document.setDescription(document.getDescription());

			file.transferTo(new File(fullPath));

			DMFile savedDocument = docRepo.save(document);
			uploadedDocuments.add(savedDocument);
		}

		return uploadedDocuments;
	}

}
