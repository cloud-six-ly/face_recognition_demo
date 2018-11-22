package com.dbgs.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dbgs.entity.SysRole;

public interface SysRoleRepository extends JpaRepository<SysRole, Long> {
	
	@Query(nativeQuery = true, value = "select role_name from sys_role where id in (select role_id from sys_user_role where user_id = :userId)")
	Set<String> findRoleNameByUserId(@Param("userId") Long userId);
}
