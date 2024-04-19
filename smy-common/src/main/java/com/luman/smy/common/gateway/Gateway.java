package com.luman.smy.common.gateway;

import com.luman.smy.common.model.BaseDP;
import com.luman.smy.common.model.PageReq;
import com.luman.smy.common.model.PageRes;

import java.util.List;

/**
 * 基础数据服务
 *
 * @author yeyinghao
 * @date 2024/04/02
 */
public interface Gateway<DP extends BaseDP> {

	/**
	 * 保存
	 *
	 * @param entity 数据库领域对象
	 */
	void save(DP entity);

	/**
	 * 批量保存
	 *
	 * @param entities 数据库领域对象
	 */
	void saveBatch(List<DP> entities);

	/**
	 * 根据id删除
	 *
	 * @param id 主键id
	 */
	void deleteById(Long id);

	/**
	 * 根据id列表删除
	 *
	 * @param ids 主键id列表
	 */
	void deleteByIds(List<Long> ids);

	/**
	 * 根据id修改
	 *
	 * @param entity 数据库领域对象
	 */
	void updateById(DP entity);

	/**
	 * 根据id查询
	 *
	 * @param id 主键id
	 */
	DP findById(Long id);

	/**
	 * 查询所有
	 */
	List<DP> findAll();

	/**
	 * 根据id列表查询
	 *
	 * @param ids 主键id列表
	 * @return {@link List}<{@link DP}>
	 */
	List<DP> findByIds(List<Long> ids);

	/**
	 * 分页查询
	 *
	 * @param req 请求
	 * @return {@link PageRes}<{@link DP}>
	 */
	PageRes<DP> listByPage(PageReq req);
}
