package com.luman.smy.client.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Collection;

@EqualsAndHashCode(callSuper = true)
@Data
public class PageModel<T> extends DTO {

	private Long pageSize;

	private Long pageIndex;

	private Long totalSize;

	private Collection<T> pageInfo;
}
