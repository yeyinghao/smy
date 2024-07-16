package com.luman.smy.start;

import com.luman.smy.infra.common.util.LoggerUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Spring Boot Starter
 *
 * @author Frank Zhang
 */
@EnableDubbo
@EnableScheduling
@SpringBootApplication(scanBasePackages = {"com.luman", "com.alibaba"})
@MapperScan("com.luman.smy.infra.db.**.mapper")
@RestController
@Slf4j
public class Application {

	public static void main(String[] args) {
		try {
			SpringApplication.run(Application.class, args);
		} catch (Exception e) {
			LoggerUtil.error(log, e);
		}
	}

	@GetMapping("/")
	public String start() {
		return "Hello, welcome to COLA world!";
	}

}
