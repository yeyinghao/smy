package com.luman.smy.web.manager.impl;

import com.luman.smy.web.model.file.req.GetFileDownloadUrlReq;
import com.luman.smy.web.model.file.req.GetUploadFileUrlReq;
import com.luman.smy.web.model.file.req.UploadFileReq;
import com.luman.smy.web.model.file.vo.GetUploadFileUrlVO;
import com.luman.smy.file.FileService;
import com.luman.smy.file.config.FileConfig;
import com.luman.smy.file.util.FileUtil;
import com.luman.smy.web.manager.FileManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.InputStream;

/**
 * 文件manager实现
 *
 * @author yeyinghao
 * @date 2023/10/22
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class FileManagerImpl implements FileManager {

	/**
	 * 文件服务
	 */
	private final FileService fileService;

	/**
	 * minio配置
	 */
	private final FileConfig fileConfig;

	@Override
	public String uploadFile(String fileName, InputStream inputStream, UploadFileReq req) {
		String fileKey = FileUtil.createObjectName(req.getFileType(), fileName);
		fileService.uploadFile(fileName, fileKey, inputStream);
		return fileKey;
	}

	@Override
	public GetUploadFileUrlVO getUploadFileUrl(GetUploadFileUrlReq req) {
		String fileKey = FileUtil.createObjectName(req.getFileType(), req.getFileName());
		String uploadFileUrl = fileService.getUploadFileUrl(fileKey);
		return GetUploadFileUrlVO.buildGetUploadFileUrlRes(fileKey, uploadFileUrl);
	}

	@Override
	public String getObjectFileUrl(GetFileDownloadUrlReq req) {
		String objectFileUrl = fileService.getObjectFileUrl(req.getFileKey(), fileConfig.expirySecond());
		// 本地直接返回就好了
		if (fileConfig.isHttp()) {
			return objectFileUrl;
		}
		return objectFileUrl.replace("http", "https");
	}
}
