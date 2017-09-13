package cn.erp.controller;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.erp.dto.MenuVo;
import cn.erp.pojo.TblEmp;
import cn.erp.pojo.TblMenu;
import cn.erp.service.MenuService;
import cn.erp.util.PageResult;
import cn.erp.util.session.SessionProvider;

/**
 * 菜单
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value="/menu")
public class MenuController {

	
	@Autowired
	private MenuService menuService;
	@Autowired
	private SessionProvider sessionProvider;
	
	
	//显示菜单
	@RequestMapping(value = "/showMenu.do")
	public void showMenu(HttpServletResponse response,HttpServletRequest request) throws Exception{
		response.setContentType("text/html;charset=utf-8");
		//返回一个json数组
		StringBuilder json=new StringBuilder();
		//sessionProvider.getAttribute(request, response, Constants.tblEmp_SESSION);
		TblEmp tblEmp=new TblEmp();
		Long uuid = tblEmp.getUuid();
		json.append("[");
		//获取所有一级菜单加载
		List<TblMenu> menuList = menuService.getAllOneLevel(uuid);
		for (TblMenu temp : menuList) {
			json.append("{\"text\":\"");
			json.append(temp.getName());
			json.append("\",\"hasChildren\":false,\"classes\":\"file\"},");
			
		}
		json.deleteCharAt(json.length()-1);
		json.append("]");
		PrintWriter pw = response.getWriter();
		pw.write(json.toString());
		pw.flush();
		//pw.close();
		
		
	}
	
		//菜单列表页面
		@RequestMapping(value = "/menuList.do")
		public String menuList(ModelMap model,PageResult pageResult){
				List<MenuVo> menuList = menuService.selAllmenu(pageResult);
				model.addAttribute("menuList", menuList);
				
				//获取下拉属性
				List<TblMenu> list=menuService.selectList();
				model.addAttribute("dictList",list);
			return "menu/list";
		}
		
		
		
		
		
		
		
		
		
		
		
}
