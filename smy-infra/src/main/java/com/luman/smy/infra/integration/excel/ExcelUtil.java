/*
 * Copyright (c) Ye Yinghao
 * 2022.1 - 2024.3
 */

package com.luman.smy.infra.integration.excel;

import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelWriter;
import com.luman.smy.infra.integration.excel.annotations.ExcelAlias;
import lombok.SneakyThrows;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ExcelUtil {

	/**
	 * 读取Excel并转化为list
	 * 需要指定返回时元素的类
	 *
	 * @param path     需要读取的Excel 的路径aa
	 * @param beanType 指定的类，
	 * @return list
	 */
	public static <T> List<T> readExcel(String path, Class<T> beanType) {
		File file = new File(path);
		ExcelReader reader = cn.hutool.poi.excel.ExcelUtil.getReader(file);
		reader.setHeaderAlias(getHeaderAlias(beanType, true));
		List<T> list = reader.readAll(beanType);
		reader.close();
		return list;
	}

	/**
	 * 读取excel
	 *
	 * @param inputStream 输入流
	 * @param beanType    bean类型
	 * @return {@link List}<{@link T}>
	 */
	public static <T> List<T> readExcel(InputStream inputStream, Class<T> beanType) {
		ExcelReader reader = cn.hutool.poi.excel.ExcelUtil.getReader(inputStream);
		reader.setHeaderAlias(getHeaderAlias(beanType, true));
		List<T> list = reader.readAll(beanType);
		reader.close();
		return list;
	}

	/**
	 * 将list写入Excel中
	 *
	 * @param path 需要写入的Excel 的路径
	 * @param list 写入的内容
	 */
	@SneakyThrows
	public static <T> void writeExcel(String path, List<T> list, Class<T> beanType, boolean isXlsx) {
		OutputStream baos = null;
		ExcelWriter writer = null;
		try {
			baos = Files.newOutputStream(Paths.get(path));
			Map<String, String> headerAlias = getHeaderAlias(beanType, false);
			writer = cn.hutool.poi.excel.ExcelUtil.getWriter(isXlsx);
			writer.setHeaderAlias(headerAlias);
			// 写入并刷盘
			writer.write(list).flush(baos);
		} finally {
			IoUtil.close(baos);
			IoUtil.close(writer);
		}
	}

	@SneakyThrows
	public static <T> byte[] writeExcel(List<T> list, Class<T> beanType, boolean isXlsx) {
		ByteArrayOutputStream baos = null;
		ExcelWriter writer = null;
		try {
			baos = new ByteArrayOutputStream();
			Map<String, String> headerAlias = getHeaderAlias(beanType, false);
			writer = cn.hutool.poi.excel.ExcelUtil.getWriter(isXlsx);
			writer.setHeaderAlias(headerAlias);
			// 写入并刷盘
			writer.write(list).flush(baos);
			return baos.toByteArray();
		} finally {
			IoUtil.close(baos);
			IoUtil.close(writer);
		}
	}

	/**
	 * 获得一个类所有 有别名的 字段 和 别名
	 *
	 * @param beanType 类
	 * @param isRead   每个键值对格式为 ("已有的","想改的")
	 *                 true 读取时 每个键值对格式为:(“别名”, “字段名”), 从excel中读取到“已有的(别名)”, 改为“想改的(字段名)”
	 *                 false 写入时 每个键值对格式为:(“字段名”, “别名”), 每个字段对应的“已有的(字段名)”, 改为“想改的(别名)”
	 * @param <T>      <T>
	 * @return Map<String, String>
	 */
	public static <T> Map<String, String> getHeaderAlias(Class<T> beanType, boolean isRead) {
		Map<String, String> headerAlias = new LinkedHashMap<>();
		// Hutool 获取字段集合的方法，无需try/catch
		List<Field> fields = Arrays.asList(ReflectUtil.getFields(beanType));
		if (fields.isEmpty()) {
			return headerAlias;
		}
		for (Field field : fields) {
			// 获得每个字段的 @ExcelAlias注解
			ExcelAlias anno = field.getAnnotation(ExcelAlias.class);
			if (anno == null || "".equals(anno.value())) {
				continue;
			}
			if (isRead) {
				// 读取时 每个键值对格式为:(“别名”, “字段名”)
				headerAlias.put(anno.value(), field.getName());
			} else {
				// 写入时 每个键值对格式为:(“字段名”, “别名”)
				headerAlias.put(field.getName(), anno.value());
			}
		}
		return headerAlias;
	}
}