package com.luman.smy.domain.user;

import lombok.Data;

import java.time.LocalDate;


/**
 * 用户实体
 *
 * @author yeyinghao
 * @date 2024/08/25
 */
@Data
public class UserEntity {

    private Long id;

    /**
     * 用户名
     */
    private UserName username;
    /**
     * 密码
     */
    private UserPassword password;

    /**
     * 姓名
     */
    private String name;

    /**
     * 手机号
     */
    private String phoneNo;

    /**
     * 性别
     */
    private Integer gender;

    /**
     * 生日
     */
    private LocalDate birthday;

    /**
     * 描述
     */
    private String description;
}
