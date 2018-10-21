package com.isheng.core.sys.query;

import com.isheng.common.base.BaseQuery;

public class MenuQuery extends BaseQuery {

	/**  */
	private static final long serialVersionUID = 215677083216021544L;

	private String name;

	private String code;

	private String url;

	private String parentId;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	
}
