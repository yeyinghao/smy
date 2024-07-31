package com.luman.smy.client.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Page Query Param
 *
 * @author jacky
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class PageQuery extends Query {

	@Valid
	@NotNull(message = "分页对象不能为空")
	private Paging paging;

}
