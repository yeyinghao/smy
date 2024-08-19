package com.luman.smy.client.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 页面查询
 *
 * @author yeyinghao
 * @date 2024/08/19
 */
@EqualsAndHashCode(callSuper = true)
@Data
public abstract class PageQuery extends Query {

	private Paging paging;

}
