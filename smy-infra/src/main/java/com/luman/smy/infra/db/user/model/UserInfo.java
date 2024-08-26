package com.luman.smy.infra.db.user.model;

import com.luman.smy.infra.integration.dal.model.BaseDP;
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
public class UserInfo extends BaseDP {

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
