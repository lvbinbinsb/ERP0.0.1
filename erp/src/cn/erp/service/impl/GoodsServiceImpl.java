package cn.erp.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import cn.erp.dto.ExcelGoodsByTimeAndGys;
import cn.erp.dto.ExcelOrderNumById;
import cn.erp.dto.GoodsDto;
import cn.erp.dto.GoodsTransportDto;
import cn.erp.dto.GoodsTymeNameAndGoodsName;
import cn.erp.dto.InfoByOrderuuid;
import cn.erp.dto.SupplierByNeedsDto;
import cn.erp.mappers.TblGoodsMapper;
import cn.erp.mappers.TblGoodstypeMapper;
import cn.erp.pojo.TblDep;
import cn.erp.pojo.TblDepExample;
import cn.erp.pojo.TblGoods;
import cn.erp.pojo.TblGoodsExample;
import cn.erp.pojo.TblGoodsExample.Criteria;
import cn.erp.pojo.TblGoodstype;
import cn.erp.service.GoodsService;
import cn.erp.util.ExcelUtil;
import cn.erp.util.PageResult;

@Service
public class GoodsServiceImpl implements GoodsService{

	
	@Autowired
	private TblGoodsMapper goodsMapper;
	@Autowired TblGoodstypeMapper goodstypeMapper;
	
	public List<GoodsDto> selGoods(Long gysId,String name,String producer,String unit,PageResult pageResult) {
		HashMap<String, Object> map =new HashMap<String, Object>();
		 
		 
		 TblGoodsExample example=new TblGoodsExample(); //总记录数 
		 //Criteria criteria = example.createCriteria();
		 int count =goodsMapper.countByExample(example); 
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
		 
		 List<GoodsDto> list = goodsMapper.selGoods(gysId, name, producer, unit, pageResult.getBeginNo(), pageResult.getPageSize());
		 pageResult.setItems(list);
		 if(count%pageResult.getPageSize()==0){
			 pageResult.setTotalPageCount(count/pageResult.getPageSize());
		 }else{
			 pageResult.setTotalPageCount(count/pageResult.getPageSize()+1);
		 }
		 
		 ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();  
	     HttpServletRequest request = attributes.getRequest();
	     request.getSession().setAttribute("PageResult", pageResult);
		
		
		
		if(list != null && list.size()>0){
			return list;
		}
		return null;
	}

	public void addGoods(Long supplier, Long goodstype, String name,
			String origin, String producer, String unit, Double inPrice,
			Double outPrice, Integer useNum) {
		TblGoodstype gt = goodstypeMapper.selectByPrimaryKey(goodstype);
		
			TblGoodstype r=new TblGoodstype();
			r.setName(gt.getName());
			r.setSupplieruuid(supplier);
			//需要返回供应商类别主键
			int j = goodstypeMapper.insert(r);
			Long goodsTypeUuid = r.getUuid();
			if(j>0){
				//商品添加
				TblGoods record=new TblGoods();
				record.setGoodstypeuuid(goodsTypeUuid);
				record.setName(name);
				record.setOrigin(origin);
				record.setProducer(producer);
				record.setUnit(unit);
				record.setInprice(inPrice);
				record.setOutprice(outPrice);
				record.setUsenum(useNum);
				int i = goodsMapper.insert(record);
				}
		
	}

	public GoodsDto selGoodsById(Long uuid) {
		GoodsDto list = goodsMapper.selGooodsById(uuid);
		return list;
		
	}

	public void updateGoods(Long supplier, Long goodstype, String name, String origin, String producer, String unit,
			Double inPrice, Double outPrice, Integer useNum,Long uuid) {
		//TblGoodstype g = goodstypeMapper.selectByPrimaryKey(goodstype);
		//拿到name
		//String n = g.getName();
		TblGoodstype records=new TblGoodstype();
		records.setUuid(goodstype);
		records.setSupplieruuid(supplier);
		goodstypeMapper.updateByPrimaryKeySelective(records);
		
		TblGoods record=new TblGoods();
		record.setUuid(uuid);
		record.setGoodstypeuuid(goodstype);
		record.setName(name);
		record.setOrigin(origin);
		record.setProducer(producer);
		record.setUnit(unit);
		record.setInprice(inPrice);
		record.setOutprice(outPrice);
		record.setUsenum(useNum);
		goodsMapper.updateByPrimaryKeySelective(record);
	}

	public List<GoodsTransportDto> selRenwuZhipaiLiebiao() {
		List<GoodsTransportDto> list = goodsMapper.selRenwuZhipaiLiebiao();
		if(list.size()>0 && list != null){
			return list;
		}
		return null;
	}

	public List<ExcelGoodsByTimeAndGys> selExcelGoodsByTimeAndGys(Long gysuuid,Long starttime,Long endtime) {
		List<ExcelGoodsByTimeAndGys> list = goodsMapper.selExcelGoodsByTimeAndGys(gysuuid, starttime, endtime);
		if(list.size()>0&&list != null){
			return list;
		}
		return null;
	}

	public List<ExcelOrderNumById> excelDetails(Long goodsuuid) {
		List<ExcelOrderNumById> list=goodsMapper.excelDetails(goodsuuid);
		if(list.size()>0 && list != null){
			return list;
		}
		return null;
	}

	public void exportExcel(List<ExcelGoodsByTimeAndGys> excelGoods,
			ServletOutputStream outputStream) {
		ExcelUtil.exportUserExcel(excelGoods, outputStream);
		
	}

	public List<GoodsTymeNameAndGoodsName> selGoodsTymeNameAndGoodsName(
			Long orderuuid) {
		List<GoodsTymeNameAndGoodsName> list = goodsMapper.selGoodsTymeNameAndGoodsName(orderuuid);
		if(list.size()>0 && list != null){
			return list;
		}
		return null;
	}

	public List<SupplierByNeedsDto> selSupplierByNeeds(Long uuid, Integer needs) {
		List<SupplierByNeedsDto> list=goodsMapper.selSupplierByNeeds(uuid,needs);
		System.err.println(list);
		if(list.size()>0&&list != null){
			return list;
		}
		return null;
	}

	public List<InfoByOrderuuid> selInfoByOrderuuid(Long orderuuid) {
		List<InfoByOrderuuid> list = goodsMapper.selInfoByOrderuuid(orderuuid);
		if(list.size()>0&&list != null){
			return list;
		}
		return null;
	}

	public void deleteGood(Long uuid) {
		goodsMapper.deleteByPrimaryKey(uuid);
		
	}

	public void updateGoods(GoodsDto goods) {
		TblGoodstype records=new TblGoodstype();
		records.setUuid(goods.getGoodstypeuuid());
		records.setSupplieruuid(goods.getGysuuid());
		goodstypeMapper.updateByPrimaryKeySelective(records);
		
		TblGoods record=new TblGoods();
		record.setUuid(goods.getUuid());
		record.setGoodstypeuuid(goods.getGoodstypeuuid());
		record.setName(goods.getName());
		record.setOrigin(goods.getOrigin());
		record.setProducer(goods.getProducer());
		record.setUnit(goods.getUnit());
		record.setInprice(goods.getInprice());
		record.setOutprice(goods.getOutprice());
		record.setUsenum(goods.getUsenum());
		goodsMapper.updateByPrimaryKeySelective(record);
	}

	public void insertGoods(GoodsDto goods) {
		//通过商品类别主键查到改商品
		TblGoodstype gt = goodstypeMapper.selectByPrimaryKey(goods.getGoodstypeuuid());
		
		TblGoodstype r=new TblGoodstype();
		r.setName(gt.getName());
		r.setSupplieruuid(goods.getGysuuid());
		//需要返回供应商类别主键
		int j = goodstypeMapper.insert(r);
		System.out.println("=========="+j);
		Long goodsTypeUuid = r.getUuid();
		if(j>0){
			//商品添加
			TblGoods record=new TblGoods();
			record.setGoodstypeuuid(goodsTypeUuid);
			record.setName(goods.getName());
			record.setOrigin(goods.getOrigin());
			record.setProducer(goods.getProducer());
			record.setUnit(goods.getUnit());
			record.setInprice(goods.getInprice());
			record.setOutprice(goods.getOutprice());
			record.setUsenum(goods.getUsenum());
			int i = goodsMapper.insert(record);
			}
		
	}

	public List<GoodsDto> selGoodsByTypeId(Long gooduuid) {
		
		return goodsMapper.selGooodsByTypeId(gooduuid);
	}

}
