package com.isheng.core.sys.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.alibaba.dubbo.config.annotation.Service;
import com.isheng.common.base.AbstractBaseService;
import com.isheng.common.base.BaseDao;
import com.isheng.common.enums.ErrMsg;
import com.isheng.common.exception.BizException;
import com.isheng.common.util.ObjUtil;
import com.isheng.core.sys.dao.RoleDao;
import com.isheng.core.sys.entity.Role;
import com.isheng.core.sys.query.RoleQuery;
import com.isheng.core.sys.service.RoleService;

@Component("roleService")
@Service(interfaceClass = RoleService.class)
public class RoleServiceImpl extends AbstractBaseService<Role, RoleQuery> implements RoleService {
	
	@Autowired
	private RoleDao roleDao;

	@Override
	protected BaseDao<Role, RoleQuery> getDao() {
		return roleDao;
	}

	@Override
	public String add(Role entity) throws BizException {
		this.dataValid(entity);
		String id = "";
		try {
			id = roleDao.save(entity);
		} catch (Exception e) {
//			throw new BizException(ErrMsg.EXP_ADD, e);
		}
		return id;
	}

	@Override
	public int update(Role entity) throws BizException {
		this.dataValid(entity);
		if (StringUtils.isEmpty(entity.getId())) {
			throw new BizException(ErrMsg.PARAM_MISS.getCode(), "ID不能为空");
		}
		return roleDao.update(entity);
	}
	
	@Override
	public List<Role> getListByUserId(String userId) throws BizException  {
		List<Role> list = null;
		if (ObjUtil.isNotNull(userId)) {
			list = roleDao.listByUserId(userId);
		}
		return list;
	}

	@Override
	protected void dataValid(Role entity) throws BizException {
		if (null == entity) {
//			throw new BizException(ErrMsg.PARAM_NULL);
		}
		if (StringUtils.isEmpty(entity.getName())) {
			throw new BizException(ErrMsg.PARAM_MISS.getCode(), "角色名称不能为空");
		}
		boolean isExist = roleDao.isExist(entity.getId(), "name", entity.getName());
		if (isExist) {
			throw new BizException(ErrMsg.PARAM_ERR.getCode(), "角色名称已存在");
		}
	}

}
