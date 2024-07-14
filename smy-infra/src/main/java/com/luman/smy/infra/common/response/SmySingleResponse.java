package com.luman.smy.infra.common.response;

import com.luman.smy.infra.common.enums.CommErrorEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@ToString
public class SmySingleResponse<T> extends SmyResponse {

	private T data;

	public static <T> SmySingleResponse<T> of(T data) {
		SmySingleResponse<T> resultHelper = new SmySingleResponse<>();
		resultHelper.setCode(CommErrorEnum.SUCCESS.getCode());
		resultHelper.setSuccess(Boolean.TRUE);
		resultHelper.setData(data);
		return resultHelper;
	}

}
