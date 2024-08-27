package com.luman.smy.domain.user.model;

import com.luman.smy.domain.dal.model.DP;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;


/**
 * 用户信息
 *
 * @author yeyinghao
 * @date 2024/08/26
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserInfo extends DP {

    /**
     * id
     */
    private Long id;

    /**
     * 电话
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
