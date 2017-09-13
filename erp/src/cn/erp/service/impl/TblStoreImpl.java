package cn.erp.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import cn.erp.dto.OperDetailsDto;
import cn.erp.dto.OrdernumAndGdr;
import cn.erp.dto.StoreByGlynameGoodsname;
import cn.erp.dto.StoreDto;
import cn.erp.mappers.TblEmpMapper;
import cn.erp.mappers.TblStoreMapper;
import cn.erp.mappers.TblStoredetailMapper;
import cn.erp.pojo.TblDep;
import cn.erp.pojo.TblDepExample;
import cn.erp.pojo.TblStore;
import cn.erp.pojo.TblStoreExample;
import cn.erp.service.TblStoreService;
import cn.erp.util.PageResult;
@Service
public class TblStoreImpl implements TblStoreService{

	@Autowired 
	TblStoreMapper tblStoreMapper;
	@Autowired
	TblEmpMapper empMapper;
	@Autowired
	TblStoredetailMapper storedetailMapper;
	
	public TblStore getTblStore(Long id) {
		return tblStoreMapper.selectByPrimaryKey(id);
	}

	//
	public List<StoreDto> selAllStore(Long empuuid,String name) {
		
		List<StoreDto> list = tblStoreMapper.selAllStore(empuuid,name);
		if(list != null && list.size()>0){
			return list;
		}
		return null;
	}

	public void save(Long empUuid, String name, String address) {
		TblStore record=new TblStore();
		record.setAddress(address);
		record.setName(name);
		record.setEmpuuid(empUuid);
		tblStoreMapper.insertSelective(record);
	}

	public StoreDto selStoreById(Long uuid) {
		StoreDto list = tblStoreMapper.selStoreById(uuid);
		if(list != null){
			return list;
		}
		return null;
	}

	public TblStore selByStoreById(Long uuid) {
		TblStore store = tblStoreMapper.selectByPrimaryKey(uuid);
		return store;
	}

	public void edit(Long empUuid, Long uuid, String name, String address) {
		TblStore record=new TblStore();
		record.setAddress(address);
		record.setName(name);
		record.setUuid(uuid);
		record.setEmpuuid(empUuid);
		int i = tblStoreMapper.updateByPrimaryKeySelective(record);
		System.out.println(i);
		
	}
	
	public void edit(TblStore store) {
		 tblStoreMapper.updateByPrimaryKeySelective(store);
	}

	public void delete(Long uuid) {
		int i = tblStoreMapper.deleteByPrimaryKey(uuid);
		System.out.println(i);
	}

	public List<StoreByGlynameGoodsname> selStoreByGlynameGoodsname(
			String storeName, String glyName, String goodsName,PageResult pageResult) {
		HashMap<String, Object> map =new HashMap<String, Object>();
		 
		 
		 TblStoreExample example=new TblStoreExample(); //总记录数 
		 //Criteria criteria = example.createCriteria();
		 //int count =depMapper.countByExample(example); 
		 int count=tblStoreMapper.countByExample(example);
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
		 
		 List<StoreByGlynameGoodsname> list = tblStoreMapper.selStoreByGlynameGoodsname(storeName, glyName, goodsName,pageResult.getPageNo(),pageResult.getPageSize());
		 pageResult.setItems(list);
		 if(count%pageResult.getPageSize()==0){
			 pageResult.setTotalPageCount(count/pageResult.getPageSize());
		 }else{
			 pageResult.setTotalPageCount(count/pageResult.getPageSize()+1);
		 }
		 
		 ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();  
	     HttpServletRequest request = attributes.getRequest();
	     request.getSession().setAttribute("PageResult", pageResult);
		
		if(list.size()>0 && list != null){
			return list;
		}
		return null;
	}

	public List<OrdernumAndGdr> selOrdernumAndGdr(Long empUuid, String orderNum) {
		List<OrdernumAndGdr> list=tblStoreMapper.selOrdernumAndGdr(empUuid,orderNum);
		if(list.size()>0 &&list != null){
			return list;
		}
		return null;
	}

	public List<OperDetailsDto> selOperDetails(String ckname, Integer cztype, Long starttime, Long endtime, Long empuuid,
			String goodsname) {
		List<OperDetailsDto> list = storedetailMapper.selOperDetails(ckname, cztype, starttime, endtime, empuuid, goodsname);
		if(list.size()>0 && list != null){
			return list;
		}
		return null;
	}

	public void save(TblStore store) {
		tblStoreMapper.insertSelective(store);
		
	}

}
