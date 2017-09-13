package cn.erp.service;

import java.util.List;

import cn.erp.pojo.TblRole;

public interface RoleService {

	/**
	 * 获取所有角色
	 */
	List<TblRole> getRoles();
	/**
	 * 条件查询
	 */
	List<TblRole> selRole(String name,String code);
	
	TblRole findRole(Long uuid);
	
	public void save(TblRole role);
	
	void deleteRole(Long uuid);
	
	void insert(String name, String code);
	
}
