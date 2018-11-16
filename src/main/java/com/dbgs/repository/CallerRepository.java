package com.dbgs.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dbgs.entity.Caller;

public interface CallerRepository extends JpaRepository<Caller, Long> {
	
	@Modifying()
	@Query("update caller set name = :name,update_date = :date where id = :id")
	public void update(@Param("name") String name, @Param("date") Date date, @Param("id") Long id);
}
