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
import com.isheng.core.sys.dao.RoleMenuDao;
import com.isheng.core.sys.entity.RoleMenu;
import com.isheng.core.sys.query.RoleMenuQuery;
import com.isheng.core.sys.service.RoleMenuService;

@Component("roleMenuService")
@Service(interfaceClass = RoleMenuService.class)
public class RoleMenuServiceImpl extends AbstractBaseService<RoleMenu, RoleMenuQuery> implements RoleMenuService {

	@Autowired
	private RoleMenuDao roleMenuDao;
	
	@Override
	protected BaseDao<RoleMenu, RoleMenuQuery> getDao() {
		return roleMenuDao;
	}
	
	@Override
	public String add(RoleMenu entity)  {
		this.dataValid(entity);
		if (roleMenuDao.isRepet(entity.getRoleId(), entity.getMenuId())) {
			throw new BizException(ErrMsg.PARAM_REPET.getCode(), "角色关系已存在");
		}
		try {
			return roleMenuDao.save(entity);
		} catch (Exception e) {
			logger.error("角色权限关系添加异常", e);
//			throw new BizException(ErrMsg.EXP_ADD, e);
		}
		return null;
	}

	@Override
	public int update(RoleMenu entity)  {
		this.dataValid(entity);
		try {
			return roleMenuDao.update(entity);
		} catch (Exception e) {
			logger.error("角色权限关系更新异常", e);
//			throw new BizException(ErrMsg.EXP_UP, e);
		}
		return 0;
	}

	@Override
	public void batchAdd(String roleId, List<String> menuIds)  {
		if (ObjUtil.isNull(roleId)) {
			throw new BizException(ErrMsg.PARAM_MISS.getCode(), "角色ID不能为空");
		}
		if (null == menuIds || menuIds.isEmpty()) {
			throw new BizException(ErrMsg.PARAM_MISS.getCode(), "请至少选择一个权限信息");
		}
		try {
			roleMenuDao.batchSave(roleId, menuIds);
		} catch (Exception e) {
			logger.error("批量添加角色权限关系异常", e);
//			throw new BizException(ErrMsg.EXP_ADD, e);
		}
		
	}

	@Override
	public List<RoleMenu> getListByRoleId(String roleId)  {
		if (ObjUtil.isNull(roleId)) {
			throw new BizException(ErrMsg.PARAM_MISS.getCode(), "角色ID不能为空");
		}
		try {
			return roleMenuDao.listByRoleId(roleId);
		} catch (Exception e) {
			logger.error("查询角色权限关系异常", e);
//			throw new BizException(ErrMsg.EXP_QUERY, e);
		}
		return null;
	}

	@Override
	protected void dataValid(RoleMenu t)  {
		if (null == t) {
//			throw new BizException(ErrMsg.PARAM_NULL);
		}
		if (ObjUtil.isNull(t.getRoleId())) {
			throw new BizException(ErrMsg.PARAM_MISS.getCode(), "角色ID不能为空");
		}
		if (ObjUtil.isNull(t.getMenuId())) {
			throw new BizException(ErrMsg.PARAM_MISS.getCode(), "权限ID不能为空");
		}
	}


}
