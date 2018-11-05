package com.isheng.core.sys.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.isheng.common.base.AbstractBaseService;
import com.isheng.common.base.BaseDao;
import com.isheng.common.enums.ErrMsg;
import com.isheng.common.exception.BizException;
import com.isheng.common.util.WebUtil;
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
	
	@Override
	public String add(Menu menu) throws BizException{
		this.dataValid(menu);
		
		String id = "";
		try {
			long nextSort = (menu.getSort() > 0) ? menu.getSort() : this.getNextSort(menu.getParentId());
			String code = WebUtil.paresUri(menu.getUrl());//如：/menu/add改成menu:add
			menu.setSort(nextSort);
			menu.setCode(code);
			id = menuDao.save(menu);
		} catch (Exception e) {
			logger.error("添加菜单失败,menu={}", menu);
			throw new BizException(ErrMsg.EXP_ADD, e);
		}
		
		return id;
	}

	@Override
	public long getNextSort(String parentId)  {
		MenuQuery query = new MenuQuery();
		query.setParentId(parentId);
		return menuDao.getCount(query) + 1;
	}
	
	@Override
	public List<Menu> getRoots(String userId) {
		return menuDao.selectRoots(userId);
	}

	@Override
	public List<Menu> getListByUserId(String userId) throws BizException {
		List<Menu> list = null;
		if (StringUtils.isNotEmpty(userId)) {
			list = menuDao.listByUserId(userId);
		}
		return list;
	}

	@Override
	public List<Menu> getListByRoleId(String roleId) throws BizException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Menu> getMenuTree(String userId) throws BizException {
		List<Menu> menus = this.getAll();//this.getListByUserId(userId);
		if (null == menus || menus.isEmpty()) {
			return new ArrayList<>(0);
		}
		
		return this.buildMenus(menus);
	}
	
	private List<Menu> buildMenus(List<Menu> menus) {
		List<Menu> parentMenus = new ArrayList<>();
		for (Menu m : menus) {
			if (StringUtils.isEmpty(m.getParentId())) {
				parentMenus.add(m);
			} 
		}
		
		for (Menu m : parentMenus) {
			m.setChildList(this.getChilds(m.getId(), menus));
		}
		
		return parentMenus;
	}
	
	private List<Menu> getChilds(String id, List<Menu> menus) {
		List<Menu> childs = new ArrayList<>();
		for (Menu m : menus) {
			if (id.equals(m.getParentId())) {
				childs.add(m);
			} 
		}
		
		//循环子菜单的子菜单
		for (Menu m : childs) {
			if (StringUtils.isEmpty(m.getUrl())) {
				m.setChildList(this.getChilds(m.getId(), menus));
			}
		}
		return childs;
	}
	
	@Override
	protected void dataValid(Menu menu) throws BizException {
		if (null == menu) {
			throw new BizException(ErrMsg.PARAM_NULL);
		}
		if (StringUtils.isEmpty(menu.getName())) {
			throw new BizException(ErrMsg.PARAM_MISS.getCode(), "名称不能为空");
		}
		if (!StringUtils.isEmpty(menu.getUrl())) {
			boolean isExist = menuDao.isExist(menu.getId(), "url", menu.getUrl());
			if (isExist) {
				throw new BizException(ErrMsg.PARAM_ERR.getCode(), "权限地址已存在");
			}
		}
	}

	
}
