package com.isheng.core.sys.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.isheng.common.base.BaseMapper;
import com.isheng.common.exception.BizException;
import com.isheng.core.sys.entity.Role;
import com.isheng.core.sys.query.RoleQuery;

@Mapper
@Repository
public interface RoleMapper extends BaseMapper<Role, RoleQuery> {
	
	List<Role> selectByUserId(String userId) throws BizException;

}
