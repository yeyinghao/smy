package com.luman.smy.client.shared.dto.data;

import lombok.Data;

import java.time.LocalDate;


/**
 * 用户vo
 *
 * @author yeyinghao
 * @date 2024/08/25
 */
@Data
public class UserVO {

    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 姓名（较常用，故放在用户主表）
     */
    private String name;
    /**
     * 手机号（可用手机号登录，故放在用户主表）
     */
    private String phoneNo;

    /*
     * 附加信息
     */

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
