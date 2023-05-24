package com.adjecti.document.manager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.adjecti.document.manager.model.DMFolder;

public interface DMFolderRepository extends JpaRepository<DMFolder, Long>{

	@Query(nativeQuery = true, 
	value = "select * from dmfolder where  parent_id  = ?") 
	List<DMFolder> getByParentId(@Param("parentId") long parentId);
}
	 

