package com.luman.code.dal.service;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.luman.code.dal.converter.PageConvertor;
import com.luman.code.dal.model.BasePO;
import com.luman.code.util.model.BaseDP;
import com.luman.code.util.model.PageReq;
import com.luman.code.util.model.PageRes;

import java.util.List;
import java.util.stream.Collectors;

public abstract class BaseDataService<PO extends BasePO, DP extends BaseDP> extends ServiceImpl<BaseMapper<PO>, PO> {

	/**
	 * 转换成Po
	 *
	 * @param domainObject 域对象
	 * @return {@link PO}
	 */
	protected abstract PO convertToPO(DP domainObject);

	/**
	 * 转换来DO
	 *
	 * @param persistenceObject 持久化对象
	 * @return {@link DP}
	 */
	protected abstract DP convertToDO(PO persistenceObject);

	/**
	 * 转换为DOS
	 *
	 * @param persistenceObjects 持久化对象
	 * @return {@link List}<{@link com.luman.code.util.model.base.DP}>
	 */
	protected List<DP> convertToDOs(List<PO> persistenceObjects) {
		if (CollectionUtil.isEmpty(persistenceObjects)) {
			return CollectionUtil.newArrayList();
		}
		return persistenceObjects.stream().map(this::convertToDO).collect(Collectors.toList());
	}

	/**
	 * 转换为pos
	 *
	 * @param domainObjects 域对象
	 * @return {@link List}<{@link com.luman.code.util.model.base.PO}>
	 */
	protected List<PO> convertToPOs(List<DP> domainObjects) {
		if (CollectionUtil.isEmpty(domainObjects)) {
			return CollectionUtil.newArrayList();
		}
		return domainObjects.stream().map(this::convertToPO).collect(Collectors.toList());
	}

	/**
	 * 保存
	 *
	 * @param entity 数据库领域对象
	 */
	public void save(DP entity) {
		PO po = convertToPO(entity);
		save(po);
		entity.setId(po.getId());
	}

	/**
	 * 批量保存
	 *
	 * @param entities 数据库领域对象
	 */
	public void saveBatch(List<DP> entities) {
		saveBatch(convertToPOs(entities));
	}

	/**
	 * 根据id删除
	 *
	 * @param id 主键id
	 */
	public void deleteById(Long id) {
		lambdaUpdate().eq(PO::getId, id).update();
	}

	/**
	 * 根据id列表删除
	 *
	 * @param ids 主键id列表
	 */
	public void deleteByIds(List<Long> ids) {
		lambdaUpdate().in(PO::getId, ids).remove();
	}

	/**
	 * 根据id修改
	 *
	 * @param entity 数据库领域对象
	 */
	public void updateById(DP entity) {
		lambdaUpdate().eq(PO::getId, entity.getId()).update(convertToPO(entity));
	}

	/**
	 * 根据id查询
	 *
	 * @param id 主键id
	 */
	public DP findById(Long id) {
		PO entity = lambdaQuery().eq(PO::getId, id).one();
		return convertToDO(entity);
	}

	/**
	 * 查询所有
	 */
	public List<DP> findAll() {
		List<PO> list = lambdaQuery().list();
		return convertToDOs(list);
	}

	/**
	 * 根据id列表查询
	 *
	 * @param ids 主键id列表
	 * @return {@link List}<{@link com.luman.code.util.model.base.DP}>
	 */
	public List<DP> findByIds(List<Long> ids) {
		List<PO> list = lambdaQuery().in(PO::getId, ids).list();
		return convertToDOs(list);
	}

	/**
	 * 分页查询
	 *
	 * @param req 请求
	 * @return {@link PageRes}<{@link com.luman.code.util.model.base.DP}>
	 */
	public PageRes<DP> listByPage(PageReq req) {
		IPage<PO> page = new Page<>(req.getPageIndex(), req.getPageSize());
		lambdaQuery().page(page);
		return PageConvertor.buildPage(page, convertToDOs(page.getRecords()));
	}
}
