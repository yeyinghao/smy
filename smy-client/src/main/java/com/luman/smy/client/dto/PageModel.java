package com.luman.smy.client.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Collection;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageModel<T> extends DTO {

	private Long pageSize;

	private Long pageIndex;

	private Long totalSize;

	private Collection<T> pageInfo;
}
