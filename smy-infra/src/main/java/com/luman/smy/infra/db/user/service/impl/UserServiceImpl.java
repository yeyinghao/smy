package com.luman.smy.infra.db.user.service.impl;

import com.luman.smy.infra.db.user.dataobject.UserDO;
import com.luman.smy.infra.db.user.mapper.UserMapper;
import com.luman.smy.infra.db.user.model.User;
import com.luman.smy.infra.db.user.service.UserService;
import com.luman.smy.integration.dal.impl.CoreServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends CoreServiceImpl<UserDO, User, UserMapper> implements UserService {

	@Override
	public UserDO convertToPO(User user) {
		UserDO userDO = new UserDO();
		userDO.setUsername(user.getUsername());
		userDO.setPassword(user.getPassword());
		userDO.setName(user.getName());
		userDO.setInfoId(user.getInfoId());
		userDO.setId(user.getId());
		userDO.setCreateTime(user.getCreateTime());
		userDO.setUpdateTime(user.getUpdateTime());
		userDO.setExtInfo(user.getExtInfo());
		userDO.setStatus(user.getStatus());
		return userDO;
	}

	@Override
	public User convertToDP(UserDO userDO) {
		User user = new User();
		user.setUsername(userDO.getUsername());
		user.setPassword(userDO.getPassword());
		user.setName(userDO.getName());
		user.setInfoId(userDO.getInfoId());
		user.setId(userDO.getId());
		user.setCreateTime(userDO.getCreateTime());
		user.setUpdateTime(userDO.getUpdateTime());
		user.setExtInfo(userDO.getExtInfo());
		user.setStatus(userDO.getStatus());
		return user;
	}
}
