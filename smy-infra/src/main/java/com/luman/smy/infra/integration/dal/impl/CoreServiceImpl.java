package com.luman.smy.integration.dal.impl;

import com.luman.smy.integration.dal.CoreService;
import com.luman.smy.integration.dal.convert.DataConvert;
import com.luman.smy.integration.dal.model.BaseDP;
import com.luman.smy.integration.dal.model.BaseDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

/**
 * 网关实现
 *
 * @author yeyinghao
 * @date 2024/04/19
 */
@SuppressWarnings("unused")
public abstract class CoreServiceImpl<P extends BaseDO, D extends BaseDP, M extends BaseMapper<P>> extends ServiceImpl<M, P> implements CoreService<D, P>, DataConvert<P, D> {

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
	public IPage<D> listByPage(IPage<P> page) {
		return page(page).convert(this::convertToDP);
	}
}
