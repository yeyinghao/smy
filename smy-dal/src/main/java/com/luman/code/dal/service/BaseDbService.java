package com.luman.code.dal.service;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.stream.Collectors;

public interface BaseDbService<BasePO, BaseDP> extends IService<BasePO> {

	/**
	 * 转换成Po
	 *
	 * @param domainObject 域对象
	 * @return {@link BasePO}
	 */
	BasePO convertToPO(BaseDP domainObject);

	/**
	 * 转换来DO
	 *
	 * @param persistenceObject 持久化对象
	 * @return {@link BaseDP}
	 */
	BaseDP convertToDO(BasePO persistenceObject);

	/**
	 * 转换为DOS
	 *
	 * @param persistenceObjects 持久化对象
	 * @return {@link List}<{@link BaseDP}>
	 */
	default List<BaseDP> convertToDOs(List<BasePO> persistenceObjects) {
		if (CollectionUtil.isEmpty(persistenceObjects)) {
			return CollectionUtil.newArrayList();
		}
		return persistenceObjects.stream().map(this::convertToDO).collect(Collectors.toList());
	}

	/**
	 * 转换为pos
	 *
	 * @param domainObjects 域对象
	 * @return {@link List}<{@link BasePO}>
	 */
	default List<BasePO> convertToPOs(List<BaseDP> domainObjects) {
		if (CollectionUtil.isEmpty(domainObjects)) {
			return CollectionUtil.newArrayList();
		}
		return domainObjects.stream().map(this::convertToPO).collect(Collectors.toList());
	}

}
