package com.luman.smy.infra.integration.dal.convert;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.luman.smy.client.dto.PageModel;
import com.luman.smy.client.dto.Paging;
import com.luman.smy.domain.dal.model.DP;
import com.luman.smy.infra.domain.dal.model.DO;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public interface DataConvert<P extends DO, D extends DP> {

	/**
	 * 构建page
	 *
	 * @param page 分页
	 */
	default PageModel<D> buildPage(IPage<D> page) {
		return new PageModel<>(page.getSize(), page.getCurrent(), page.getTotal(), page.getRecords());
	}

	/**
	 * 构建页面
	 *
	 * @param paging 分页
	 * @return {@link IPage }<{@link P }>
	 */
	default IPage<P> buildPage(Paging paging) {
		if (Objects.isNull(paging) || Objects.isNull(paging.getPageIndex()) || Objects.isNull(paging.getPageSize())) {
			return new Page<>(DEFAULT_PAGE_INDEX, DEFAULT_PAGE_SIZE);
		}
		return new Page<>(paging.getPageIndex(), paging.getPageSize());
	}

	/**
	 * 转换成Po
	 *
	 * @param d 域对象
	 * @return {@link P}
	 */
	P convertToPO(D d);

	/**
	 * 转换来DO
	 *
	 * @param p 持久化对象
	 * @return {@link D}
	 */
	D convertToDP(P p);

	/**
	 * 转换为DOs
	 *
	 * @param ps 持久化对象
	 * @return {@link List}<{@link D}>
	 */
	default List<D> convertToDPs(List<P> ps) {
		if (CollectionUtil.isEmpty(ps)) {
			return CollectionUtil.newArrayList();
		}
		return ps.stream().map(this::convertToDP).collect(Collectors.toList());
	}

	/**
	 * 转换为POs
	 *
	 * @param ds 域对象
	 * @return {@link List}<{@link P}>
	 */
	default List<P> convertToPOs(List<D> ds) {
		if (CollectionUtil.isEmpty(ds)) {
			return CollectionUtil.newArrayList();
		}
		return ds.stream().map(this::convertToPO).collect(Collectors.toList());
	}

	/**
	 * 默认页面索引
	 */
	Integer DEFAULT_PAGE_INDEX = 1;

	/**
	 * 默认页面大小
	 */
	Integer DEFAULT_PAGE_SIZE = 20;
}
