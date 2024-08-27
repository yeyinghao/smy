package com.luman.smy.infra.domain.user.service.impl;

import com.luman.smy.infra.domain.user.mapper.dataobject.UserInfoDO;
import com.luman.smy.infra.domain.user.mapper.UserInfoMapper;
import com.luman.smy.infra.domain.user.mapper.model.UserInfo;
import com.luman.smy.infra.domain.user.service.UserInfoService;
import com.luman.smy.infra.integration.dal.impl.CoreServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 用户信息服务实现
 *
 * @author yeyinghao
 * @date 2024/08/26
 */
@Service
public class UserInfoServiceImpl extends CoreServiceImpl<UserInfo, UserInfoDO, UserInfoMapper> implements UserInfoService {

	@Override
	public UserInfoDO convertToPO(UserInfo userInfo) {
	    UserInfoDO userInfoDO = new UserInfoDO();
	    userInfoDO.setId(userInfo.getId());
	    userInfoDO.setPhoneNo(userInfo.getPhoneNo());
	    userInfoDO.setGender(userInfo.getGender());
	    userInfoDO.setBirthday(userInfo.getBirthday());
	    userInfoDO.setDescription(userInfo.getDescription());
	    userInfoDO.setId(userInfo.getId());
	    userInfoDO.setCreateTime(userInfo.getCreateTime());
	    userInfoDO.setUpdateTime(userInfo.getUpdateTime());
	    userInfoDO.setExtInfo(userInfo.getExtInfo());
	    userInfoDO.setStatus(userInfo.getStatus());
	    return userInfoDO;
	}

	@Override
	public UserInfo convertToDP(UserInfoDO userInfoDO) {
	    UserInfo userInfo = new UserInfo();
	    userInfo.setId(userInfoDO.getId());
	    userInfo.setPhoneNo(userInfoDO.getPhoneNo());
	    userInfo.setGender(userInfoDO.getGender());
	    userInfo.setBirthday(userInfoDO.getBirthday());
	    userInfo.setDescription(userInfoDO.getDescription());
	    userInfo.setId(userInfoDO.getId());
	    userInfo.setCreateTime(userInfoDO.getCreateTime());
	    userInfo.setUpdateTime(userInfoDO.getUpdateTime());
	    userInfo.setExtInfo(userInfoDO.getExtInfo());
	    userInfo.setStatus(userInfoDO.getStatus());
	    return userInfo;
	}
}
