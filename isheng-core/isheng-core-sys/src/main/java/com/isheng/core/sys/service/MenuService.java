package com.isheng.core.sys.service;

import java.util.List;

import com.isheng.common.base.BaseService;
import com.isheng.common.exception.BizException;
import com.isheng.core.sys.entity.Menu;
import com.isheng.core.sys.query.MenuQuery;

public interface MenuService extends BaseService<Menu, MenuQuery> {
	
	/**
	 * 获取根权限
	 * 
	 * @param userId
	 * @return
	 */
	public List<Menu> getRoots(String userId);
	
	public long getNextSort(String parentId);
	
	/**
	 * 查询用户的所有权限
	 * @param userId
	 * @return
	 * @throws BizException
	 */
	public List<Menu> getListByUserId(String userId) throws BizException;
	
	/**
	 * 查询指定角色的所有菜单
	 * @param roleId
	 * @return
	 * @throws BizException
	 */
	public List<Menu> getListByRoleId(String roleId) throws BizException;
	
	/**
	 * 
	 * 得到某个用户的权限数
	 * @param userId
	 * @return
	 * @throws BizException
	 */
	public List<Menu> getMenuTree(String userId) throws BizException;
	

}
