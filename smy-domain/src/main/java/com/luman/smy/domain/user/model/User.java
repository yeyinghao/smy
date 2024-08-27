package com.luman.smy.domain.user.model;

import com.luman.smy.domain.dal.model.DP;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 用户
 *
 * @author yeyinghao
 * @date 2024/08/26
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class User extends DP {

    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;

    /**
     * 姓名（较常用，故放在用户主表）
     */
    private String name;

    /**
     * 附加信息主键ID
     */
    private Long infoId;

}
