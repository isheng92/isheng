package com.isheng.web.admin.common;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;

import com.isheng.common.constant.Global;
import com.isheng.core.sys.entity.User;
import com.isheng.core.sys.pojo.SessionUser;
import com.isheng.web.admin.security.domain.UserToken;

/**
 * shiro session工具类
 *
 * @author isheng92
 * @version $Id: ShiroUtil.java 2018年10月28日 下午10:33:28 $
 */
public class ShiroUtil {
	
	/**
	 * 得到当前会话
	 * 
	 * @return
	 */
	public static Session getSession() {
		return SecurityUtils.getSubject().getSession();
	}
	
	/**
	 * 获取存放在会话中的对象
	 * 
	 * @param key
	 * @return
	 */
	public static Object getSessionAttr(String key) {
		return getSession().getAttribute(key);
	}
	
	/**
	 * 获取存放在会话中的已知类型的对象
	 * 
	 * @param key
	 * @param clazz
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getSessionAttr(String key, Class<T> clazz) {
		return (T)getSessionAttr(key);
	}
	
	/**
	 * 设置会话属性
	 * 
	 * @param key
	 * @param value
	 */
	public static void setSessionAttr(Object key, Object value) {
		getSession().setAttribute(key, value);
	}
	
	/**
	 * 移除会话属性
	 * 
	 * @param key
	 */
	public static void removeSessionAttr(Object key) {
		getSession().removeAttribute(key);
	}
	
	/**
	 * 获取当前登录的用户
	 * 
	 * @return
	 */
	public static SessionUser getCurrentUser() {
		return getSessionAttr(Global.SESSION_USER_KEY, SessionUser.class);
	}
	
	/**
	 * 退出
	 */
	public static void logout() {
		SecurityUtils.getSubject().logout();
	}
	
	/**
	 * 登录
	 * 
	 * @param user
	 */
	public static void login(User user) {
		SecurityUtils.getSubject().login(new UserToken(user, user.getPwd()));
	}

}
