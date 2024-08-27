package com.luman.smy.domain.user.model;

import jakarta.validation.ValidationException;
import org.apache.commons.lang3.StringUtils;


/**
 * 用户名称
 *
 * @author yeyinghao
 * @date 2024/08/25
 */
public class UserName {

    private final String name;

    public UserName(String name) {
        if (StringUtils.isBlank(name)) {
            throw new ValidationException("用户名不能为空");
        }
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
