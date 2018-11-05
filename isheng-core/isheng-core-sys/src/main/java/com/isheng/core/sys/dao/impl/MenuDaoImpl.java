package com.isheng.core.sys.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.isheng.common.base.AbstractBaseDao;
import com.isheng.common.base.BaseMapper;
import com.isheng.common.exception.BizException;
import com.isheng.core.sys.dao.MenuDao;
import com.isheng.core.sys.dao.mapper.MenuMapper;
import com.isheng.core.sys.entity.Menu;
import com.isheng.core.sys.query.MenuQuery;

@Repository(value = "menuDao")
public class MenuDaoImpl extends AbstractBaseDao<Menu, MenuQuery> implements MenuDao {
	
	@Autowired
	private MenuMapper menuMapper;

	@Override
	protected BaseMapper<Menu, MenuQuery> getMapper() {
		return menuMapper;
	}

	@Override
	public List<Menu> listByUserId(String userId) throws BizException {
		return menuMapper.selectByUserId(userId);
	}

	@Override
	public List<Menu> selectRoots(String userId) {
		return menuMapper.selectRoots(userId);
	}
}
