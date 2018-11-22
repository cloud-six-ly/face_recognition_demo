package com.dbgs.service;

import java.util.Set;

import com.dbgs.entity.SysUser;

public interface UserService {
	SysUser getUser(String userName, String passWord);
	
	Set<String> findPermissionsByUserId(Long userId);
}
