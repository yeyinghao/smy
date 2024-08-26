package com.luman.smy.infra.common.helper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.luman.smy.client.dto.PageModel;
import com.luman.smy.client.dto.Paging;
import com.luman.smy.infra.integration.dal.model.BaseDO;
import lombok.experimental.UtilityClass;

import java.util.Objects;

/**
 * 分页
 *
 * @author yeyinghao
 * @date 2024/08/19
 */
@UtilityClass
public class PageHelper {

	/**
	 * 默认页面索引
	 */
	private static final Integer DEFAULT_PAGE_INDEX = 1;

	/**
	 * 默认页面大小
	 */
	private static final Integer DEFAULT_PAGE_SIZE = 20;

	/**
	 * 构建page
	 *
	 * @param page 分页
	 * @return {@link PageModel }<{@link R }>
	 */
	public static <R> PageModel<R> buildPage(IPage<R> page) {
		return new PageModel<>(page.getSize(), page.getCurrent(), page.getTotal(), page.getRecords());
	}

	/**
	 * 构建页面
	 *
	 * @param paging 分页
	 * @return {@link IPage }<{@link P }>
	 */
	public static <P extends BaseDO> IPage<P> buildPage(Paging paging) {
		if (Objects.isNull(paging) || Objects.isNull(paging.getPageIndex()) || Objects.isNull(paging.getPageSize())) {
			return new Page<>(DEFAULT_PAGE_INDEX, DEFAULT_PAGE_SIZE);
		}
		return new Page<>(paging.getPageIndex(), paging.getPageSize());
	}
}
