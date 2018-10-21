package com.isheng.core.sys.entity;

import java.util.List;
import com.isheng.common.base.BaseEntity;

/**
 * 菜单
 *
 *
 * @author Administrator
 * @version $Id: Menu.java 2018年7月21日 上午12:28:54 $
 */
public class Menu extends BaseEntity {

	private static final long serialVersionUID = 5955332391431717453L;

	private String name;

	private String code;

	private String url;

	private String parentId;

	private long sort;
	
	private String remark;
	
	/**********中文展示 ****************/
	/** 页面是否被选中 */
	private boolean checked;
	
	/** 下级菜单 */
	private List<Menu> childList;

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

	public long getSort() {
		return sort;
	}

	public void setSort(long sort) {
		this.sort = sort;
	}
	
	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	
	public List<Menu> getChildList() {
		return childList;
	}

	public void setChildList(List<Menu> childList) {
		this.childList = childList;
	}
	
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	

}
