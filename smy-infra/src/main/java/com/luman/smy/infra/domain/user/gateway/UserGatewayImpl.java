package com.luman.smy.infra.domain.user.gateway;

import com.luman.smy.domain.user.model.UserEntity;
import com.luman.smy.infra.domain.user.convertor.UserConvertor;
import com.luman.smy.domain.user.gateway.UserGateway;
import com.luman.smy.infra.db.user.model.User;
import com.luman.smy.infra.db.user.model.UserInfo;
import com.luman.smy.infra.db.user.service.UserInfoService;
import com.luman.smy.infra.db.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * UserGatewayImpl
 *
 * @author <a href="mailto:daoyuan0626@gmail.com">amos.wang</a>
 * @date 2021/1/8
 */
@Component("userGateway")
@RequiredArgsConstructor
public class UserGatewayImpl implements UserGateway {

	private final UserService userService;
	private final UserInfoService userInfoService;

	@Override
	@Transactional(rollbackFor = Throwable.class)
	public UserEntity save(UserEntity userEntity) {

		// 初始化用户信息
		ImmutablePair<User, UserInfo> pair = UserConvertor.toAddUserDO(userEntity);
		User userDO = pair.getLeft();
		UserInfo userInfoDO = pair.getRight();

		// 1. 先保存用户信息
		userInfoService.save(userInfoDO);
		userService.save(userDO);
		return UserConvertor.toEntity(userDO, userInfoDO);
	}

	@Override
	public UserEntity findById(Long id) {
		return null;
	}

	@Override
	public UserEntity findPasswordInfo(String username) {
		return null;
	}

	@Override
	public Boolean checkByUsername(Long userId, String username) {
		return null;
	}
}
