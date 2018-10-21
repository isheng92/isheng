package com.isheng.common.base;

import java.util.List;

import com.isheng.common.exception.BizException;

/**
 * 基础dao
 *
 *
 * @author Administrator
 * @version $Id: BaseDao.java 2018年9月1日 下午6:32:09 $
 */
public interface BaseDao<T, Q extends BaseQuery> {
	
	/**
	 * 新增插入
	 * @param entity
	 * @return 数据ID
	 */
	String save(T entity) throws BizException;
	
	/**
	 * 按主键删除
	 * @param id
	 * @return
	 */
	int delete(String id) throws BizException;
	
	/**
	 * 更新
	 * @param entity
	 * @return
	 */
	int update(T entity) throws BizException;
	
	/**
	 * 根据主键获取
	 * @param id
	 * @return
	 */
	T getOne(String id) throws BizException;
	
	/**
	 * 获取满足条件的记录数
	 * @param query
	 * @return
	 */
	long getCount(Q query) throws BizException;
	
	/**
	 * 查询所有
	 * @return
	 * @throws BizException
	 */
	List<T> getAll() throws BizException;
	
	/**
	 * 获取满足条件所有记录
	 * @param query
	 * @return
	 */
	List<T> getList(Q query) throws BizException;
	
	/**
	 * 根据条件分页查询
	 * @param query
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	List<T> getLimit(Q query, int pageNo, int pageSize) throws BizException;
	
	/**
	 * 查询指定的值是否有相同
	 * @param value
	 * @param id
	 * @return
	 */
	boolean isExist(String id, String column, Object value) throws BizException;


}
