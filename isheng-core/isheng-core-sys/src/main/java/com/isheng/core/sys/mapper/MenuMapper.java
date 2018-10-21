package com.isheng.core.sys.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.isheng.common.base.BaseMapper;
import com.isheng.core.sys.entity.Menu;
import com.isheng.core.sys.query.MenuQuery;

@Mapper
@Repository
public interface MenuMapper extends BaseMapper<Menu, MenuQuery> {

}
