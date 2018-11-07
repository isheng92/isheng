package com.isheng.web.admin.controller;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.alibaba.dubbo.config.annotation.Reference;
import com.isheng.common.constant.Global;
import com.isheng.common.enums.ErrMsg;
import com.isheng.common.exception.BizException;
import com.isheng.common.model.ResultModel;
import com.isheng.common.util.WebUtil;
import com.isheng.core.sys.entity.Menu;
import com.isheng.core.sys.pojo.SessionUser;
import com.isheng.core.sys.service.MenuService;
import com.isheng.core.sys.service.UserService;
import com.isheng.web.admin.common.ShiroUtil;

/**
 * 首页
 *
 *
 * @author Administrator
 * @version $Id: IndexController.java 2018年9月8日 下午8:16:32 $
 */
@Controller
public class IndexController extends AbstractBaseController {
	
	private static final String PREFIX_PATH = "/common";
	
	@Reference
	private UserService userService;
	@Reference
	private MenuService menuService;
	
	/**
	 * 首页
	 * 
	 * @return
	 */
	@RequestMapping(value = {"/index", "/"})
	public String index(HttpServletRequest request) {
		String gto = request.getParameter(Global.GOTO_KEY);
		if (StringUtils.isEmpty(gto) || "null".equalsIgnoreCase(gto)) {
			gto = "index";
		}
		return gto;
	}
	
	/**
	 * 获取用户的所有菜单或者下级菜单
	 * 
	 * @param userId
	 */
	@SuppressWarnings("unchecked")
	private List<Menu> loadUserMenu(String parentId) {
		List<Menu> menus = WebUtil.getSessionAttr(Global.MENU_ALL_KEY, List.class);
		if (null == menus || menus.isEmpty()) {
			SessionUser sessionUser = getCurrentUser();
			menus = menuService.getMenuTree(null);//menuService.getMenuTree(sessionUser.getUserId());
			WebUtil.setSessionAttr(Global.MENU_ALL_KEY, menus);
		}
		
		if (!StringUtils.isEmpty(parentId)) {
			for (Menu m : menus) {
				if (parentId.equals(m.getId())) {
					menus = m.getChildList();
					break;
				}
			}
		}
		
		return (null != menus ) ? menus : new ArrayList<>(0);
	}
	
	/**
	 * 获取用户根目录
	 * 
	 * @return
	 */
	@RequestMapping("/loadRoot")
	public String loadRootMenu(Model model) {
		List<Menu> roots = this.loadUserMenu(null);
		model.addAttribute("menuList", roots);
		return PREFIX_PATH + "/header-nav";
	}
	
	/**
	 * 获取指定ID的下级菜单
	 * 
	 * @return
	 */
	@RequestMapping("/loadMenu")
	public String loadMenu(Model model, String parentId) {
		if (StringUtils.isEmpty(parentId)) {
			throw new BizException("父级菜单ID为空！");
		}
		List<Menu> menus = this.loadUserMenu(parentId);
		model.addAttribute("menuList", menus);
		return PREFIX_PATH + "/left";
	}
	
	/**
	 * 登出
	 * 
	 * @return
	 */
	@RequestMapping("/logout")
	public Object logout() {
		ResultModel result = new ResultModel();
		ShiroUtil.logout();
		return result.setCode(ErrMsg.SUCCESS.getCode()).setMsg("退出成功").addData(Global.GOTO_KEY, "index");
	}
	
}
