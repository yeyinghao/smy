package com.luman.smy.infra.db.user.dataobject;

import com.luman.smy.infra.integration.dal.model.BaseDO;
import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName("org_user_info")
public class UserInfoDO extends BaseDO {

    private Long id;

    private String phoneNo;

    private Integer gender;

    private LocalDate birthday;

    private String description;

}
