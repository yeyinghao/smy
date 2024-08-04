package com.luman.smy.infra.common.log;

import lombok.Data;
import org.slf4j.Logger;

import java.util.List;

@Data
public class LogInfo {

	private Logger log;

	private Boolean res;

	private Long startTime;

	private String className;

	private String methodName;

	private List<Object> args;

	private Object response;

	private Throwable throwable;

}
