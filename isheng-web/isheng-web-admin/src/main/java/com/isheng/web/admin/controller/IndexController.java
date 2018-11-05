package com.isheng.web.admin.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.dubbo.config.annotation.Reference;
import com.isheng.common.base.BaseController;
import com.isheng.common.constant.Global;
import com.isheng.common.util.WebUtil;
import com.isheng.core.sys.entity.Menu;
import com.isheng.core.sys.pojo.SessionUser;
import com.isheng.core.sys.query.MenuQuery;
import com.isheng.core.sys.service.MenuService;
import com.isheng.core.sys.service.UserService;

/**
 * 首页
 *
 *
 * @author Administrator
 * @version $Id: IndexController.java 2018年9月8日 下午8:16:32 $
 */
@Controller
public class IndexController extends AbstractBaseController {
	
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
	
	@RequestMapping("/loadRootMenu")
	public ModelAndView loadRootMenu() {
		ModelAndView mv = new ModelAndView();
		
//		SessionUser sessionUser = getCurrentUser();
		
		List<Menu> roots = menuService.getRoots(null);//menuService.getRoots(sessionUser.getUserId());
		mv.setViewName("/common/header-nav");
		mv.addObject("menuList", roots);
		return mv;
	}
	
	/**
	 * 加载导航菜单
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/loadMenu")
	public ModelAndView loadMenu(HttpServletRequest req) {
		ModelAndView mv = new ModelAndView();
		List<Menu> list = WebUtil.getSessionAttr(Global.MENU_USER_KEY, List.class);
		if (null == list || list.isEmpty()) {
			list = menuService.getMenuTree(null);//this.initMenu();
		}
		mv.setViewName("/common/left");
		mv.addObject("menuList", list);
		return mv;
	}
	
}
