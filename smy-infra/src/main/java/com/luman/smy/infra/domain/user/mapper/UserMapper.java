package com.luman.smy.infra.domain.user.mapper;

import com.luman.smy.infra.domain.user.dataobject.UserDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;


/**
 * 用户映射器
 *
 * @author yeyinghao
 * @date 2024/08/26
 */
@Repository
public interface UserMapper extends BaseMapper<UserDO> {
}
