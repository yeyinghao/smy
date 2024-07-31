package com.luman.smy.client.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;

@Data
public class Paging implements Serializable {

	@Max(value = 50, message = "分页大小不能超过50")
	@Min(value = 1, message = "分页大小不能少于1")
	@NotNull
	private Long pageSize;

	@Min(value = 1, message = "当前页不能小于1")
	@NotNull
	private Long pageIndex;

}
