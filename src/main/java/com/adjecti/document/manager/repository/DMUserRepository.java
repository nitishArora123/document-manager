package com.adjecti.document.manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.adjecti.document.manager.model.DMUser;

public interface DMUserRepository  extends JpaRepository<DMUser, Long>{

}
