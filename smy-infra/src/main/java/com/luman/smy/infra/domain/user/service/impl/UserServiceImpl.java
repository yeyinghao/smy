package com.luman.smy.infra.domain.user.service.impl;

import com.luman.smy.client.dto.PageModel;
import com.luman.smy.client.dto.Paging;
import com.luman.smy.domain.user.model.User;
import com.luman.smy.domain.user.service.UserService;
import com.luman.smy.infra.domain.dal.impl.CoreServiceImpl;
import com.luman.smy.infra.domain.user.dataobject.UserDO;
import com.luman.smy.infra.domain.user.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
 * 用户服务实现
 *
 * @author yeyinghao
 * @date 2024/08/26
 */
@Service
public class UserServiceImpl extends CoreServiceImpl<User, UserDO, UserMapper> implements UserService {

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

	@Override
	public PageModel<User> page(Paging paging, String name) {
		return buildPage(lambdaQuery()
				.like(UserDO::getName, name)
				.page(buildPage(paging))
				.convert(this::convertToDP));
	}
}
