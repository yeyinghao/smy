/*
 * Copyright (c) Ye Yinghao
 * 2022.1 - 2023.10
 */

package com.luman.smy.common.feature.file.impl;

import com.luman.smy.common.feature.file.FileClient;
import com.luman.smy.common.feature.file.FileService;
import com.luman.smy.common.feature.file.model.FileStatDTO;
import com.luman.smy.common.util.LoggerUtil;
import io.minio.StatObjectResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

/**
 * 文件服务实现
 *
 * @author yeyinghao
 * @date 2023/09/17
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class FileServiceImpl implements FileService {

	/**
	 * minio客户端
	 */
	private final FileClient fileClient;

	@Override
	@SneakyThrows
	public void uploadFile(MultipartFile file, String objectName) {
		fileClient.uploadFile(objectName, file.getOriginalFilename(), file.getInputStream());
	}

	@Override
	public void uploadFile(String objectName, String fileName, InputStream inputStream) {
		fileClient.uploadFile(objectName, fileName, inputStream);
	}


	@Override
	public String getUploadFileUrl(String objectName) {
		return fileClient.getUploadFileUrl(objectName);
	}

	@Override
	public String getObjectFileUrl(String objectName, Long expiry) {
		return fileClient.getObjectFileUrl(objectName, expiry);
	}

	@Override
	public byte[] downloadObject(String objectName) {
		return fileClient.downloadObject(objectName);
	}

	@Override
	public FileStatDTO statObject(String objectName) {
		StatObjectResponse statObjectResponse = fileClient.statObject(objectName);
		return new FileStatDTO();
	}

	@Override
	public void removeListObject(List<String> objectNameList) {
		fileClient.removeListObject(objectNameList);
	}

	@Override
	public List<String> listObjectNames() {
		return fileClient.listObjectNames();
	}

	@Override
	public Boolean isExist(String objectName) {
		try {
			fileClient.statObject(objectName);
			return true;
		} catch (Throwable e) {
			LoggerUtil.info(log, e);
		}
		return false;
	}
}
