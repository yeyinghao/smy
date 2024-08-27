package com.luman.smy.infra.domain.dal.impl;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.luman.smy.client.dto.PageModel;
import com.luman.smy.client.dto.Paging;
import com.luman.smy.domain.dal.CoreService;
import com.luman.smy.domain.dal.model.DP;
import com.luman.smy.infra.domain.dal.model.DO;
import com.luman.smy.infra.integration.dal.convert.DataConvert;

import java.util.List;

/**
 * 网关实现
 *
 * @author yeyinghao
 * @date 2024/04/19
 */
@SuppressWarnings("unused")
public abstract class CoreServiceImpl<D extends DP, P extends DO, M extends BaseMapper<P>> extends ServiceImpl<M, P> implements CoreService<D>, DataConvert<P, D> {

	@Override
	public void save(D entity) {
		P po = convertToPO(entity);
		save(po);
		entity.setId(po.getId());
	}

	@Override
	public void saveOrUpdate(D entity) {
		P po = convertToPO(entity);
		saveOrUpdate(po);
		entity.setId(po.getId());
	}

	@Override
	public void saveBatch(List<D> entities) {
		saveBatch(convertToPOs(entities));
	}

	@Override
	public void deleteById(Long id) {
		removeById(id);
	}

	@Override
	public void deleteByIds(List<Long> ids) {
		removeByIds(ids);
	}

	@Override
	public void updateById(D entity) {
		updateById(convertToPO(entity));
	}

	@Override
	public D findById(Long id) {
		return convertToDP(getById(id));
	}

	@Override
	public List<D> findAll() {
		return convertToDPs(list());
	}

	@Override
	public List<D> findByIds(List<Long> ids) {
		return convertToDPs(listByIds(ids));
	}

	@Override
	public PageModel<D> listByPage(Paging paging) {
		return buildPage(lambdaQuery().page(buildPage(paging)).convert(this::convertToDP));
	}
}
