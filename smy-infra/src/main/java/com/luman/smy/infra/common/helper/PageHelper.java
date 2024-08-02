package com.luman.smy.infra.common.helper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.luman.smy.client.dto.PageModel;
import com.luman.smy.client.dto.Paging;
import com.luman.smy.infra.integration.dal.model.BaseDO;
import lombok.experimental.UtilityClass;

import java.util.Objects;

@UtilityClass
public class PageHelper {

	private static final Integer DEFAULT_PAGE_INDEX = 1;

	private static final Integer DEFAULT_PAGE_SIZE = 20;

	public static <R> PageModel<R> buildPage(IPage<R> page) {
		PageModel<R> pageModel = new PageModel<>(page.getSize(), page.getCurrent(), page.getTotal());
		pageModel.setListInfo(page.getRecords());
		return pageModel;
	}

	public static <P extends BaseDO> IPage<P> buildPage(Paging paging) {
		if (Objects.isNull(paging) || Objects.isNull(paging.getPageIndex()) || Objects.isNull(paging.getPageSize())) {
			return new Page<>(DEFAULT_PAGE_INDEX, DEFAULT_PAGE_SIZE);
		}
		return new Page<>(paging.getPageIndex(), paging.getPageSize());
	}
}
