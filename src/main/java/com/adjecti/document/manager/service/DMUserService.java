package com.adjecti.document.manager.service;

import java.util.List;
import java.util.Optional;

import com.adjecti.document.manager.model.DMUser;

public interface DMUserService {

		public  DMUser create(DMUser user);

	    public  Optional<DMUser> getById(long id);

	    public   List<DMUser> getAll();

	    public DMUser update(DMUser user, long id);

	    public void delete(long id);
	

	
}
