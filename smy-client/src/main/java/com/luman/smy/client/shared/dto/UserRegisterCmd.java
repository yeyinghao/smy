package com.luman.smy.client.shared.dto;

import com.luman.smy.client.dto.Command;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


/**
 * 用户注册命令行
 *
 * @author yeyinghao
 * @date 2024/08/25
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisterCmd extends Command {

    private static final long serialVersionUID = -5726685703640910355L;

    public UserRegisterCmd(String username, String password) {
        this.username = username;
        this.password = password;
    }

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
