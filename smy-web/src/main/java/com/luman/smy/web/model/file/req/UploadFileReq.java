package com.luman.smy.web.model.file.req;

import com.luman.smy.common.enums.FileTypeEnum;
import lombok.Data;

@Data
public class UploadFileReq {

	private FileTypeEnum fileType;

}
