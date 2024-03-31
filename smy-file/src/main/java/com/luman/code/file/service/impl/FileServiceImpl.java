/*
 * Copyright (c) Ye Yinghao
 * 2022.1 - 2023.10
 */

package com.luman.code.file.service.impl;

import cn.hutool.core.io.IoUtil;
import com.luman.code.file.client.FileClient;
import com.luman.code.file.service.FileService;
import com.luman.code.util.enums.CommErrorEnum;
import com.luman.code.util.exception.Assert;
import io.minio.StatObjectResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * 文件服务实现
 *
 * @author yeyinghao
 * @date 2023/09/17
 */
@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

	/**
	 * 文件代理
	 */
	private final FileClient fileClient;

	@Override
	@SneakyThrows
	public void uploadFile(MultipartFile file, String fileKey) {
		String fileName = file.getOriginalFilename();
		Assert.notBlank(fileName, CommErrorEnum.BIZ_PROCESS_FAIL, "文件名称不能为空");
		InputStream inputStream = new ByteArrayInputStream(file.getBytes());
		fileClient.saveObject(fileKey, fileName, inputStream);
	}

	@Override
	public void uploadFile(String fileKey, String fileName, InputStream inputStream) {
		fileClient.saveObject(fileKey, fileName, inputStream);
	}

	@Override
	public String getUploadFileUrl(String fileKey) {
		return fileClient.getUploadObjectUrl(fileKey);
	}

	@Override
	public String getObjectFileUrl(String fileKey, Long expiry) {
		return fileClient.getObjectUrl(fileKey, expiry);
	}

	@Override
	public byte[] downloadObject(String fileKey) {
		InputStream inputStream = null;
		try {
			inputStream = fileClient.downloadObject(fileKey);
			return IoUtil.readBytes(inputStream);
		} finally {
			IoUtil.close(inputStream);
		}
	}

	@Override
	public StatObjectResponse statObject(String fileKey) {
		return fileClient.statObject(fileKey);
	}

	@Override
	public Boolean isExist(String fileKey) {
		return fileClient.isExist(fileKey);
	}
}
