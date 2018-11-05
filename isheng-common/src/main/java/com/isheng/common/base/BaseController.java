package com.isheng.common.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.isheng.common.model.ResultModel;

public abstract class BaseController {
	
	/**
	 * 日志对象
	 */
	protected Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 数据验证
	 * 
	 * @param object
	 * @return
	 */
	protected ResultModel dataValid(Object object) {
		return new ResultModel();
	};

}
