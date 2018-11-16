package com.dbgs.entity;

import lombok.Data;

@Data
public class FaceVerifyEntity {
	private String image;
	private String image_type;
	private String face_field;
}
