package com.dbgs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dbgs.entity.Info;

public interface InfoRepository extends JpaRepository<Info, String> {
	
	Info findByKey(String key);
	
	@Modifying()
	@Query("update info set value = :value where key = :key")
	void updateValueByKey(@Param("value") String value, @Param("key") String key);
}
