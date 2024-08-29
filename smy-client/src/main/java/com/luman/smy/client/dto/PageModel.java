package com.luman.smy.client.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;

import static java.util.stream.Collectors.toList;

/**
 * 页面模型
 *
 * @author yeyinghao
 * @date 2024/08/19
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageModel<T> extends DTO {

	/**
	 * 串口版本uid
	 */
	@Serial
	private static final long serialVersionUID = 1L;

	/**
	 * 页面大小
	 */
	private Long pageSize;

	/**
	 * 页面索引
	 */
	private Long pageIndex;

	/**
	 * 总大小
	 */
	private Long totalSize;

	/**
	 * 集合
	 */
	private Collection<T> collection;

	public PageModel<T> setRecords(List<T> records) {
		this.collection = records;
		return this;
	}

	/**
	 * IPage 的泛型转换
	 *
	 * @param mapper 转换函数
	 * @param <R>    转换后的泛型
	 * @return 转换泛型后的 IPage
	 */
	@SuppressWarnings("unchecked")
	public <R> PageModel<R> convert(Function<? super T, ? extends R> mapper) {
		List<R> collect = this.getCollection().stream().map(mapper).collect(toList());
		return ((PageModel<R>) this).setRecords(collect);
	}
}
