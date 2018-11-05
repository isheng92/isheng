package com.isheng.core.sys.dao.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.isheng.common.base.BaseMapper;
import com.isheng.common.exception.BizException;
import com.isheng.core.sys.entity.User;
import com.isheng.core.sys.query.UserQuery;

@Mapper
@Repository
public interface UserMapper extends BaseMapper<User, UserQuery> {
	
	User selectByLoginName(String loginName) throws BizException;
	
	User selectByMobile(String mobile) throws BizException;

}
