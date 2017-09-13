package cn.erp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.erp.dto.GoodstypeDto;
import cn.erp.mappers.TblGoodstypeMapper;
import cn.erp.mappers.TblSupplierMapper;
import cn.erp.pojo.TblGoodstype;
import cn.erp.pojo.TblGoodstypeExample;
import cn.erp.service.GoodstypeService;

@Service
@Transactional
public class GoodstypeServiceImpl implements GoodstypeService{

	@Autowired
	private TblGoodstypeMapper goodstypeMapper;
	@Autowired
	private TblSupplierMapper supplierMapper;
	
	public List<GoodstypeDto> selGoodstype(String name, Long gysname) {
		 List<GoodstypeDto> list=null;
	
			list= goodstypeMapper.selGoodstype(name, gysname);
		
		 if(list.size()>0 && list != null){
			 return list;
		 }
		return null;
		  
	}

	public void addGoodstype(String name, Long supplieruuid) {
		TblGoodstype record=new TblGoodstype();
		record.setName(name);
		record.setSupplieruuid(supplieruuid);
		goodstypeMapper.insert(record);
	}

	public TblGoodstype selById(Long uuid) {
		return goodstypeMapper.selectByPrimaryKey(uuid);
	}

	public void editGoodstype(Long uuid,String name,Long supplieruuid) {
		TblGoodstype record=new TblGoodstype();
		record.setName(name);
		record.setSupplieruuid(supplieruuid);
		record.setUuid(uuid);
		goodstypeMapper.updateByPrimaryKey(record);
		
	}

	public void delete(Long uuid) {
		goodstypeMapper.deleteByPrimaryKey(uuid);
	}

	public List<TblGoodstype> selAll() {
		TblGoodstypeExample example=new TblGoodstypeExample();
		List<TblGoodstype> list = goodstypeMapper.selectByExample(example);
		if(list != null){
			return list;
		}
		return null;
	}

	public void editGoodstype(TblGoodstype goodStyle) {
		goodstypeMapper.updateByPrimaryKey(goodStyle);
		
	}

}
