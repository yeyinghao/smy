package com.luman.smy.infra.domain.user.convertor;

import com.luman.smy.domain.user.model.UserEntity;
import com.luman.smy.domain.user.model.UserName;
import com.luman.smy.infra.db.user.model.User;
import com.luman.smy.infra.db.user.model.UserInfo;
import org.apache.commons.lang3.tuple.ImmutablePair;

/**
 * UserConvertor DO <---> Entity
 *
 * @author <a href="mailto:daoyuan0626@gmail.com">amos.wang</a>
 * @date 2021/1/9
 */
public class UserConvertor {

    public static UserEntity toEntity(User userDO, UserInfo userInfoDO) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(userDO.getId());
        userEntity.setUsername(new UserName(userDO.getUsername()));
        userEntity.setName(userDO.getName());

        if (userInfoDO != null) {
            userEntity.setPhoneNo(userInfoDO.getPhoneNo());
            userEntity.setGender(userInfoDO.getGender());
            userEntity.setBirthday(userInfoDO.getBirthday());
            userEntity.setDescription(userInfoDO.getDescription());
        }

        return userEntity;
    }

    public static ImmutablePair<User, UserInfo> toAddUserDO(UserEntity userEntity) {
        User userDO = new User();
        userDO.setId(userEntity.getId());
        userDO.setUsername(userEntity.getUsername().getName());
        userDO.setPassword(userEntity.getPassword().getEncryptPassword());
        userDO.setName(userEntity.getName());

        // user info
        UserInfo userInfoDO = new UserInfo();
        userInfoDO.setPhoneNo(userEntity.getPhoneNo());
        userInfoDO.setGender(userEntity.getGender());
        userInfoDO.setBirthday(userEntity.getBirthday());
        userInfoDO.setDescription(userEntity.getDescription());

        return new ImmutablePair<>(userDO, userInfoDO);
    }

    public static void toModifyUserDO(UserEntity userEntity, User userDO, UserInfo userInfoDO) {
        userDO.setName(userEntity.getName());
        userDO.setUsername(userEntity.getUsername().getName());

        userInfoDO.setPhoneNo(userEntity.getPhoneNo());
        userInfoDO.setGender(userEntity.getGender());
        userInfoDO.setBirthday(userEntity.getBirthday());
        userInfoDO.setDescription(userEntity.getDescription());
    }

}
