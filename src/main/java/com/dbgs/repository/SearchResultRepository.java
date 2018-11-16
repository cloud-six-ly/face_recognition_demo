package com.dbgs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dbgs.entity.SearchResult;

public interface SearchResultRepository extends JpaRepository<SearchResult, Long> {

}
