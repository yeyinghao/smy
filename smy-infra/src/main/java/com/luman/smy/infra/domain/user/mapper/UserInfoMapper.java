package com.luman.smy.infra.domain.user.mapper;

import com.luman.smy.infra.domain.user.dataobject.UserInfoDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * 用户信息映射器
 *
 * @author yeyinghao
 * @date 2024/08/26
 */
@Repository
public interface UserInfoMapper extends BaseMapper<UserInfoDO> {
}
