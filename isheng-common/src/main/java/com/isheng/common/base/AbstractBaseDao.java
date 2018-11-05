package com.isheng.common.base;

import java.util.Date;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import com.isheng.common.idgen.IdGenerate;
import com.isheng.common.util.ReflexUtil;

public abstract class AbstractBaseDao<T, Q extends BaseQuery> implements BaseDao<T, Q> {
	
	/**
	 * 不同的dao中传递不同的mapper
	 * 
	 * @return
	 */
	protected abstract BaseMapper<T, Q> getMapper();

	@Override
	public String save(T entity) {
		String id = IdGenerate.nextId();
		ReflexUtil.setFieldValue(entity, "id", id);//通过反射设置id
		ReflexUtil.setFieldValue(entity, "createTime", new Date());//通过反射设置createTime
		int result = this.getMapper().insert(entity);
		return (result >= 1) ? id : "";
	};

	@Override
	public int delete(String id) {
		return this.getMapper().delete(id);
	}

	@Override
	public int update(T entity){
		ReflexUtil.setFieldValue(entity, "updateTime", new Date());
		return this.getMapper().update(entity);
	}

	@Override
	public T getOne(String id) {
		return this.getMapper().selectOne(id);
	}

	@Override
	public long getCount(Q query) {
		return this.getMapper().selectCount(query);
	}
	
	@Override
	public List<T> getAll() {
		return this.getMapper().selectAll();
	}

	@Override
	public List<T> getList(Q query) {
		return this.getMapper().selectList(query);
	}

	@Override
	public List<T> getLimit(Q query, int pageNo, int pageSize) {
		return this.getMapper().selectLimit(query, pageNo, pageSize);
	}

	@Override
	public boolean isExist(String id, String column, Object value) {
		if (StringUtils.isEmpty(id)) {
			id = "";
		}
		return this.getMapper().isExist(id, column, value) >= 1;
	}

}
