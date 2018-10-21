package com.isheng.core.sys.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.isheng.common.base.AbstractBaseDao;
import com.isheng.common.base.BaseMapper;
import com.isheng.core.sys.dao.MenuDao;
import com.isheng.core.sys.entity.Menu;
import com.isheng.core.sys.mapper.MenuMapper;
import com.isheng.core.sys.query.MenuQuery;

@Repository(value = "menuDao")
public class MenuDaoImpl extends AbstractBaseDao<Menu, MenuQuery> implements MenuDao {
	
	@Autowired
	private MenuMapper menuMapper;

	@Override
	protected BaseMapper<Menu, MenuQuery> getMapper() {
		return menuMapper;
	}
}
