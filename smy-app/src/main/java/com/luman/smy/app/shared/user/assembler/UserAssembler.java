package com.luman.smy.app.shared.user.assembler;

import com.luman.smy.domain.user.UserEntity;
import com.luman.smy.domain.user.UserName;
import com.luman.smy.domain.user.UserPassword;
import com.luman.smy.client.shared.dto.UserRegisterCmd;
import com.luman.smy.client.shared.dto.data.UserVO;


/**
 * 用户汇编
 *
 * @author yeyinghao
 * @date 2024/08/25
 */
public class UserAssembler {

    /**
     * 实体
     *
     * @param co 有限公司
     * @return {@link UserEntity }
     */
    public static UserEntity toEntity(UserRegisterCmd co) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(new UserName(co.getUsername()));
        userEntity.setPassword(new UserPassword(co.getPassword()));
        userEntity.setName(co.getName());
        userEntity.setPhoneNo(co.getPhoneNo());
        userEntity.setGender(co.getGender());
        userEntity.setBirthday(co.getBirthday());
        userEntity.setDescription(co.getDescription());

        return userEntity;
    }

    /**
     * 来value对象
     *
     * @param userEntity 用户实体
     * @return {@link UserVO }
     */
    public static UserVO toValueObject(UserEntity userEntity) {
        UserVO userVO = new UserVO();
        userVO.setId(userEntity.getId());
        userVO.setName(userEntity.getName());
        userVO.setUsername(userEntity.getUsername().getName());
        userVO.setPhoneNo(userEntity.getPhoneNo());
        userVO.setGender(userEntity.getGender());
        userVO.setBirthday(userEntity.getBirthday());
        userVO.setDescription(userEntity.getDescription());

        return userVO;
    }

}
