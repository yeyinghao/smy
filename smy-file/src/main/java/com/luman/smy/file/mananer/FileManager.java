package com.luman.smy.file.mananer;

import com.luman.smy.file.model.req.GetFileDownloadUrlReq;
import com.luman.smy.file.model.req.GetUploadFileUrlReq;
import com.luman.smy.file.model.req.UploadFileReq;
import com.luman.smy.file.model.vo.GetUploadFileUrlVO;

import java.io.InputStream;

/**
 * 文件管理器
 *
 * @author yeyinghao
 * @date 2023/09/16
 */
public interface FileManager {

	/**
	 * 上传文件
	 *
	 * @param fileName    文件名称
	 * @param inputStream 输入流
	 * @param req         请求
	 * @return {@link String}
	 */
	String uploadFile(String fileName, InputStream inputStream, UploadFileReq req);

	/**
	 * 获取上传文件url
	 *
	 * @return {@link String}
	 */
	GetUploadFileUrlVO getUploadFileUrl(GetUploadFileUrlReq req);

	/**
	 * 获取目标文件url
	 *
	 * @param fileKey 对象名称
	 * @return {@link String}
	 */
	String getObjectFileUrl(GetFileDownloadUrlReq req);
}
