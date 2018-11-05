package com.isheng.web.admin.security;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.dubbo.config.annotation.Reference;
import com.isheng.common.constant.Global;
import com.isheng.core.sys.entity.Menu;
import com.isheng.core.sys.entity.User;
import com.isheng.core.sys.pojo.SessionUser;
import com.isheng.core.sys.service.MenuService;
import com.isheng.web.admin.common.ShiroUtil;
import com.isheng.web.admin.security.domain.UserToken;

/**
 * 自定义权限认证、授权器
 *
 * @author isheng92
 * @version $Id: CustomRealm.java 2018年10月31日 下午10:58:26 $
 */
public class CustomRealm extends AuthorizingRealm {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Reference
	private MenuService menuService;

	/**
	 * 授权，进行鉴权但缓存中无用户的授权信息时调用
	 * 
	 * @see org.apache.shiro.realm.AuthorizingRealm#doGetAuthorizationInfo(org.apache.shiro.subject.PrincipalCollection)
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		String userId = (String) principals.fromRealm(getName()).iterator().next();
		if (StringUtils.isEmpty(userId)) {
			return null;
		}
		
		SimpleAuthorizationInfo info = (SimpleAuthorizationInfo) ShiroUtil.getSessionAttr(Global.PERMISSIONS);
		if (null != info) {
			return info;
		}
		
		info = new SimpleAuthorizationInfo();
		List<Menu> menuList = new ArrayList<>();//menuService.listByUserId(userId);
		if (null != menuList && !menuList.isEmpty()) {
			for (Menu m : menuList) {
				info.addStringPermission(m.getCode());
			}
			ShiroUtil.setSessionAttr(Global.SESSION_USER_KEY, info);
		}
		
		return info;
	}

	/**
	 * 认证，登陆时调用
	 * 
	 * @see org.apache.shiro.realm.AuthenticatingRealm#doGetAuthenticationInfo(org.apache.shiro.authc.AuthenticationToken)
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		UserToken userToken = (UserToken) token;
		User user = userToken.getUser();
		logger.info("账户登录，账户:{},被踢出", user.getLoginName());
		////// TODO 从缓存中删除user session
		SessionUser sessionUser = new SessionUser();
		sessionUser.setLoginName(user.getLoginName());
		sessionUser.setMobile(user.getMobile());
		sessionUser.setNick(user.getNick());
		sessionUser.setRealName(user.getRealName());
		sessionUser.setUserId(user.getId());
		
		ShiroUtil.setSessionAttr(Global.SESSION_USER_KEY, sessionUser);
		// 放入缓存中
		logger.info("账户登录,账户:{}, sessionId:{}", user.getLoginName(), SecurityUtils.getSubject().getSession().getId());

		return new SimpleAuthenticationInfo(user.getId(), userToken.getPassword(), getName());
	}

}
