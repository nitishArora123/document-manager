package com.adjecti.document.manager.serviceImpl;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adjecti.document.manager.model.DMFolder;
import com.adjecti.document.manager.repository.DMFolderRepository;
import com.adjecti.document.manager.service.DMFolderService;

@Service
public class DMFolderServiceImpl implements DMFolderService {

	@Autowired
	private DMFolderRepository folderRepo;

	@Override
	public DMFolder create(DMFolder folder, String path) {
		// TODO Auto-generated method stub
		String fullpath = path + folder.getName();
		File file = new File(fullpath);
		System.out.println(fullpath + " checking...");
	    
	    if (!file.exists()) {
	    	file.mkdir();	
	        System.out.println("Directory is created");
	       // DMFolder dm = new DMFolder();
	        
	        folder.setSystemPath(fullpath);
	        folder.setCreatedDate(new Date());
	        System.out.println(folder);
	        folderRepo.save(folder);
	        
	  //      return true;
	    }
		return folderRepo.save(folder);
	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub
		folderRepo.deleteById(id);
		
	}

	@Override
	public DMFolder update( long id,DMFolder folder,String path) {
		DMFolder fold = folderRepo.findById(id).orElse(null);
		if (fold != null) {
			String fullpath = path + folder.getName();
			File file=new File(fullpath);
			file.renameTo(file);	
			fold.setName(folder.getName());
			fold.setCreatedDate(folder.getCreatedDate());
			fold.setSystemPath(fullpath);
			fold.setUpdatedDate(new Date());
			fold = folderRepo.save(fold);
			System.out.println(fold.getUpdatedDate());
		}
		return fold;
	}


	@Override
	public List<DMFolder> getAll() {
		// TODO Auto-generated method stub
		return folderRepo.getAll();
	}

	@Override
	public DMFolder getById(long id) {
		// TODO Auto-generated method stub
		return folderRepo.findById(id).get();
	}

	
	@Override
	public List<DMFolder> getByParentId(long parentId) {
		// TODO Auto-generated method stub
			
		List<DMFolder> list = folderRepo.getByParentId(parentId);
		System.out.println(list);
		return list;
	}

	@Override
	public DMFolder createParentFolder(DMFolder dmFolder, String path) {
	    String parentPath = path + dmFolder.getParentId();
	    File parentFolder = new File(parentPath);
	    System.out.println(parentPath + " checking...");
	    
	    if (!parentFolder.exists()) {
	        parentFolder.mkdir();
	        System.out.println("Parent directory is created");
	    }
	    
	    String path2 = parentPath + "/" + dmFolder.getName();
	    File file = new File(path2);
	    System.out.println(path2 + " checking...");
	    
	    if (!file.exists()) {
	        file.mkdir();
	        System.out.println("Directory is created");
	    }
	    
	    dmFolder.setSystemPath(path);
	    dmFolder.setCreatedDate(new Date());
	    System.out.println(dmFolder);
	    
	    return folderRepo.save(dmFolder);
	}

	@Override
	public byte[] downloadFolder(long id) {
		DMFolder folder = folderRepo.findById(id).orElse(null);
		if (folder == null) {
			return null; 
		}

		String folderPath = folder.getSystemPath();
		File folderFile = new File(folderPath);
		if (!folderFile.exists() || !folderFile.isDirectory()) {
			return null;
		}

		try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
				ZipOutputStream zos = new ZipOutputStream(baos)) {
System.out.println(zos + "zzzzz");
System.out.println(baos + "baos");
			addFilesToZip(folderFile, folderFile.getName(), zos);

			zos.finish();
			zos.close();

			return baos.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
			return null; 
		}
	}
	
	private void addFilesToZip(File folder, String parentFolderPath, ZipOutputStream zos) throws IOException {
		File[] files = folder.listFiles();
		System.out.println(files + "listsssss");
		if (files != null) {
			for (File file : files) {
				if (file.isDirectory()) {
					addFilesToZip(file, parentFolderPath + "/" + file.getName(), zos);
				} else {
					Path filePath = file.toPath();
					String entryPath = parentFolderPath + "/" + filePath.getFileName().toString();
					System.out.println(entryPath);
					ZipEntry entry = new ZipEntry(entryPath);
					zos.putNextEntry(entry);
					zos.write(Files.readAllBytes(filePath));
					zos.closeEntry();
				}
			}
		}
	}

}

