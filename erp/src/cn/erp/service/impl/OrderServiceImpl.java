package cn.erp.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import cn.erp.dto.OrderDetailDto;
import cn.erp.dto.OrderDto;
import cn.erp.dto.OrderNumPriceDto;
import cn.erp.dto.SelOrderByTimeAndCreaterDto;
import cn.erp.mappers.TblOrderMapper;
import cn.erp.pojo.TblDep;
import cn.erp.pojo.TblDepExample;
import cn.erp.pojo.TblOrder;
import cn.erp.pojo.TblOrderExample;
import cn.erp.pojo.TblOrderExample.Criteria;
import cn.erp.service.OrderService;
import cn.erp.util.PageResult;


@Service
public class OrderServiceImpl implements OrderService{

	
	@Autowired
	private TblOrderMapper orderMapper;

	public List<TblOrder> getAllOrder() {
		TblOrderExample example=new TblOrderExample();
		List<TblOrder> list = orderMapper.selectByExample(example);
		if(list.size()>0 && list != null){
			return list;
		}
		return null;
	}

	public List<OrderDto> selOrderByOrderNum(Long uuid) {
		List<OrderDto> list = orderMapper.selOrderByOrderNum(uuid);
		if(list != null && list.size()>0){
			return list;
		}
		return null;
	}

	public List<OrderDetailDto> selOrderDetailById(Long uuid) {
		List<OrderDetailDto> list = orderMapper.selOrderDetailById(uuid);
		if(list.size()>0 && list != null ){
			return list;
		}
		return null;
	}

	public List<OrderNumPriceDto> selNumAndPriceById(Long uuid) {
		List<OrderNumPriceDto> list = orderMapper.selNumAndPriceById(uuid);
		if(list.size()>0 && list != null){
			return list;
		}
		return null;
	}

	public List<SelOrderByTimeAndCreaterDto> selOrderByTimeAndCreater(Long createtime,Long createtimeend,Long zdrname,PageResult pageResult) {
 HashMap<String, Object> map =new HashMap<String, Object>();
		 
		 
		 TblOrderExample example=new TblOrderExample(); //总记录数 
		
		 int count =orderMapper.countByExample(example);
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
		 
		 List<SelOrderByTimeAndCreaterDto> list = orderMapper.selOrderByTimeAndCreater(pageResult.getBeginNo(),pageResult.getPageSize(),zdrname, createtime, createtimeend);
		 
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

	public List<OrderDto> selOrder(String creater, Long createtime1, Long createtime2, Integer ordertype,
			Integer totalnum1, Integer totalnum2, Double totalprice1, Double totalprice2) {
	//根据页面信息查找
		
		TblOrderExample example=new TblOrderExample();
		Criteria criteria = example.createCriteria();
		if(creater!=null&&creater!=""){
			
		}
		
		if(createtime1!=null&&createtime1!=0l){
			criteria.andCreatetimeGreaterThanOrEqualTo(createtime1);
		}
		
		if(createtime2!=null&&createtime2!=0l){
			criteria.andCreatetimeLessThanOrEqualTo(createtime2);
		}
		
		if(ordertype!=null&&ordertype!=-1){
			criteria.andOrdertypeEqualTo(ordertype);
		}
		
		if(totalnum1!=null&&totalnum1!=0){
			criteria.andTotalnumGreaterThanOrEqualTo(totalnum1);
		}
		
		if(totalnum2!=null&&totalnum2!=0){
			criteria.andTotalnumLessThanOrEqualTo(totalnum2);
		}
		
		if(totalprice1!=null&&totalprice1!=0d){
			criteria.andTotalpriceGreaterThanOrEqualTo(totalprice1);
		}
		
		if(totalprice2!=null&&totalprice2!=0d){
			criteria.andTotalpriceLessThanOrEqualTo(totalprice2);
		}
		
		List<OrderDto> list = orderMapper.selectMy(example);
		return list;
	}
	
	
	
	
}
