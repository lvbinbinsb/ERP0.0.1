package cn.erp.service;

import java.util.List;

import cn.erp.dto.MenuVo;
import cn.erp.pojo.TblMenu;
import cn.erp.util.PageResult;

public interface MenuService {

	/**
	 * 获取所有菜单
	 */
	List<MenuVo> selAllmenu(PageResult pageResult);

	/**
	 * 获取所有一级菜单
	 */
	List<TblMenu> getAllOneLevel(Long uuid);

	List<TblMenu> selectList();
}
