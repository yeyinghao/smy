/*
 * Copyright (c) Ye Yinghao
 * 2022.1 - 2024.1
 */

package com.luman.smy.infra.config;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.math.Money;
import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.luman.smy.infra.common.constant.CommConstant;
import com.luman.smy.infra.common.enums.BaseEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Objects;

/**
 * 拦截器配置
 *
 * @author yeyinghao
 * @date 2023/08/01
 */
@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

	/**
	 * js的number最大安全值
	 */
	private static final Long MAX_VALUE = 9007199254740991L;

	/**
	 * 添加处理程序以提供来自 Web 应用程序根目录、类路径等特定位置的静态资源，例如图像、js 和 css 文件。
	 *
	 * @param registry registry
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/favicon.ico").addResourceLocations("classpath:/static/");
	}

	/**
	 * 跨域支持
	 *
	 * @param registry 注册表
	 */
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedOriginPatterns("*").allowCredentials(true)
				.allowedMethods("GET", "POST", "DELETE", "PUT", "PATCH", "OPTIONS", "HEAD")
				.maxAge(CommConstant.CORS_MAX_AGE_SECOND);
	}

	/**
	 * 扩展消息转换器
	 *
	 * @param converters 转换器
	 */
	@Override
	public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.stream().filter(converter -> MappingJackson2HttpMessageConverter.class.isAssignableFrom(converter.getClass())).findFirst().ifPresent(converter -> {
			MappingJackson2HttpMessageConverter messageConverter = (MappingJackson2HttpMessageConverter) converter;
			// 模块
			SimpleModule module = new SimpleModule();
			// Long转String
			module.addSerializer(Long.class, longJsonSerializer());
			module.addSerializer(Long.TYPE, longJsonSerializer());
			// 处理BaseEnum枚举
			module.addSerializer(BaseEnum.class, baseEnumJsonSerializer());
			module.addDeserializer(BaseEnum.class, baseEnumJsonDeserializer());
			// 处理BaseEnum枚举
			module.addSerializer(Money.class, moneyJsonSerializer());
			module.addDeserializer(Money.class, moneyJsonDeserializer());
			// 时间格式化
			module.addSerializer(LocalDateTime.class, localDateTimeJsonSerializer());
			module.addDeserializer(LocalDateTime.class, localDateTimeJsonDeserializer());
			module.addSerializer(LocalDate.class, localDateJsonSerializer());
			module.addDeserializer(LocalDate.class, localDateJsonDeserializer());
			// 注册自定义模块
			ObjectMapper objectMapper = messageConverter.getObjectMapper();
			objectMapper.registerModule(module);
		});
	}

	private static JsonSerializer<Long> longJsonSerializer() {
		return new JsonSerializer<>() {
			@Override
			public void serialize(Long value, JsonGenerator jsonGenerator, SerializerProvider serializers) throws IOException {
				if (Objects.nonNull(value)) {
					if (value < MAX_VALUE) {
						jsonGenerator.writeNumber(value);
					} else {
						jsonGenerator.writeString(String.valueOf(value));
					}
				}
			}
		};
	}

	/**
	 * 获取基础枚举json序列化器
	 *
	 * @return {@link JsonSerializer}<{@link BaseEnum}>
	 */
	private static JsonSerializer<BaseEnum> baseEnumJsonSerializer() {
		return new JsonSerializer<>() {
			@Override
			public void serialize(BaseEnum baseEnum, JsonGenerator jsonGenerator, SerializerProvider serializers) throws IOException {
				String name = null;
				String description = null;
				if (Objects.nonNull(baseEnum)) {
					name = baseEnum.name();
					// 增加一个字段，格式为【枚举类名称+Text】，存储枚举的name
					description = baseEnum.getDescription();
				}
				jsonGenerator.writeString(name);
				// 增加一个字段，格式为【枚举类名称+Text】，存储枚举的name
				jsonGenerator.writeStringField(jsonGenerator.getOutputContext().getCurrentName() + "Text", description);
			}
		};
	}

	/**
	 * 获取基础枚举json反序列化器
	 *
	 * @return {@link JsonDeserializer}<{@link BaseEnum}>
	 */
	@SuppressWarnings("unchecked")
	private static JsonDeserializer<BaseEnum> baseEnumJsonDeserializer() {
		return new JsonDeserializer<>() {
			@Override
			public BaseEnum deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
				JsonNode node = p.getCodec().readTree(p);
				String currentName = p.currentName();
				Object currentValue = p.getCurrentValue();
				Class<BaseEnum> findPropertyType = (Class<BaseEnum>) BeanUtils.findPropertyType(currentName, currentValue.getClass());
				return BaseEnum.getEnumByNameOrDesc(findPropertyType, node.asText());
			}
		};
	}


	/**
	 * 获取基础枚举json序列化器
	 *
	 * @return {@link JsonSerializer}<{@link BaseEnum}>
	 */
	private static JsonSerializer<Money> moneyJsonSerializer() {
		return new JsonSerializer<>() {
			@Override
			public void serialize(Money money, JsonGenerator jsonGenerator, SerializerProvider serializers) throws IOException {
				String amount = null;
				if (Objects.nonNull(money)) {
					amount = money.getAmount().toPlainString();
				}
				jsonGenerator.writeNumber(amount);
			}
		};
	}

	/**
	 * 获取基础枚举json反序列化器
	 *
	 * @return {@link JsonDeserializer}<{@link BaseEnum}>
	 */
	private static JsonDeserializer<Money> moneyJsonDeserializer() {
		return new JsonDeserializer<>() {
			@Override
			public Money deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
				String amount = ((JsonNode) (p.getCodec().readTree(p))).asText();
				if (StrUtil.isBlank(amount)) {
					return null;
				}
				return new Money(((JsonNode) (p.getCodec().readTree(p))).asText());
			}
		};
	}

	/**
	 * 获取基础枚举json序列化器
	 *
	 * @return {@link JsonSerializer}<{@link BaseEnum}>
	 */
	private static JsonSerializer<LocalDate> localDateJsonSerializer() {
		return new JsonSerializer<>() {
			@Override
			public void serialize(LocalDate date, JsonGenerator jsonGenerator, SerializerProvider serializers) throws IOException {
				if (date == null) {
					return;
				}
				jsonGenerator.writeNumber(date.atStartOfDay().toInstant(ZoneOffset.UTC).getEpochSecond());
			}
		};
	}

	/**
	 * 获取基础枚举json反序列化器
	 *
	 * @return {@link JsonDeserializer}<{@link BaseEnum}>
	 */
	private static JsonDeserializer<LocalDate> localDateJsonDeserializer() {
		return new JsonDeserializer<>() {
			@Override
			public LocalDate deserialize(JsonParser jsonParser, DeserializationContext ctxt) throws IOException, JsonProcessingException {
				long timestamp = jsonParser.getValueAsLong();
				if (timestamp < 0) {
					return null;
				}
				return DateUtil.toLocalDateTime(Instant.ofEpochSecond(timestamp)).toLocalDate();
			}
		};
	}


	/**
	 * 获取基础枚举json序列化器
	 *
	 * @return {@link JsonSerializer}<{@link BaseEnum}>
	 */
	private static JsonSerializer<LocalDateTime> localDateTimeJsonSerializer() {
		return new JsonSerializer<>() {
			@Override
			public void serialize(LocalDateTime date, JsonGenerator jsonGenerator, SerializerProvider serializers) throws IOException {
				if (date == null) {
					return;
				}
				jsonGenerator.writeNumber(date.toInstant(ZoneOffset.UTC).getEpochSecond());
			}
		};
	}

	/**
	 * 获取基础枚举json反序列化器
	 *
	 * @return {@link JsonDeserializer}<{@link BaseEnum}>
	 */
	private static JsonDeserializer<LocalDateTime> localDateTimeJsonDeserializer() {
		return new JsonDeserializer<>() {
			@Override
			public LocalDateTime deserialize(JsonParser jsonParser, DeserializationContext ctxt) throws IOException, JsonProcessingException {
				long timestamp = jsonParser.getValueAsLong();
				if (timestamp < 0) {
					return null;
				}
				return DateUtil.toLocalDateTime(Instant.ofEpochSecond(timestamp));
			}
		};
	}


}
