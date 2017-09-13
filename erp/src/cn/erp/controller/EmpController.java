package cn.erp.controller;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cn.erp.dto.EmpDep;
import cn.erp.pojo.TblDep;
import cn.erp.pojo.TblEmp;
import cn.erp.pojo.TblEmpRole;
import cn.erp.pojo.TblRole;
import cn.erp.service.DepService;
import cn.erp.service.EmpService;
import cn.erp.service.RoleService;
import cn.erp.util.session.Constants;
import cn.erp.util.session.SessionProvider;

/**
 * 雇员
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value="/login")
public class EmpController {

	@Autowired
	private EmpService empService;
	@Autowired
	private SessionProvider sessionProvider;
	@Autowired
	private DepService depService;
	@Autowired
	private RoleService roleService;
	
	//FIXME
	@RequestMapping(value = "/login.do")
	public String login(){
		return "login";
	}
	
	//FIXME
	@RequestMapping(value = "/gologin.do")
	public String goLogin(String username,String pwd,ModelMap model,HttpServletRequest request,HttpServletResponse response)throws Exception{
		if(StringUtils.isNotBlank(username) && StringUtils.isNotBlank(pwd)){
			TblEmp tblEmp = empService.goLogin(username, pwd);
			if(tblEmp != null){
				//把用户对象放在Session
				sessionProvider.setAttribute(request, response, Constants.tblEmp_SESSION, tblEmp);
				return "main";
			}else{
				model.addAttribute("error", "用户名或密码错误");
			}
		}else{
			model.addAttribute("error", "用户名或密码不能为空");
		}
		return "login";
	}
	
	
	@RequestMapping(value = "/page.do")
	public String page(){
		return "context";
	}
	
	
	//FIXME(跳转雇员列表 )
	//到雇员列表
	@RequestMapping(value = "/emp.do")
	public String empList(ModelMap model,TblEmp emp) throws Exception{
		List<TblDep> deps =null;
		/*if(emp.getDepuuid()!=null){
			deps= depService.getAllDep(emp.getDepuuid());  //查询所有部门
		}else{
			deps= depService.getAllDep(null);
		}*/
		deps= depService.getAllDep(null);
		model.addAttribute("deps", deps);
		//List<TblEmp> emps = empService.selEmp(username,name,tele,gender,depUuid);
		List<EmpDep> emps = empService.getEmps(emp);//待修改动态sql
		model.addAttribute("emps", emps);
		
		model.addAttribute("curemp", emp);
		return "employee/list";
	}
	
	
	
	//到新增员工页面
	@RequestMapping(value ="/toadd.do")
	public String toAdd(ModelMap model) throws Exception{
		///查询出所有部门= depService.getAllDep()
		List<TblDep> deps=depService.getAllDep(null);
		List<TblRole> roles=roleService.getRoles();
		model.addAttribute("roles", roles);
		model.addAttribute("deps", deps);
		return "employee/input";
	}
	
	
	
	//保存员工
	@RequestMapping(value ="/save.do")//,Long birthday
	public String save(TblEmp emp,@RequestParam(name="roleId") Long[] roleId) throws Exception{
		//SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
		//long time = dateFormat.parse(birthday).getTime();
		//empService.save(username, pwd, name, email, tele, gender, address, depId, time,roleId);
		//emp.setBirthday(time);
		empService.saveMy(emp,roleId);
		
		return "redirect:/login/emp.do";
	}
	
	
	//FIXME(跳转修改员工页面)
	//区修改员工//待修改,role
	@RequestMapping(value="/toupdate.do")
	public String toupdate(ModelMap model,Long uuid){
		///查询出所有部门depService.getAllDep(uuid)
		List<TblDep> deps = depService.getAllDep(null);
		EmpDep info = empService.selectEmpAndDepByEmpId(uuid);
		//所有角色
		List<TblRole> roles=roleService.getRoles();
		//员工id的到对应角色
		List<TblEmpRole> rolist = empService.selEmpAndRoleById(uuid);
		
		model.addAttribute("rolist", rolist);
		model.addAttribute("roles", roles);
		model.addAttribute("info", info);
		model.addAttribute("deps", deps);
		return "employee/edit";
	}
	
	//FIXME
	//修改员工信息
	@RequestMapping(value="/update.do")
	@SuppressWarnings("all")
	public String update(ModelMap model, EmpDep emp,
			HttpServletRequest request,HttpServletResponse response,Long[] roleId) throws Exception{
		//SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
		// long time = dateFormat.parse(birthday).getTime();
		// boolean b = empService.updateEmp(uuid,username,name,email,tele,gender,address,depId,time,roleId);
		boolean b=empService.updateEmpMy(emp,roleId);
		if(b){
			return "redirect:/login/emp.do";
		}else{
			return "redirect:/myerror.jsp";
		}
		
	}
	
	
	//取消到list页面
	@RequestMapping(value ="/quit.do",method=RequestMethod.GET)
	public String quit(ModelMap model){
		List<EmpDep> emps = null;//empService.getEmps();
		model.addAttribute("emps", emps);
		return "redirect:/login/emp.do";
	}
	
	//查询
	/*@RequestMapping(value="/select.do")
	public String selEmp(ModelMap model,String username,String name,String tele,Integer gender,Long depUuid) throws Exception{
		List<TblEmp> emps = empService.selEmp(username,name,tele,gender,depUuid);
		model.addAttribute("emps", emps);
		return "employee/list";
	}*/
	
	//删除
	@RequestMapping(value="/del.do")
	public String  delEmp(Long uuid){
		empService.delEmp(uuid);
		return "redirect:/login/emp.do";
	}

	//到修改密码页面
	@RequestMapping(value="/editPasswordUi.do")
	public String  editPassword(){
		return "employee:/changePwd";
	}
	
	//修改密码
	@RequestMapping(value="/editPwd.do")
	public String  editPwd(HttpServletRequest request,HttpServletResponse response,String newpwd){
		//Serializable attribute = sessionProvider.getAttribute(request, response, pwd);
		Object object = sessionProvider.getAttribute(request, response, Constants.tblEmp_SESSION);
		TblEmp emp=(TblEmp) object;
		//empService.editPwd(pwd,newpwd);
		emp.setPwd(newpwd);
		empService.updateEmpById(emp);
		return "employee/success";
		
	}
	
	//注销
	@RequestMapping(value="/quit.do")
	public void  quit(HttpServletRequest request,HttpServletResponse response){
		sessionProvider.logout(request, response);
	}
	
	
}
