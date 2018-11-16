package com.dbgs.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;

@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "search_result")
public class SearchResult {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String errorCode;
	private String result;
	private String imageUrl;
	@CreatedDate
	private Date createDate;
}
