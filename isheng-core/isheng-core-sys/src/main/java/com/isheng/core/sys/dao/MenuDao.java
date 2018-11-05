package com.isheng.core.sys.dao;

import java.util.List;

import com.isheng.common.base.BaseDao;
import com.isheng.common.exception.BizException;
import com.isheng.core.sys.entity.Menu;
import com.isheng.core.sys.query.MenuQuery;

public interface MenuDao extends BaseDao<Menu, MenuQuery> {
	
	/**
	 * 查询用户的根权限
	 * 
	 * @param userId
	 * @return
	 */
	List<Menu> selectRoots(String userId);

	/**
	 * 查询用户的所有权限
	 * 
	 * @param userId
	 * @return
	 * @throws BizException
	 */
	List<Menu> listByUserId(String userId) throws BizException;
}
