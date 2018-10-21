package com.isheng.core.sys.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.isheng.common.base.AbstractBaseService;
import com.isheng.common.base.BaseDao;
import com.isheng.core.sys.dao.MenuDao;
import com.isheng.core.sys.entity.Menu;
import com.isheng.core.sys.query.MenuQuery;
import com.isheng.core.sys.service.MenuService;

/**
 * 菜单服务，使用dubbo的service暴漏出service
 *
 * @author isheng92
 * @version $Id: MenuServiceImpl.java 2018年10月21日 下午11:06:26 $
 */
@Component("menuService")
@Service(interfaceClass = MenuService.class)
public class MenuServiceImpl extends AbstractBaseService<Menu, MenuQuery> implements MenuService {
	
	@Autowired
	private MenuDao menuDao;

	@Override
	protected BaseDao<Menu, MenuQuery> getDao() {
		return menuDao;
	}

	
}
