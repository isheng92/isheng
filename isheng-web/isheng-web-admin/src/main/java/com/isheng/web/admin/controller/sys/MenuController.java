package com.isheng.web.admin.controller.sys;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.dubbo.config.annotation.Reference;
import com.isheng.common.enums.ErrMsg;
import com.isheng.common.exception.BizException;
import com.isheng.common.model.Page;
import com.isheng.common.model.ResultModel;
import com.isheng.common.util.ObjUtil;
import com.isheng.core.sys.entity.Menu;
import com.isheng.core.sys.pojo.SessionUser;
import com.isheng.core.sys.query.MenuQuery;
import com.isheng.core.sys.service.MenuService;
import com.isheng.web.admin.controller.AbstractBaseController;

@Controller
@RequestMapping("/menu")
public class MenuController extends AbstractBaseController {
	
	private static final String PATH_PREFIX = "/sys/menu/";
	
	@Reference
	private MenuService menuService;
	
	/**
	 * 权限管理首页
	* @Title: index
	* @param @return    
	* @return String    
	* @throws
	 */
	@RequestMapping("/index")
	public String index() {
		return PATH_PREFIX + "menuIndex";
	}
	
	@RequestMapping("/list")
	public ModelAndView list(MenuQuery menuQuery, @RequestParam(defaultValue="1")String pageNo, @RequestParam(defaultValue="10")String pageSize) {
		ModelAndView mv = new ModelAndView();
		Page<Menu> page = menuService.getPaging(menuQuery, Integer.valueOf(pageNo), Integer.valueOf(pageSize));
		mv.addObject("result", page);
		mv.setViewName(PATH_PREFIX + "menuList");
		return mv;
	}
	
	@ResponseBody
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public Object add(Menu menu) {
		ResultModel result = this.dataValid(menu);
		if (!result.isSuccess()) {
			return result;
		}
		
//		SessionUser sessionUser = getCurrentUser();
//		menu.setCreateUser(sessionUser.getUserId());
		
		try {
			String id = menuService.add(menu);
			if (StringUtils.isBlank(id)) {
				return result.setResult(ErrMsg.FAILED);
			}
			return result.addData("id", id);
		} catch (BizException e) {
			logger.error("菜单添加异常,menu={},exception={}", menu, e);
			return result.setResult(e);
		}
	}
	
	@ResponseBody
	@RequestMapping("/delete")
	public Object delete(String id) {
		ResultModel result = new ResultModel();
		if (StringUtils.isBlank(id)) {
			return result.setResult(ErrMsg.PARAM_NULL);
		}
		
		try {
			int count = menuService.deleteById(id);
			if (count <= 0) {
				return result.setResult(ErrMsg.FAILED);
			}
			
			return result.setResult(ErrMsg.SUCCESS);
		} catch (BizException e) {
			logger.error("菜单删除失败,id={},exception={}", id, e);
			return result.setResult(e);
		} 
		
	}
	
	@ResponseBody
	@RequestMapping("/detail")
	public Object detail(String id) {
		ResultModel result = new ResultModel();
		try {
			Menu menu = menuService.getById(id);
			if (null == menu) {
				return result.setResult(ErrMsg.FAILED);
			}
			return result.setResult(ErrMsg.SUCCESS).addData("menu", menu);
		}  catch (BizException e) {
			logger.error("菜单查询失败,id={},exception={}", id, e);
			return result.setResult(e);
		} 
	}
	
	@ResponseBody
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public Object update(Menu menu) {
		ResultModel result = this.dataValid(menu);
		if (!result.isSuccess()) {
			return result;
		}
		
//		SessionUser user = getCurrentUser();
//		menu.setUpdateUser(user.getUserId());
		
		try {
			int count = menuService.update(menu);
			if (count <= 0) {
				return result.setResult(ErrMsg.FAILED);
			}
			return result;
		} catch (BizException e) {
			logger.error("菜单更新异常,menu={},exception={}", menu, e);
			return result.setResult(e);
		} 
	}
	
	/**
	 * 获取下一个排序
	 */
	@ResponseBody
	@RequestMapping(value = "/nextSort")
	public Object nextSort(final String parentId) {
		ResultModel result = new ResultModel();
		
		try {
			long sort = menuService.getNextSort(parentId);
			if (sort <= 0) {
				sort = 1;
			}
			return result.setResult(ErrMsg.SUCCESS).addData("sort", sort);
		}  catch (BizException e) {
			logger.error("菜单排序号获取异常:parentId={}", parentId);
			return result.setResult(e);
		}
	}
	
	@Override
	protected  ResultModel dataValid(Object object) {
		ResultModel result = new ResultModel();
		if (null == object) {
			return result.setResult(ErrMsg.PARAM_NULL);
		}
		Menu data = (Menu)object;
		
		if (ObjUtil.isNull(data.getName())) {
			return result.setCode(ErrMsg.PARAM_MISS.getCode()).setMsg("名称不能为空");
		}
		return result.setResult(ErrMsg.SUCCESS);
	}

}
