/*
 * Copyright (c) Ye Yinghao
 * 2022.1 - 2023.10
 */

package com.luman.code.file.service;

import io.minio.StatObjectResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

/**
 * 文件服务
 *
 * @author yeyinghao
 * @date 2024/03/31
 */
public interface FileService {
	/**
	 * 上传文件
	 *
	 * @param file       文件
	 * @param fileKey 对象名称
	 */
	void uploadFile(MultipartFile file, String fileKey);

	/**
	 * 上传文件
	 *
	 * @param fileKey     文件key
	 * @param fileName    文件名称
	 * @param inputStream 输入流
	 */
	void uploadFile(String fileKey, String fileName, InputStream inputStream);

	/**
	 * 获取上传文件url
	 *
	 * @param fileKey 对象名称
	 * @return {@link String}
	 */
	String getUploadFileUrl(String fileKey);

	/**
	 * 得到对象文件url
	 *
	 * @param fileKey 对象名称
	 * @param expiry     到期
	 * @return {@link String}
	 */
	String getObjectFileUrl(String fileKey, Long expiry);

	/**
	 * 下载对象
	 *
	 * @param fileKey 对象名称
	 * @return {@link InputStream}
	 */
	byte[] downloadObject(String fileKey);

	/**
	 * 统计对象
	 *
	 * @param fileKey 对象名称
	 * @return {@link StatObjectResponse}
	 */
	StatObjectResponse statObject(String fileKey);

	/**
	 * 对象是否存在
	 *
	 * @param fileKey 对象名称
	 * @return {@link Boolean}
	 */
	Boolean isExist(String fileKey);
}
