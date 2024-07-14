package com.luman.smy.infra.common.response;

import com.luman.smy.infra.common.enums.CommErrorEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@ToString
public class SmyMultiResponse<T> extends SmyResponse {

	private List<T> data;

	public static <T> SmyMultiResponse<T> of(List<T> data) {
		SmyMultiResponse<T> resultHelper = new SmyMultiResponse<>();
		resultHelper.setCode(CommErrorEnum.SUCCESS.getCode());
		resultHelper.setSuccess(Boolean.TRUE);
		resultHelper.setData(data);
		return resultHelper;
	}

}
