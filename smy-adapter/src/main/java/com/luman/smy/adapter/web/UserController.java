package com.luman.smy.adapter.web;

import cn.dev33.satoken.annotation.SaIgnore;
import com.luman.smy.client.dto.ListModel;
import com.luman.smy.client.dto.PageModel;
import com.luman.smy.client.dto.Response;
import com.luman.smy.client.shared.api.UserManager;
import com.luman.smy.client.shared.dto.UserPageQueryCmd;
import com.luman.smy.client.shared.dto.UserRegisterCmd;
import com.luman.smy.client.shared.dto.data.UserVO;
import com.luman.smy.infra.common.log.web.WebLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 用户控制器
 *
 * @author yeyinghao
 * @date 2024/08/19
 */
@RestController
@RequestMapping("user")
@WebLog
public class UserController {

	@Autowired
	private UserManager userService;

	@SaIgnore
	@PostMapping(value = "/register")
	public Response<UserVO> register(@RequestBody UserRegisterCmd cmd) {
		return userService.register(cmd);
	}

	@SaIgnore
	@PostMapping(value = "/list")
	public Response<ListModel<UserVO>> list() {
		return userService.list();
	}

	@SaIgnore
	@PostMapping(value = "/page")
	public Response<PageModel<UserVO>> page(@RequestBody UserPageQueryCmd cmd) {
		return userService.page(cmd);
	}

}

