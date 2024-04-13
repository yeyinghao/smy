package com.luman.smy.file.model.req;

import com.luman.smy.common.enums.FileTypeEnum;
import lombok.Data;

@Data
public class GetUploadFileUrlReq {

	private String fileName;

	private FileTypeEnum fileType;

}
