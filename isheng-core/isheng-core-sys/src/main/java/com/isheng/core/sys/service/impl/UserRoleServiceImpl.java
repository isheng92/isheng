package com.isheng.core.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.alibaba.dubbo.config.annotation.Service;
import com.isheng.common.base.AbstractBaseService;
import com.isheng.common.base.BaseDao;
import com.isheng.common.enums.ErrMsg;
import com.isheng.common.exception.BizException;
import com.isheng.common.util.ObjUtil;
import com.isheng.core.sys.dao.UserRoleDao;
import com.isheng.core.sys.entity.UserRole;
import com.isheng.core.sys.query.UserRoleQuery;
import com.isheng.core.sys.service.UserRoleService;

@Component("userRoleService")
@Service(interfaceClass = UserRoleService.class)
public class UserRoleServiceImpl extends AbstractBaseService<UserRole, UserRoleQuery> implements UserRoleService {
	
	@Autowired
	private UserRoleDao userRoleDao;
	
	@Override
	protected BaseDao<UserRole, UserRoleQuery> getDao() {
		return userRoleDao;
	}

	@Override
	public String add(UserRole entity) throws BizException {
		this.dataValid(entity);
		if (userRoleDao.isRepet(entity.getUserId(), entity.getRoleId())) {
			throw new BizException(ErrMsg.PARAM_REPET.getCode(), "用户角色关系已存在");
		}
		try {
			return userRoleDao.save(entity);
		} catch (Exception e) {
			logger.error("用户角色分配异常, UserRole={}", entity);
//			throw new BizException(ErrMsg.EXP_ADD, e);
		}
		return null;
	}

	@Override
	public int update(UserRole entity) throws BizException {
		this.dataValid(entity);
		try {
			return userRoleDao.update(entity);
		} catch (Exception e) {
			logger.error("用户角色分配更新异常, UserRole={}", entity);
//			throw new BizException(ErrMsg.EXP_UP, e);
		}
		return 0;
	}

	@Override
	public void batchAdd(String userId, List<String> roleIds) throws BizException {
		if (ObjUtil.isNull(userId)) {
			throw new BizException(ErrMsg.PARAM_MISS.getCode(), "用户ID不能为空");
		}
		if (null == roleIds || roleIds.isEmpty()) {
			throw new BizException(ErrMsg.PARAM_MISS.getCode(), "请至少选择一个角色信息");
		}
		
		try {
			userRoleDao.batchSave(userId, roleIds);
		} catch (Exception e) {
			logger.error("用户角色关系批量添加异常", e);
//			throw new BizException(ErrMsg.EXP_ADD, e);
		}
		
	}

	@Override
	public List<UserRole> getListByUserId(String userId) throws BizException {
		List<UserRole> list = null;
		if (ObjUtil.isNotNull(userId)) {
			list = userRoleDao.listByUserId(userId);
		}
		return list;
	}

	@Override
	protected void dataValid(UserRole t) throws BizException {
		if (null == t) {
//			throw new BizException(ErrMsg.PARAM_NULL);
		}
		if (ObjUtil.isNull(t.getRoleId())) {
			throw new BizException(ErrMsg.PARAM_MISS.getCode(), "角色ID不能为空");
		}
		if (ObjUtil.isNull(t.getUserId())) {
			throw new BizException(ErrMsg.PARAM_MISS.getCode(), "用户ID不能为空");
		}
	}

}
