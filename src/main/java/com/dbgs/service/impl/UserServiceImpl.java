package com.dbgs.service.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dbgs.entity.SysUser;
import com.dbgs.repository.SysUserRepository;
import com.dbgs.service.UserService;

@Service
@Transactional(rollbackFor=Exception.class)
public class UserServiceImpl implements UserService {

	@Autowired
	private SysUserRepository sysUserRepository;
	
	@Override
	public SysUser getUser(String userName, String passWord) {
		return sysUserRepository.findByUserNameAndPassWord(userName, passWord);
	}

	@Override
	public Set<String> findPermissionsByUserId(Long userId) {
		// TODO Auto-generated method stub
		return null;
	}

}
