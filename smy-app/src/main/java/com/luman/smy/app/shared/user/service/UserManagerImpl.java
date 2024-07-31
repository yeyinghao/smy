package com.luman.smy.app.shared.user.service;

import com.luman.smy.app.shared.user.command.UserRegisterCmdExe;
import com.luman.smy.app.shared.user.command.query.UserInfoQueryExe;
import com.luman.smy.client.dto.ListModel;
import com.luman.smy.client.dto.PageModel;
import com.luman.smy.client.dto.Response;
import com.luman.smy.client.shared.api.UserManager;
import com.luman.smy.client.shared.dto.UserPageQueryCmd;
import com.luman.smy.client.shared.dto.UserRegisterCmd;
import com.luman.smy.client.shared.dto.data.UserVO;
import com.luman.smy.infra.common.helper.PageHelper;
import com.luman.smy.infra.common.template.RTemplate;
import com.luman.smy.infra.db.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户相关
 *
 * @author <a href="mailto:daoyuan0626@gmail.com">amos.wang</a>
 * @date 2021/1/8
 */
@Service
public class UserManagerImpl implements UserManager {

	/**
	 * xxxExe 避免 Service 膨胀利器
	 */
	@Autowired
	private UserRegisterCmdExe userRegisterCmdExe;

	@Autowired
	private UserInfoQueryExe userInfoQueryExe;

	@Override
	public Response<UserVO> register(UserRegisterCmd cmd) {
		return RTemplate.excute(() -> {
			return userRegisterCmdExe.execute(cmd);
		}, cmd);
	}

	@Override
	public Response<ListModel<UserVO>> list() {
		return RTemplate.excute(() -> {
			return new ListModel<>(userInfoQueryExe.list().stream().map(this::convertUser).toList());
		}, null);
	}

	private UserVO convertUser(User user) {
		UserVO userVO = new UserVO();
		userVO.setUsername(user.getUsername());
		userVO.setName(user.getName());
		return userVO;
	}

	@Override
	public Response<PageModel<UserVO>> page(UserPageQueryCmd cmd) {
		return RTemplate.excute(() -> {
			return PageHelper.buildPage(userInfoQueryExe.page(cmd).convert(this::convertUser));
		}, cmd);
	}
}
