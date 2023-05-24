package com.adjecti.document.manager.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adjecti.document.manager.exception.DataNotFoundException;
import com.adjecti.document.manager.model.DMFileType;
import com.adjecti.document.manager.repository.DMFileTypeRepository;
import com.adjecti.document.manager.service.DMFileTypeService;


@Service
public class DMFileTypeServiceImpl implements DMFileTypeService{

	@Autowired
	private DMFileTypeRepository docRepo;
	
	@Override
	public List<DMFileType> getAll() {
		// TODO Auto-generated method stub
		return docRepo.findAll();
	}

	@Override
	public DMFileType getById(long id) {
		// TODO Auto-generated method stub
		return docRepo.findById(id).get();
	}

	@Override
	public DMFileType update(DMFileType docType, long id) {
		DMFileType doc = docRepo.findById(id).get();
		if (doc != null) {
			doc.setDmFileType(doc.getDmFileType());
			System.out.println("in update");
			docRepo.save(doc);
		} else {
			throw new DataNotFoundException("Data For Particular Id is Not Available");
		}

		return doc;
	}


	@Override
	public void delete(long id) {
		docRepo.deleteById(id);
	}

	@Override
	public DMFileType create(DMFileType docType) {
		// TODO Auto-generated method stub
		return docRepo.save(docType);
	}	
	
}
