package com.dbgs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dbgs.entity.SysUser;

public interface SysUserRepository extends JpaRepository<SysUser, Long> {
	SysUser findByUserNameAndPassWord(String userName, String passWord);
	
	@Query(nativeQuery = true, value = "select res_url from sys_resources where id in (select resources_id from sys_role_resources where role_id in (select role_id from sys_user_role where user_id = :userId))")
	List<Object> findPermissionsByUserId(@Param("userId") Long userId);
}
