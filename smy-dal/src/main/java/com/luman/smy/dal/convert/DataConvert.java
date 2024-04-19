package com.luman.smy.dal.convert;

import cn.hutool.core.collection.CollectionUtil;
import com.luman.smy.common.model.BaseDP;
import com.luman.smy.dal.model.BasePO;

import java.util.List;
import java.util.stream.Collectors;

public interface DataConvert<PO extends BasePO, DP extends BaseDP> {

	/**
	 * 转换成Po
	 *
	 * @param dp 域对象
	 * @return {@link PO}
	 */
	PO convertToPO(DP dp);

	/**
	 * 转换来DO
	 *
	 * @param po 持久化对象
	 * @return {@link DP}
	 */
	DP convertToDO(PO po);

	/**
	 * 转换为DOs
	 *
	 * @param pos 持久化对象
	 * @return {@link List}<{@link DP}>
	 */
	default List<DP> convertToDOs(List<PO> pos) {
		if (CollectionUtil.isEmpty(pos)) {
			return CollectionUtil.newArrayList();
		}
		return pos.stream().map(this::convertToDO).collect(Collectors.toList());
	}

	/**
	 * 转换为POs
	 *
	 * @param dos 域对象
	 * @return {@link List}<{@link PO}>
	 */
	default List<PO> convertToPOs(List<DP> dos) {
		if (CollectionUtil.isEmpty(dos)) {
			return CollectionUtil.newArrayList();
		}
		return dos.stream().map(this::convertToPO).collect(Collectors.toList());
	}
}
