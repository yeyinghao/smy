package com.luman.code.file;

import io.minio.StatObjectResponse;

import java.io.InputStream;
import java.util.List;

/**
 * 文件代理
 *
 * @author yeyinghao
 * @date 2023/09/16
 */
public interface FileClient {

	/**
	 * 列出对象名称
	 *
	 * @return {@link List}<{@link String}>
	 */
	List<String> listObjectNames();

	/**
	 * 保存对象
	 *
	 * @param objectName  对象名称
	 * @param fileName    文件名称
	 * @param inputStream 输入流
	 */
	void saveObject(String objectName, String fileName, InputStream inputStream);

	/**
	 * 下载对象
	 *
	 * @param objectName 对象名称
	 * @return {@link InputStream}
	 */
	InputStream downloadObject(String objectName);

	/**
	 * 删除对象
	 *
	 * @param objectName 对象名称
	 */
	void removeObject(String objectName);

	/**
	 * 删除列表对象
	 *
	 * @param objectNameList 对象名称列表
	 */
	void removeListObject(List<String> objectNameList);

	/**
	 * 获取对象url
	 *
	 * @param objectName 对象名称
	 * @param expiry     到期
	 * @return {@link String}
	 * @description: 获取文件路径
	 */
	String getObjectUrl(String objectName, Long expiry);

	/**
	 * 获取上传对象url
	 *
	 * @param objectName 对象名称
	 * @return {@link String}
	 * @description: 获取临时文件上传路径
	 */
	String getUploadObjectUrl(String objectName);

	/**
	 * 统计对象
	 *
	 * @param objectName 对象名称
	 * @return {@link StatObjectResponse}
	 */
	StatObjectResponse statObject(String objectName);

	/**
	 * 对象是否存在
	 *
	 * @param objectName 对象名称
	 * @return {@link Boolean}
	 */
	Boolean isExist(String objectName);
}
