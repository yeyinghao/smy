package com.luman.smy.infra.domain.user.dataobject;

import com.luman.smy.infra.domain.dal.model.DO;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

/**
 * 模块名称: app
 * 模块描述: UserInfoDO
 *
 * @author amos.wang
 * @date 2021/2/5 13:48
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("org_user_info")
public class UserInfoDO extends DO {

    private Long id;

    private String phoneNo;

    private Integer gender;

    private LocalDate birthday;

    private String description;

}
