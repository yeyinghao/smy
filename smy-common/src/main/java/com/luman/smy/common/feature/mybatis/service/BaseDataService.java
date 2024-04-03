package com.luman.smy.common.feature.mybatis.service;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.luman.smy.common.converter.PageConverter;
import com.luman.smy.common.model.BaseDP;
import com.luman.smy.common.model.BasePO;
import com.luman.smy.common.model.PageReq;
import com.luman.smy.common.model.PageRes;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 基础数据服务
 *
 * @author yeyinghao
 * @date 2024/04/02
 */
public abstract class BaseDataService<P extends BasePO, D extends BaseDP> extends ServiceImpl<BaseMapper<P>, P> {

	/**
	 * 转换成Po
	 *
	 * @param domainObject 域对象
	 * @return {@link P}
	 */
	protected abstract P convertToPO(D domainObject);

	/**
	 * 转换来DO
	 *
	 * @param persistenceObject 持久化对象
	 * @return {@link D}
	 */
	protected abstract D convertToDO(P persistenceObject);

	/**
	 * 转换为DOs
	 *
	 * @param persistenceObjects 持久化对象
	 * @return {@link List}<{@link D}>
	 */
	protected List<D> convertToDOs(List<P> persistenceObjects) {
		if (CollectionUtil.isEmpty(persistenceObjects)) {
			return CollectionUtil.newArrayList();
		}
		return persistenceObjects.stream().map(this::convertToDO).collect(Collectors.toList());
	}

	/**
	 * 转换为POs
	 *
	 * @param domainObjects 域对象
	 * @return {@link List}<{@link P}>
	 */
	protected List<P> convertToPOs(List<D> domainObjects) {
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
	public void save(D entity) {
		P po = convertToPO(entity);
		save(po);
		entity.setId(po.getId());
	}

	/**
	 * 批量保存
	 *
	 * @param entities 数据库领域对象
	 */
	public void saveBatch(List<D> entities) {
		saveBatch(convertToPOs(entities));
	}

	/**
	 * 根据id删除
	 *
	 * @param id 主键id
	 */
	public void deleteById(Long id) {
		lambdaUpdate().eq(P::getId, id).update();
	}

	/**
	 * 根据id列表删除
	 *
	 * @param ids 主键id列表
	 */
	public void deleteByIds(List<Long> ids) {
		lambdaUpdate().in(P::getId, ids).remove();
	}

	/**
	 * 根据id修改
	 *
	 * @param entity 数据库领域对象
	 */
	public void updateById(D entity) {
		lambdaUpdate().eq(P::getId, entity.getId()).update(convertToPO(entity));
	}

	/**
	 * 根据id查询
	 *
	 * @param id 主键id
	 */
	public D findById(Long id) {
		P entity = lambdaQuery().eq(P::getId, id).one();
		return convertToDO(entity);
	}

	/**
	 * 查询所有
	 */
	public List<D> findAll() {
		List<P> list = lambdaQuery().list();
		return convertToDOs(list);
	}

	/**
	 * 根据id列表查询
	 *
	 * @param ids 主键id列表
	 * @return {@link List}<{@link D}>
	 */
	public List<D> findByIds(List<Long> ids) {
		List<P> list = lambdaQuery().in(P::getId, ids).list();
		return convertToDOs(list);
	}

	/**
	 * 分页查询
	 *
	 * @param req 请求
	 * @return {@link PageRes}<{@link D}>
	 */
	public PageRes<D> listByPage(PageReq req) {
		IPage<P> page = new Page<>(req.getPageIndex(), req.getPageSize());
		lambdaQuery().page(page);
		return PageConverter.buildPage(page, convertToDOs(page.getRecords()));
	}
}
