package com.isheng.core.sys.dao.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.isheng.common.base.AbstractBaseDao;
import com.isheng.common.base.BaseMapper;
import com.isheng.common.exception.BizException;
import com.isheng.core.sys.dao.UserDao;
import com.isheng.core.sys.dao.mapper.UserMapper;
import com.isheng.core.sys.entity.User;
import com.isheng.core.sys.query.UserQuery;

@Component("userDao")
@Service(interfaceClass = UserDao.class)
public class UserDaoImpl extends AbstractBaseDao<User, UserQuery> implements UserDao {

	@Resource
	private UserMapper userMapper;

	@Override
	protected BaseMapper<User, UserQuery> getMapper() {
		return userMapper;
	}

	@Override
	public User getByLoginName(String loginName) throws BizException {
		return userMapper.selectByLoginName(loginName);
	}

	@Override
	public User getByMobile(String mobile) throws BizException {
		return userMapper.selectByMobile(mobile);
	}

}
