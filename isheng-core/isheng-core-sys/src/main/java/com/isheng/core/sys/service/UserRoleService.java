package com.isheng.core.sys.service;

import java.util.List;

import com.isheng.common.base.BaseService;
import com.isheng.common.exception.BizException;
import com.isheng.core.sys.entity.UserRole;
import com.isheng.core.sys.query.UserRoleQuery;

public interface UserRoleService extends BaseService<UserRole, UserRoleQuery> {
	
	/**
	 * 批量新增
	 * 
	 * @param userId
	 * @param roleIds
	 * @throws BizException
	 */
	public void batchAdd(String userId, List<String> roleIds) throws BizException;
	
	/**
	 * 获取用户的所有用户和角色信息
	 * 
	 * @param userId
	 * @return
	 * @throws BizException
	 */
	public List<UserRole> getListByUserId(String userId) throws BizException;

}
