package com.isheng.core.sys.service;

import com.isheng.common.base.BaseService;
import com.isheng.common.exception.BizException;
import com.isheng.common.model.ResultResp;
import com.isheng.core.sys.entity.User;
import com.isheng.core.sys.pojo.UserLogin;
import com.isheng.core.sys.query.UserQuery;

public interface UserService extends BaseService<User, UserQuery> {
	
	/**
	 * 根据登录名查询
	 * @param loginName
	 * @return
	 * @throws BizException
	 */
	User getByLoginName(String loginName) throws BizException;
	
	/**
	 * 根据手机号查询
	 * @param mobile
	 * @return
	 * @throws BizException
	 */
	User getByMobile(String mobile) throws BizException;
	
	/**
	 * 用户登陆
	 * @param userLogin
	 * @return
	 * @throws BizException
	 */
	ResultResp<User> login(UserLogin userLogin) throws BizException;

}
