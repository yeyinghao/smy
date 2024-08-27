package com.luman.smy.infra.domain.user.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.luman.smy.client.dto.Paging;
import com.luman.smy.infra.domain.user.mapper.model.User;
import com.luman.smy.infra.integration.dal.CoreService;

/**
 * 用户服务
 *
 * @author yeyinghao
 * @date 2024/08/26
 */
public interface UserService extends CoreService<User> {
	IPage<User> page(Paging paging, String name);
}
