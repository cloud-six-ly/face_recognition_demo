package com.dbgs.utils;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dbgs.repository.CallerRepository;

@Service
@Transactional(rollbackFor=Exception.class)
@CacheConfig(cacheNames = "caller")
public class SwitchService {

	@Autowired
	private CallerRepository callerRepository;
	
	@Cacheable(value = "caller", sync = true, keyGenerator = "sameKeyGenerator")
    public String getCaller() {
		return callerRepository.findAll().get(0).getName();
	}
	
	@CachePut(value = "caller", keyGenerator = "sameKeyGenerator")
    public String updateCaller(String name) {
		callerRepository.update(name, new Date(), 1L);
		return name;
    }

}