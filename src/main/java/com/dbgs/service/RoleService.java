package com.dbgs.service;

import java.util.Set;

public interface RoleService {
	Set<String> findRoleNameByUserId(Long userId);
}
