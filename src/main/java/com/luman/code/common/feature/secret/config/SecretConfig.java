package com.luman.code.common.feature.secret.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "smy.secret")
@Data
public class SecretConfig {

	private boolean mode = true;

}
