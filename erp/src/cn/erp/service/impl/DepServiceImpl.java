package cn.erp.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.erp.dto.DepListDto;
import cn.erp.mappers.TblDepMapper;
import cn.erp.pojo.TblDep;
import cn.erp.pojo.TblDepExample;
import cn.erp.pojo.TblDepExample.Criteria;
import cn.erp.service.DepService;
import cn.erp.util.PageResult;

@Service
public class DepServiceImpl implements DepService {

	@Autowired
	private TblDepMapper depMapper;

	public DepListDto getDeps(int page, int rows) throws Exception {
		// 分页处理
		PageHelper.startPage(page, rows);
		TblDepExample example = new TblDepExample();
		List<TblDep> list = depMapper.selectByExample(example);
		PageInfo<TblDep> pageInfo = new PageInfo<TblDep>(list);
		DepListDto depListDto = new DepListDto();
		depListDto.setTotal(pageInfo.getTotal());
		depListDto.setRows(list);

		return depListDto;
	}

	public void addDep(String name, String tele) throws Exception {
		TblDep record = new TblDep();
		record.setTele(name);
		record.setName(tele);
		depMapper.insert(record);
	}

	public void delDep(Long uuid) throws Exception {
		depMapper.deleteByPrimaryKey(uuid);
	}

	public TblDep updateDepById(Long uuid) throws Exception {
		return depMapper.selectByPrimaryKey(uuid);
	}

	public void updateDep(String name, String tele, Long uuid) {
		TblDep record = new TblDep();
		record.setUuid(uuid);
		record.setName(name);
		record.setTele(tele);
		depMapper.updateByPrimaryKeySelective(record);
	}

	public List<TblDep> getAllDep(Long depUuid) {
		TblDepExample example = new TblDepExample();
		if(depUuid!=null){
			Criteria criteria = example.createCriteria();
			criteria.andUuidEqualTo(depUuid);
		}
		
		List<TblDep> list = depMapper.selectByExample(example);
		return list;
	}
	
	

	@SuppressWarnings("static-access") // startpage0,endpage //,int pageNo,int
										// pageSize
	public List<TblDep> selDep(String name, String tele,PageResult pageResult) {
		/*
		 * TblDepExample example=new TblDepExample(); Criteria criteria =
		 * example.createCriteria(); if(StringUtils.isNotBlank(name)){
		 * criteria.andNameEqualTo(name); } if(StringUtils.isNotBlank(tele)){
		 * criteria.andTeleEqualTo(tele); } List<TblDep> list =
		 * depMapper.selectByExample(example); if(list != null &&list.size()>0){
		 * return list; }
		 */
		
		/** * 分页查询先注释 */
		
		
		 
		 //Criteria criteria = example.createCriteria();
		 int count =depMapper.selListPageCount(name, tele);
		 String str=new String(); 
		 String  valueOf = str.valueOf(count); 
		 long tatalcount =Long.parseLong(valueOf); 
		 pageResult.setTotalCount(tatalcount);
		 //list数据 List<TblDep>
		 if(pageResult.getPageNo()==0){
			 pageResult.setPageNo(1);
		 }
		 
		 if(pageResult.getPageSize()==0){
			 pageResult.setPageSize(10);
		 }
		 
		 if(pageResult.getBeginNo()==0){
			 pageResult.setBeginNo((pageResult.getPageNo()-1)*pageResult.getPageSize());
		 }
		 
		 	
		 List<TblDep> list = depMapper.selListPage(pageResult.getBeginNo(),pageResult.getPageSize(),name,tele);
		 pageResult.setItems(list);
		 if(count%pageResult.getPageSize()==0){
			 pageResult.setTotalPageCount(count/pageResult.getPageSize());
		 }else{
			 pageResult.setTotalPageCount(count/pageResult.getPageSize()+1);
		 }
		 
		 ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();  
	     HttpServletRequest request = attributes.getRequest();
	     request.getSession().setAttribute("PageResult", pageResult);
		return list;
	}

	// 分页查询
	public PageResult getPageResult(int pageNO, int pageSize) {
		// depMapper.getPageResult(pageNO,pageSize);
		return null;
	}

	/*
	 * public List<TblDep> selDep(String name, String tele) { // TODO
	 * Auto-generated method stub return null; }
	 */

}
