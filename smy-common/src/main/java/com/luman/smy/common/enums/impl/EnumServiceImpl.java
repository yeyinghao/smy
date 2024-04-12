package com.luman.smy.common.enums.impl;

import com.luman.smy.common.enums.BaseEnum;
import com.luman.smy.common.util.LoggerUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;

import javax.annotation.PostConstruct;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 枚举服务实现
 *
 * @author yeyinghao
 * @date 2024/04/07
 */
@Component
@Slf4j
public class EnumServiceImpl {

	//用于存放枚举的MAP
	public static final Map<String, List<BaseEnum>> ENUM_MAP = new HashMap<>();

	/**
	 * 基础包
	 */
	private static final String BASE_PACKAGES = "com.luman";

	/**
	 * 资源模式
	 */
	private static final String RESOURCE_PATTERN = "/**/*.class";

	/**
	 * 资源模式解析器
	 */
	private final ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();

	/**
	 * 根据key获取枚举类Class
	 *
	 * @param code 处理后的枚举类名，处理方式是将枚举类名首字母转小写，去掉末尾的Enum
	 * @return {@link List}<{@link BaseEnum}>
	 */
	public static List<BaseEnum> getEnumClass(String code) {
		return ENUM_MAP.get(code);
	}

	@PostConstruct
	public void initEnumsMap() {
		try {
			//根据classname生成class对应的资源路径,需要扫描的包路径
			String pattern = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX + ClassUtils.convertClassNameToResourcePath(BASE_PACKAGES) + RESOURCE_PATTERN;
			//获取classname的IO流资源
			Resource[] resources = resourcePatternResolver.getResources(pattern);
			//MetadataReaderFactory接口 ，MetadataReader的工厂接口。允许缓存每个MetadataReader的元数据集
			MetadataReaderFactory readerFactory = new CachingMetadataReaderFactory(this.resourcePatternResolver);
			for (Resource resource : resources) {
				if (resource.isReadable()) {
					//通过class资源（resource）生成MetadataReader
					MetadataReader reader = readerFactory.getMetadataReader(resource);
					//获取class名
					String className = reader.getClassMetadata().getClassName();
					Class<?> clz = Class.forName(className);
					if (!clz.isEnum()) {
						continue;
					}
					//通过反射调用枚举的values方法
					Method valuesMethod = clz.getMethod("values");
					BaseEnum[] values = (BaseEnum[]) valuesMethod.invoke(null);
					//将枚举类名首字母转小写，去掉末尾的Enum
					ENUM_MAP.put(clz.getSimpleName().substring(0, 1).toLowerCase() + clz.getSimpleName().substring(1).replace("Enum", ""), Arrays.asList(values));
				}
			}
		} catch (Throwable e) {
			LoggerUtil.error(log, e);
		}
	}

}

