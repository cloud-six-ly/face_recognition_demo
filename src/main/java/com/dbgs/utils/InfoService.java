package com.dbgs.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dbgs.repository.InfoRepository;

@Service
@Transactional(rollbackFor=Exception.class)
@CacheConfig(cacheNames = "pictureDirectory")
public class InfoService {

	@Autowired
	private InfoRepository infoRepository;
	
	@Cacheable(value = "pictureDirectory", sync = true, keyGenerator = "sameKeyGenerator")
    public String getValueByKey(String key) {
		return infoRepository.findByKey(key).getValue();
	}
	
	@CachePut(value = "pictureDirectory", keyGenerator = "sameKeyGenerator")
    public String updateValueByKey(String value, String key) {
		infoRepository.updateValueByKey(value, key);
		return "value";
    }

}