package com.isheng.web.admin.controller;

import com.isheng.common.base.BaseController;
import com.isheng.core.sys.pojo.SessionUser;
import com.isheng.web.admin.common.ShiroUtil;

public abstract class AbstractBaseController extends BaseController {
	
	protected SessionUser getCurrentUser() {
		return ShiroUtil.getCurrentUser();
	}

}
