/*
 * Copyright (c) Ye Yinghao
 * 2022.1 - 2023.10
 */

package com.luman.code.common.feature.file.service.impl;

import cn.hutool.core.io.IoUtil;
import cn.hutool.http.Header;
import com.luman.code.common.enums.CommErrorEnum;
import com.luman.code.common.exception.Assert;
import com.luman.code.common.feature.file.service.FileService;
import io.minio.*;
import io.minio.errors.*;
import io.minio.http.Method;
import io.minio.messages.DeleteError;
import io.minio.messages.DeleteObject;
import io.minio.messages.Item;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.*;

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
	private final MinioClient minioClient;

	/**
	 * 其他文件的最大大小
	 */
	@Value("${minio.fileSize}")
	private Long fileSize;

	/**
	 * bucket名称
	 */
	@Value("${minio.bucket-name}")
	private String bucketName;

	@Override
	@SneakyThrows
	public void uploadFile(MultipartFile file, String objectName) {
		putObject(objectName, file.getOriginalFilename(), new ByteArrayInputStream(file.getBytes()));
	}

	@Override
	@SneakyThrows
	public void uploadFile(String objectName, String fileName, InputStream inputStream) {
		putObject(objectName, fileName, inputStream);
	}

	private void putObject(String objectName, String fileName, InputStream inputStream) throws ErrorResponseException, InsufficientDataException, InternalException, InvalidKeyException, InvalidResponseException, IOException, NoSuchAlgorithmException, ServerException, XmlParserException {
		try {
			Assert.notBlank(fileName, CommErrorEnum.BIZ_PROCESS_FAIL, "文件名称不能为空");
			// 下载文件时自动添加文件名
			Map<String, String> headers = new HashMap<>();
			headers.put(Header.CONTENT_DISPOSITION.getValue(), "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
			PutObjectArgs putObjectArgs = PutObjectArgs.builder().bucket(bucketName).object(objectName).stream(inputStream, -1, fileSize).headers(headers).build();
			minioClient.putObject(putObjectArgs);
		} finally {
			IoUtil.close(inputStream);
		}
	}

	@Override
	@SneakyThrows
	public String getUploadFileUrl(String objectName) {
		return getObjectFileUrl(objectName, 3600L);
	}

	@Override
	@SneakyThrows
	public String getObjectFileUrl(String objectName, Long expiry) {
		return minioClient.getPresignedObjectUrl(GetPresignedObjectUrlArgs.builder().method(Method.GET).bucket(bucketName).object(objectName).expiry(expiry.intValue()).build());
	}

	@Override
	@SneakyThrows
	public byte[] downloadObject(String objectName) {
		InputStream inputStream = null;
		try {
			inputStream = minioClient.getObject(GetObjectArgs.builder().bucket(bucketName).object(objectName).build());
			return IoUtil.readBytes(inputStream);
		} finally {
			IoUtil.close(inputStream);
		}
	}

	@Override
	@SneakyThrows
	public StatObjectResponse statObject(String objectName) {
		return minioClient.statObject(StatObjectArgs.builder().bucket(bucketName).object(objectName).build());
	}

	@Override
	public void removeListObject(List<String> objectNameList) {
		List<DeleteObject> objects = new LinkedList<>();
		for (String objectName : objectNameList) {
			objects.add(new DeleteObject(objectName));
		}
		Iterable<Result<DeleteError>> results = minioClient.removeObjects(RemoveObjectsArgs.builder().bucket(bucketName).objects(objects).build());
		Assert.notEmpty(results, CommErrorEnum.BIZ_PROCESS_FAIL, "批量删除失败");
	}

	@Override
	@SneakyThrows
	public List<String> listObjectNames() {
		List<String> listObjectNames = new ArrayList<>();
		Iterable<Result<Item>> myObjects = minioClient.listObjects(ListObjectsArgs.builder().bucket(bucketName).build());
		for (Result<Item> result : myObjects) {
			Item item = result.get();
			listObjectNames.add(item.objectName());
		}
		return listObjectNames;
	}

	@Override
	public Boolean isExist(String objectName) {
		try {
			statObject(objectName);
		} catch (Throwable e) {
			return false;
		}
		return false;
	}
}
