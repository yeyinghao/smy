package com.luman.smy.infra.common.response;

import com.luman.smy.infra.common.enums.CommErrorEnum;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@ToString
public class SmyPageResponse<T> extends SmyResponse {

	private IPage<T> data;

	public static <T> SmyPageResponse<T> of(IPage<T> data) {
		SmyPageResponse<T> resultHelper = new SmyPageResponse<>();
		resultHelper.setCode(CommErrorEnum.SUCCESS.getCode());
		resultHelper.setSuccess(Boolean.TRUE);
		resultHelper.setData(data);
		return resultHelper;
	}

}
