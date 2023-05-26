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
import org.springframework.core.io.FileSystemResource;
import org.springframework.data.util.StreamUtils;
import org.springframework.stereotype.Service;

import com.adjecti.document.manager.model.DMFolder;
import com.adjecti.document.manager.repository.DMFolderRepository;
import com.adjecti.document.manager.service.DMFolderService;

import jakarta.servlet.http.HttpServletResponse;

@Service
public class DMFolderServiceImpl implements DMFolderService {

	@Autowired
	private DMFolderRepository folderRepo;

	@Override
	public DMFolder create(DMFolder folder, String path) {
		// TODO Auto-generated method stub
		System.out.println("create    "+ folder);
	
	
		String fullpath;
		if(folder.getParentId() != 0) {
			DMFolder parentFolder = getById(folder.getParentId());
			System.out.println(parentFolder);
			fullpath = parentFolder.getSystemPath()+"/"+ folder.getName();
		}else{
			
			fullpath = path + folder.getName();
		}
		
		File file = new File(fullpath);
		System.out.println(fullpath + " checking...");
	    
	    if (!file.exists()) {
	    	
	    	file.mkdir();	
	        System.out.println("Directory is created");
	        folder.setCreatedDate(new Date());
			folder.setSystemPath(fullpath);
	        System.out.println(folder);
	        folderRepo.save(folder);
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
		System.out.println("dm folder       i   "+dmFolder);
		
	    String parentPath = path + dmFolder.getParentId();
	    File parentFolder = new File(parentPath);
	    System.out.println(parentPath + " checking.......");
	    
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
	public void downloadFolder(HttpServletResponse response, long id) {
		    DMFolder folder = folderRepo.findById(id).get();
		    if (folder == null) {
		       System.out.println("empty folder");
		        return;
		    }

		    String folderPath = folder.getSystemPath();
		    File folderFile = new File(folderPath);
		    if (!folderFile.exists() || !folderFile.isDirectory()) {
		    	System.out.println("not exists");
		    }else {
		    	System.out.println("Exists and its a directory");
		    }

		    try {
		        response.setContentType("application/zip");
		        response.setHeader("Content-Disposition", "attachment; filename=" + folder.getName() + ".zip");

		        try (ZipOutputStream zos = new ZipOutputStream(response.getOutputStream())) {
		        	System.out.println("in zosssssss");
		        	System.out.println(folderFile.getName() + "folder file name");
		        	System.out.println(folderFile + "folder file");
		            addFilesToZip(folderFile, folderFile.getName(), zos);
		            System.out.println("after adding");
		        }
		    } catch (IOException e) {
		        e.printStackTrace();
		    }
		}

	public void addFilesToZip(File folder, String parentFolderPath, ZipOutputStream zos) throws IOException {
	    System.out.println(folder + "fulll");
	    System.out.println(parentFolderPath);
	    System.out.println(zos);

	    System.out.println("Files in " + folder.getAbsolutePath() + ":");
	    
	    // Create an entry for the empty folder
	    String folderEntryPath = parentFolderPath + "/";
	    ZipEntry folderEntry = new ZipEntry(folderEntryPath);
	    zos.putNextEntry(folderEntry);
	    zos.closeEntry();

	    File[] files = folder.listFiles();
	    if (files != null && files.length > 0) {
	        for (File file : files) {
	            System.out.println(file + "is it a file?");
	            System.out.println(file.getName() + " |||||||||| " + file.getAbsolutePath());

	            if (file.isDirectory()) {
	                String entryPath = parentFolderPath + "/" + file.getName() + "/";
	                System.out.println(entryPath + " directory zip");
	                ZipEntry entry = new ZipEntry(entryPath);
	                zos.putNextEntry(entry);
	                addFilesToZip(file, entryPath, zos);
	                zos.closeEntry();
	            } else {
	                Path filePath = file.toPath();
	                String entryPath = parentFolderPath + "/" + file.getName();
	                System.out.println(entryPath + " not dir zip");
	                ZipEntry entry = new ZipEntry(entryPath);
	                zos.putNextEntry(entry);
	                Files.copy(filePath, zos);
	                zos.closeEntry();
	            }
	        }
	    }
	}
}

