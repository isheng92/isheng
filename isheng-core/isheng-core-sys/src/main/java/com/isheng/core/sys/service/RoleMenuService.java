package com.isheng.core.sys.service;

import java.util.List;

import com.isheng.common.base.BaseService;
import com.isheng.core.sys.entity.RoleMenu;
import com.isheng.core.sys.query.RoleMenuQuery;

public interface RoleMenuService extends BaseService<RoleMenu, RoleMenuQuery> {

	/**
	 * 批量插入
	 * @param roleId
	 * @param menuIds
	 * @throws BizException
	 */
	void batchAdd(String roleId, List<String> menuIds);
	
	/**
	 * 根据角色id获取所有角色和权限对应关系
	 * @param roleId
	 * @return
	 * @throws BizException
	 */
	List<RoleMenu> getListByRoleId(String roleId) ;

}
