package com.adjecti.document.manager.serviceImpl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adjecti.document.manager.model.DMUser;
import com.adjecti.document.manager.repository.DMUserRepository;
import com.adjecti.document.manager.service.DMUserService;

@Service
public class DMUserServiceImpl implements DMUserService{

	@Autowired
	private DMUserRepository userRepository;
	
	 @Override
	    public DMUser create(DMUser user) {
	        return userRepository.save(user);
	    }

	    @Override
	    public Optional<DMUser> getById(long id) {
	        return userRepository.findById(id);
	    }

	    @Override
	    public List<DMUser> getAll() {
	        return userRepository.findAll();
	    }

	    @Override
	    public DMUser update(DMUser user, long id) {
	        Optional<DMUser> existingUserOptional = userRepository.findById(id);
	        if (existingUserOptional.isPresent()) {
	            DMUser existingUser = existingUserOptional.get();
	            existingUser.setFirstName(user.getFirstName());
	            existingUser.setLastName(user.getLastName());
	            existingUser.setDmFolder(user.getDmFolder());
	            existingUser.setCreateDate(new Date());
	            existingUser.setDmFile(user.getDmFile());
	            existingUser.setEmailAddress(user.getEmailAddress());
	            existingUser.setUserName(user.getUserName());
	            return userRepository.save(existingUser);
	        } else {
	            throw new RuntimeException("User not found");
	        }
	    }

	    @Override
	    public void delete(long id) {
	        userRepository.deleteById(id);
	    }
	}
	