package com.luman.smy.dal.service;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.luman.smy.common.model.BaseDP;
import com.luman.smy.common.model.PageReq;
import com.luman.smy.common.model.PageRes;
import com.luman.smy.dal.model.BasePO;
import com.luman.smy.dal.util.PageUtil;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 基础数据服务
 *
 * @author yeyinghao
 * @date 2024/04/02
 */
public interface DataService<P extends BasePO, D extends BaseDP> extends IService<P> {

	/**
	 * 转换成Po
	 *
	 * @param dp 域对象
	 * @return {@link P}
	 */
	P convertToPO(D dp);

	/**
	 * 转换来DO
	 *
	 * @param po 持久化对象
	 * @return {@link D}
	 */
	D convertToDO(P po);

	/**
	 * 转换为DOs
	 *
	 * @param pos 持久化对象
	 * @return {@link List}<{@link D}>
	 */
	default List<D> convertToDOs(List<P> pos) {
		if (CollectionUtil.isEmpty(pos)) {
			return CollectionUtil.newArrayList();
		}
		return pos.stream().map(this::convertToDO).collect(Collectors.toList());
	}

	/**
	 * 转换为POs
	 *
	 * @param dos 域对象
	 * @return {@link List}<{@link P}>
	 */
	default List<P> convertToPOs(List<D> dos) {
		if (CollectionUtil.isEmpty(dos)) {
			return CollectionUtil.newArrayList();
		}
		return dos.stream().map(this::convertToPO).collect(Collectors.toList());
	}

	/**
	 * 保存
	 *
	 * @param entity 数据库领域对象
	 */
	default void save(D entity) {
		P po = convertToPO(entity);
		save(po);
		entity.setId(po.getId());
	}

	/**
	 * 批量保存
	 *
	 * @param entities 数据库领域对象
	 */
	default void saveBatch(List<D> entities) {
		saveBatch(convertToPOs(entities));
	}

	/**
	 * 根据id删除
	 *
	 * @param id 主键id
	 */
	default void deleteById(Long id) {
		lambdaUpdate().eq(P::getId, id).update();
	}

	/**
	 * 根据id列表删除
	 *
	 * @param ids 主键id列表
	 */
	default void deleteByIds(List<Long> ids) {
		lambdaUpdate().in(P::getId, ids).remove();
	}

	/**
	 * 根据id修改
	 *
	 * @param entity 数据库领域对象
	 */
	default void updateById(D entity) {
		lambdaUpdate().eq(P::getId, entity.getId()).update(convertToPO(entity));
	}

	/**
	 * 根据id查询
	 *
	 * @param id 主键id
	 */
	default D findById(Long id) {
		P entity = lambdaQuery().eq(P::getId, id).one();
		return convertToDO(entity);
	}

	/**
	 * 查询所有
	 */
	default List<D> findAll() {
		List<P> list = lambdaQuery().list();
		return convertToDOs(list);
	}

	/**
	 * 根据id列表查询
	 *
	 * @param ids 主键id列表
	 * @return {@link List}<{@link D}>
	 */
	default List<D> findByIds(List<Long> ids) {
		List<P> list = lambdaQuery().in(P::getId, ids).list();
		return convertToDOs(list);
	}

	/**
	 * 分页查询
	 *
	 * @param req 请求
	 * @return {@link PageRes}<{@link D}>
	 */
	default PageRes<D> listByPage(PageReq req) {
		IPage<P> page = new Page<>(req.getPageIndex(), req.getPageSize());
		lambdaQuery().page(page);
		return PageUtil.buildPage(page, convertToDOs(page.getRecords()));
	}
}
