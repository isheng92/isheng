package com.isheng.core.sys.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.isheng.common.base.BaseMapper;
import com.isheng.common.exception.BizException;
import com.isheng.core.sys.entity.UserRole;
import com.isheng.core.sys.query.UserRoleQuery;

/**
 * 用户角色
 * @author Administrator
 *
 */
@Mapper
@Repository
public interface UserRoleMapper extends BaseMapper<UserRole, UserRoleQuery> {
	
	/**
	 * 根据用户ID查询
	 * @param userId
	 * @return
	 */
	List<UserRole> selectByUserId(@Param("userId")String userId) throws BizException;
	
	/**
	 * 是否重复添加用户和角色关系
	 * 
	 * @param userId
	 * @param roleId
	 * @return
	 * @throws BizException
	 */
	int isRepet(@Param("userId")String userId, @Param("roleId")String roleId) throws BizException;

}
