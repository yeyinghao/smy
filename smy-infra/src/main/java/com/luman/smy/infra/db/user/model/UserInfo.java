package com.luman.smy.infra.db.user.model;

import com.luman.smy.integration.dal.model.BaseDP;
import lombok.Data;

import java.time.LocalDate;

/**
 * 模块名称: app
 * 模块描述: UserInfoDO
 *
 * @author amos.wang
 * @date 2021/2/5 13:48
 */
@Data
public class UserInfo extends BaseDP {

    private Long id;

    private String phoneNo;

    private Integer gender;

    private LocalDate birthday;

    private String description;

}
