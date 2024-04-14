/*
 * Copyright (c) Ye Yinghao
 * 2022.1 - 2024.3
 */

package com.luman.smy.web.model.file.vo;

import lombok.Builder;
import lombok.Data;

/**
 * 获取上传文件urlres
 *
 * @author yeyinghao
 * @date 2024/03/12
 */
@Data
@Builder
public class GetUploadFileUrlVO {

	/**
	 * 文件key
	 */
	private String fileKey;

	/**
	 * 上传url
	 */
	private String uploadUrl;

	/**
	 * 构建获取上传文件urlres
	 *
	 * @param fileKey       文件key
	 * @param uploadFileUrl 上传文件网址
	 * @return {@link GetUploadFileUrlVO}
	 */
	public static GetUploadFileUrlVO buildGetUploadFileUrlRes(String fileKey, String uploadFileUrl) {
		return GetUploadFileUrlVO.builder().fileKey(fileKey).uploadUrl(uploadFileUrl).build();
	}
}
