package com.isheng.common.base;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.isheng.common.model.Page;

/**
 * 基础抽象服务类
 *
 *
 * @author Administrator
 * @version $Id: BaseAbstractService.java 2018年9月1日 下午6:38:28 $
 */
public abstract class AbstractBaseService<T, Q extends BaseQuery> implements BaseService<T, Q> {
	
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * 获取dao
	 * 
	 * @return
	 */
	protected abstract BaseDao<T, Q> getDao();
	
	/**
	 * 数据校验
	 * @param t
	 * @throws BizException
	 */
	protected void dataValid(T t) {
		return ;
	}

	@Override
	public String add(T entity) {
		return this.getDao().save(entity);
	}

	@Override
	public int deleteById(String id) {
		return this.getDao().delete(id);
	}

	@Override
	public int update(T entity) {
		return this.getDao().update(entity);
	}

	@Override
	public T getById(String id) {
		return this.getDao().getOne(id);
	}

	@Override
	public long getCount(Q query) {
		return this.getDao().getCount(query);
	}
	
	@Override
	public List<T> getAll() {
		return this.getDao().getAll();
	}

	@Override
	public List<T> getList(Q query) {
		return this.getDao().getList(query);
	}

	@Override
	public Page<T> getPaging(Q query, int pageNo, int pageSize) {
		if (pageNo <= 0) {
			pageNo = 1;
		}

		List<T> list = null;
		long count  = this.getDao().getCount(query);
		if (count >= 1) {
			list = this.getDao().getLimit(query, pageNo, pageSize);
		}
		
		return new Page<T>(list, count, pageNo, pageSize);
		
	}

	@Override
	public boolean isExist(String id, String column, Object value) {
		return this.getDao().isExist(id, column, value);
	}
	
	

}
