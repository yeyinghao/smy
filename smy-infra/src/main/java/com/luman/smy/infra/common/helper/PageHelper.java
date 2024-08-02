package com.luman.smy.infra.common.helper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.luman.smy.client.dto.PageModel;
import com.luman.smy.infra.integration.dal.model.BaseDO;
import lombok.experimental.UtilityClass;

@UtilityClass
public class PageHelper {
	public static <P extends BaseDO> IPage<P> buildPage(Long pageSize, Long pageIndex) {
		return new Page<>(pageIndex, pageSize);
	}

	public static <R> PageModel<R> buildPage(IPage<R> page) {
		PageModel<R> pageModel = new PageModel<>(page.getSize(), page.getCurrent(), page.getTotal());
		pageModel.setListInfo(page.getRecords());
		return pageModel;
	}
}
