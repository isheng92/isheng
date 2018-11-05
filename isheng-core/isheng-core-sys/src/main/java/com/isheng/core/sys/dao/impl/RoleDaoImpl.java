package com.isheng.core.sys.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.isheng.common.base.AbstractBaseDao;
import com.isheng.common.base.BaseMapper;
import com.isheng.common.exception.BizException;
import com.isheng.core.sys.dao.RoleDao;
import com.isheng.core.sys.dao.mapper.RoleMapper;
import com.isheng.core.sys.entity.Role;
import com.isheng.core.sys.query.RoleQuery;

@Component("roleDao")
@Service(interfaceClass = RoleDao.class)
public class RoleDaoImpl extends AbstractBaseDao<Role, RoleQuery> implements RoleDao {

	@Resource
	private RoleMapper roleMapper;

	@Override
	protected BaseMapper<Role, RoleQuery> getMapper() {
		return roleMapper;
	}

	@Override
	public List<Role> listByUserId(String userId) throws BizException {
		return roleMapper.selectByUserId(userId);
	}
	
}
