package com.luman.smy.infra.db.user.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.luman.smy.client.dto.Paging;
import com.luman.smy.infra.db.user.model.User;
import com.luman.smy.infra.integration.dal.CoreService;

public interface UserService extends CoreService<User> {
	IPage<User> page(Paging paging, String name);
}
