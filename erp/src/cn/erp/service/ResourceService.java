package cn.erp.service;

import java.util.List;

import cn.erp.pojo.TblRes;

public interface ResourceService {
	/**
	 * 获得所有资源列表
	 */
	List<TblRes> selAllResource();

	TblRes selectById(Long uuid);

	void update(TblRes res);

	void delete(Long uuid);

}
