package com.luman.smy.client.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Page Query Param
 *
 * @author jacky
 */
@EqualsAndHashCode(callSuper = true)
@Data
public abstract class PageQuery extends Query {

	private Paging paging;

}
