package com.dbgs.service.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dbgs.repository.SysRoleRepository;
import com.dbgs.service.RoleService;

@Service
@Transactional(rollbackFor=Exception.class)
public class RoleServiceImpl implements RoleService {

	@Autowired
	SysRoleRepository sysRoleRepository;
	
	@Override
	public Set<String> findRoleNameByUserId(Long userId) {
		return sysRoleRepository.findRoleNameByUserId(userId);
	}

}
