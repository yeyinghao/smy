package com.luman.smy.domain.user;

import jakarta.validation.ValidationException;
import org.apache.commons.lang3.StringUtils;

/**
 * 用户名称
 *
 * @author <a href="mailto:daoyuan0626@gmail.com">amos.wang</a>
 * @date 2021/11/27
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
