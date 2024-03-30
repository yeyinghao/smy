/*
 * Copyright (c) Ye Yinghao
 * 2022.1 - 2024.3
 */

package com.luman.code.dal.model;

import com.luman.code.util.enums.BaseEnum;
import com.luman.code.util.model.INFO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class ErrorInfo extends INFO {

	private String errorCode;

	private String errorMsg;

	public ErrorInfo(BaseEnum baseEnum) {
		this.errorCode = baseEnum.name();
		this.errorMsg = baseEnum.getDescription();
	}
}
