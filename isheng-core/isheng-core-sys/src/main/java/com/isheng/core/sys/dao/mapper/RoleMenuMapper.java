package com.isheng.core.sys.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.isheng.common.base.BaseMapper;
import com.isheng.common.exception.BizException;
import com.isheng.core.sys.entity.RoleMenu;
import com.isheng.core.sys.query.RoleMenuQuery;
/**
 * 角色权限
 * @author Administrator
 *
 */
@Mapper
@Repository
public interface RoleMenuMapper extends BaseMapper<RoleMenu, RoleMenuQuery> {
	
	/**
	 * 根据角色id获取所有角色和权限对应关系
	 * @param roleId
	 * @return
	 * @throws BizException
	 */
	List<RoleMenu> selectByRoleId(@Param("roleId")String roleId) throws BizException;
	
	/**
	 * 角色和权限关系是否重复
	 * 
	 * @param roleId
	 * @param menuId
	 * @return
	 * @throws BizException
	 */
	int isRepet(@Param("roleId")String roleId, @Param("menuId")String menuId) throws BizException;

}
