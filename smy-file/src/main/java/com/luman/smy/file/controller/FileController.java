/*
 * Copyright (c) Ye Yinghao
 * 2022.1 - 2024.1
 */

package com.luman.smy.file.controller;

import com.luman.smy.common.helper.ResultHelper;
import com.luman.smy.file.mananer.FileManager;
import com.luman.smy.file.model.req.GetFileDownloadUrlReq;
import com.luman.smy.file.model.req.GetUploadFileUrlReq;
import com.luman.smy.file.model.req.UploadFileReq;
import com.luman.smy.file.model.vo.GetUploadFileUrlVO;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * minio控制器
 *
 * @author yeyinghao
 * @date 2023/08/07
 */
@RequestMapping("/api/file")
@RestController
@RequiredArgsConstructor
public class FileController {

	/**
	 * 文件管理器
	 */
	private final FileManager fileManager;

	/**
	 * 上传文件
	 *
	 * @param file 文件
	 * @return {@link ResultHelper}<{@link Boolean}>
	 */
	@SneakyThrows
	@PostMapping("/upload")
	public ResultHelper<String> uploadFile(MultipartFile file, @RequestBody UploadFileReq req) {
		return ResultHelper.of(fileManager.uploadFile(file.getOriginalFilename(), file.getInputStream(), req));
	}

	/**
	 * 获取上传文件url
	 *
	 * @return {@link ResultHelper}<{@link String}>
	 */
	@GetMapping("/getUploadFileUrl")
	public ResultHelper<GetUploadFileUrlVO> getUploadFileUrl(@RequestBody GetUploadFileUrlReq req) {
		return ResultHelper.of(fileManager.getUploadFileUrl(req));
	}

	/**
	 * 得到对象文件url
	 *
	 * @param req 对象名称
	 * @return {@link ResultHelper}<{@link String}>
	 */
	@PostMapping("/getObjectFileUrl")
	public ResultHelper<String> getObjectFileUrl(@RequestBody GetFileDownloadUrlReq req) {
		return ResultHelper.of(fileManager.getObjectFileUrl(req));
	}
}
