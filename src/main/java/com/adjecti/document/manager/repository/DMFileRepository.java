package com.adjecti.document.manager.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.adjecti.document.manager.model.DMFile;

public interface DMFileRepository extends JpaRepository<DMFile, Long>{

	 @Query(value = "SELECT * FROM dmfile WHERE name=?", nativeQuery = true)
	 Optional<DMFile> findByName(String fileName); 
	
}

	