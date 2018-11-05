package com.isheng.core.sys.dao;

import com.isheng.common.base.BaseDao;
import com.isheng.common.exception.BizException;
import com.isheng.core.sys.entity.User;
import com.isheng.core.sys.query.UserQuery;

public interface UserDao extends BaseDao<User, UserQuery>{
	
	/**
	 * 根据登录名获取
	 * @param loginName
	 * @return
	 * @throws BizException
	 */
	User getByLoginName(String loginName) throws BizException;
	
	/**
	 * 根据手机号获取
	 * @param mobile
	 * @return
	 * @throws BizException
	 */
	User getByMobile(String mobile) throws BizException;

}
