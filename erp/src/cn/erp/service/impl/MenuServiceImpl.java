package cn.erp.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import cn.erp.dto.MenuVo;
import cn.erp.mappers.TblMenuMapper;
import cn.erp.pojo.TblDep;
import cn.erp.pojo.TblMenu;
import cn.erp.pojo.TblMenuExample;
import cn.erp.service.MenuService;
import cn.erp.util.PageResult;
@Service
public class MenuServiceImpl implements MenuService{

	
	@Autowired
	private TblMenuMapper menuMapper;
	
	public List<MenuVo> selAllmenu(PageResult pageResult) {
		
		TblMenuExample example=new TblMenuExample();
		int count =menuMapper.countByExample(example); 
		
		 String str=new String(); 
		
		 String  valueOf =Integer.toString(count); 
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
		 
		 	
		 List<MenuVo> menuList = menuMapper.selectSonAndFather(pageResult);
		 pageResult.setItems(menuList);
		 if(count%pageResult.getPageSize()==0){
			 pageResult.setTotalPageCount(count/pageResult.getPageSize());
		 }else{
			 pageResult.setTotalPageCount(count/pageResult.getPageSize()+1);
		 }
		 
		 ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();  
	     HttpServletRequest request = attributes.getRequest();
	     request.getSession().setAttribute("PageResult", pageResult);
		
	     
	   
		
		if(menuList != null && menuList.size()>0){
			return menuList;
		}
		return null;
	}

	public List<TblMenu> getAllOneLevel(Long uuid) {
		//Long id=(long) 1;
		TblMenuExample example=new TblMenuExample();
		//Criteria criteria = example.createCriteria();
		//criteria.andPuuidEqualTo(id);
		List<TblMenu> list = menuMapper.selectByExample(example);
		if(list != null){
			return list;
		}
		return null;
	}

	
	public List<TblMenu> selectList() {
		
		TblMenuExample example=new TblMenuExample();
		List<TblMenu> list = menuMapper.selectByExample(example);
		if(list!=null&&list.size()>0){
			return list;
		}
		return null;
	}

}
