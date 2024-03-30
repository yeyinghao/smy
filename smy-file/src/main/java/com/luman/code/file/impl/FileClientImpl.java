package com.luman.code.file.impl;

import cn.hutool.core.io.IoUtil;
import cn.hutool.http.Header;
import com.luman.code.file.FileClient;
import com.luman.code.file.enums.FileErrorEnum;
import com.luman.code.util.exception.Assert;
import io.minio.*;
import io.minio.http.Method;
import io.minio.messages.DeleteError;
import io.minio.messages.DeleteObject;
import io.minio.messages.Item;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.net.URLEncoder;
import java.util.*;

/**
 * 文件代理impl
 *
 * @author yeyinghao
 * @date 2023/09/16
 */
@Service
@Slf4j
public class FileClientImpl implements FileClient {

	/**
	 * minio客户
	 */
	private final MinioClient minioClient;

	/**
	 * bucket名称
	 */
	@Value("${minio.bucket-name}")
	private String bucketName;

	/**
	 * 文件大小
	 */
	@Value("${minio.file-size}")
	private Long fileSize;

	/**
	 * 文件代理impl
	 *
	 * @param minioClient minio客户
	 */
	public FileClientImpl(MinioClient minioClient) {
		this.minioClient = minioClient;
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
	@SneakyThrows
	public void saveObject(String objectName, String fileName, InputStream inputStream) {
		try {
			Assert.notBlank(fileName, FileErrorEnum.FILE_NAME_NOT_BLANK);
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
	public InputStream downloadObject(String objectName) {
		return minioClient.getObject(GetObjectArgs.builder().bucket(bucketName).object(objectName).build());
	}

	@Override
	@SneakyThrows
	public void removeObject(String objectName) {
		minioClient.removeObject(RemoveObjectArgs.builder().bucket(bucketName).object(objectName).build());
	}

	@Override
	@SneakyThrows
	public void removeListObject(List<String> objectNameList) {
		List<DeleteObject> objects = new LinkedList<>();
		for (String objectName : objectNameList) {
			objects.add(new DeleteObject(objectName));
		}
		Iterable<Result<DeleteError>> results = minioClient.removeObjects(RemoveObjectsArgs.builder().bucket(bucketName).objects(objects).build());
		Assert.notEmpty(results, FileErrorEnum.FILE_BATCH_DELETE_FAIL);
	}

	@Override
	@SneakyThrows
	public String getObjectUrl(String objectName, Long expiry) {
		return minioClient.getPresignedObjectUrl(GetPresignedObjectUrlArgs.builder().method(Method.GET).bucket(bucketName).object(objectName).expiry(expiry.intValue()).build());
	}

	@Override
	@SneakyThrows
	public String getUploadObjectUrl(String objectName) {
		return minioClient.getPresignedObjectUrl(GetPresignedObjectUrlArgs.builder().method(Method.PUT).bucket(bucketName).object(objectName).expiry(60 * 60).build());
	}

	@SneakyThrows
	@Override
	public StatObjectResponse statObject(String objectName) {
		return minioClient.statObject(StatObjectArgs.builder().bucket(bucketName).object(objectName).build());
	}

	@Override
	public Boolean isExist(String objectName) {
		try {
			minioClient.statObject(StatObjectArgs.builder().bucket(bucketName).object(objectName).build());
		} catch (Throwable e) {
			return false;
		}
		return false;
	}
}
