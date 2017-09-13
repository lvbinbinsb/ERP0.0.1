package cn.erp.service.impl;

import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.erp.dto.EmpDep;
import cn.erp.mappers.TblEmpMapper;
import cn.erp.mappers.TblEmpRoleMapper;
import cn.erp.pojo.TblEmp;
import cn.erp.pojo.TblEmpExample;
import cn.erp.pojo.TblEmpExample.Criteria;
import cn.erp.pojo.TblEmpRole;
import cn.erp.pojo.TblEmpRoleExample;
import cn.erp.service.EmpService;
@Service
@Transactional
public class EmpServiceImpl implements EmpService{

	@Autowired
	private TblEmpMapper empMapper;
	@Autowired
	private TblEmpRoleMapper empRoleMapper;
	private cn.erp.pojo.TblEmpRoleExample.Criteria createCriteria;
	
	public TblEmp goLogin(String username, String pwd) {
		TblEmpExample example=new TblEmpExample();
		Criteria createCriteria = example.createCriteria();
		createCriteria.andUsernameEqualTo(username);
		createCriteria.andPwdEqualTo(pwd);
		List<TblEmp> list = empMapper.selectByExample(example);
		if(list.size() >0 && list != null){
			return list.get(0);
		}
		return null;
	}
	
	//待修改需要返回员工id
	public void save(String username, String pwd, String name, String email,
			String tele, Integer gender, String address, Long depId,
			long time,Long[] roleId) {
		TblEmp record=new TblEmp();
		record.setUsername(username);
		record.setAddress(address);
		record.setPwd(pwd);
		record.setName(name);
		record.setEmail(email);
		record.setTele(tele);
		record.setGender(gender);
		record.setDepuuid(depId);
		record.setBirthday(time);
		empMapper.insert(record);
		Long uuid = record.getUuid();
		TblEmpRole ex=new TblEmpRole();
		for (Long id : roleId) {
			ex.setRoleuuid(id);
			ex.setEmpuuid(uuid);
			int i = empRoleMapper.insert(ex);
			
		}
	}
	
	public void saveMy(TblEmp emp, Long[] roleId) {
		empMapper.insert(emp);
		Long uuid=emp.getUuid();
		TblEmpRole ex=new TblEmpRole();
		for (Long id : roleId) {
			ex.setRoleuuid(id);
			ex.setEmpuuid(uuid);
			int i = empRoleMapper.insert(ex);
			
		}
		
	}

	public List<EmpDep> getEmps(TblEmp emp) {
		TblEmpExample example=new TblEmpExample();
		
		if(emp!=null){
			Criteria criteria = example.createCriteria();
			if(emp.getDepuuid()!=null&&emp.getDepuuid()!=-1){
				criteria.andDepuuidEqualTo(emp.getDepuuid());
			}
			if(emp.getGender()!=null&&emp.getGender()!=-1){
				criteria.andGenderEqualTo(emp.getGender());
			}
			if(emp.getUsername()!=null&&StringUtils.isNotBlank(emp.getUsername())){
				criteria.andUsernameLike("%"+emp.getUsername()+"%");
			}
			if(emp.getName()!=null&&StringUtils.isNotBlank(emp.getName())){
				criteria.andNameLike("%"+emp.getName()+"%");
			}
			if(emp.getEmail()!=null&emp.getEmail()!=""){
				criteria.andEmailLike(emp.getEmail());
			}
			if(emp.getTele()!=null&emp.getTele()!=""){
				criteria.andTeleLike(emp.getTele()+"%");
			}
		}
		
		return empMapper.selectEmpAndDep(example);
	}

	public EmpDep selectEmpAndDepByEmpId(Long uuid) {
		EmpDep empDepInfo=empMapper.selectEmpAndDepByEmpId(uuid);
		 return empDepInfo;
	}
	
	public void updateEmpById(TblEmp record){
		//TblEmpExample example=new TblEmpExample();
		empMapper.updateByPrimaryKeySelective(record);
	}

	public List<TblEmp> selEmp(String username,String name,String tele,Integer gender,Long depUuid) throws Exception {
		TblEmpExample example=new TblEmpExample();
		Criteria criteria = example.createCriteria();
		if(StringUtils.isNotBlank(username)){
			criteria.andUsernameEqualTo(username);
		}
		if(StringUtils.isNotBlank(name)){
			criteria.andUsernameEqualTo(name);
		}
		if(StringUtils.isNotBlank(tele)){
			criteria.andUsernameEqualTo(tele);
		}
		if(gender != null){
			criteria.andGenderEqualTo(gender);
		}
		if(depUuid != null){
			criteria.andDepuuidEqualTo(depUuid);
		}
		
		List<TblEmp> emps = empMapper.selectByExample(example);
		if(emps.size()>0 && emps != null ){
			return emps;
		}
		return null;
	}

	public void delEmp(Long uuid) {
		empMapper.deleteByPrimaryKey(uuid);
	}
	
	
	//FIXME
	public List<TblEmpRole> selEmpAndRoleById(Long uuid) {
		//List<EmpDep> list = empMapper.selEmpAndRoleById(uuid);
		
		TblEmpRoleExample example=new TblEmpRoleExample();
		cn.erp.pojo.TblEmpRoleExample.Criteria criteria = example.createCriteria();
		criteria.andEmpuuidEqualTo(uuid);
		List<TblEmpRole> list2 = empRoleMapper.selectByExample(example);
		return list2;
	}

	public boolean updateEmp(Long uuid, String username, String name, String email, String tele, Integer gender,
			String address, Long depId, long time, Long[] roleId) {
		TblEmp record=new TblEmp();
		record.setUuid(uuid);
		record.setUsername(username);
		record.setName(name);
		record.setEmail(email);
		record.setTele(tele);
		record.setGender(gender);
		record.setAddress(address);
		record.setDepuuid(depId);
		record.setBirthday(time);
		int j=0;
		 int i = empMapper.updateByPrimaryKey(record);
		 System.err.println(i);
		 List<EmpDep> edList = empMapper.selEmpAndRoleById(uuid);
		 for (EmpDep empDep : edList) {
			Long jueseid = empDep.getJueseid();
			TblEmpRoleExample example=new TblEmpRoleExample();
			cn.erp.pojo.TblEmpRoleExample.Criteria criteria = example.createCriteria();
			criteria.andEmpuuidEqualTo(uuid);
			criteria.andRoleuuidEqualTo(jueseid);
			List<TblEmpRole> list = empRoleMapper.selectByExample(example);
			for (TblEmpRole tblEmpRole : list) {
				Long erUuid = tblEmpRole.getUuid();
				for (Long rId : roleId) {
					TblEmpRole EmpRole=new TblEmpRole();
					EmpRole.setUuid(erUuid);
					EmpRole.setEmpuuid(uuid);
					EmpRole.setRoleuuid(rId);
					   j = empRoleMapper.updateByPrimaryKey(EmpRole);
				}
				
			}
			
		}
		 
		return j==0?false:true;
		
	}
	
	public boolean updateEmpMy(EmpDep emp, Long[] roleId) throws Exception {
		TblEmp te = empMapper.selectByPrimaryKey(emp.getUuid());
		
		TblEmp record=new TblEmp();
		record.setUuid(emp.getUuid());
		record.setUsername(emp.getUsername());
		if(emp.getPwd()!=null||emp.getPwd()!=""){
			record.setPwd(emp.getPwd());
		}else{
			emp.setPwd(te.getPwd());
		}
		record.setName(emp.getName());
		record.setEmail(emp.getEmail());
		record.setTele(emp.getTele());
		record.setGender(emp.getGender());
		record.setAddress(emp.getAddress());
		record.setDepuuid(emp.getDepId());
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		long time=sdf.parse(emp.getBirthday()).getTime();
		record.setBirthday(time);
		
		List<Long> roleIdList=null;
		int j=0;
		 int i = empMapper.updateByPrimaryKey(record);
		//保存角色
		 if(roleId!=null&&roleId.length!=0){
		//增加的关系  取消的关系做删除处理  角色集合
			 
			 TblEmpRoleExample example=new TblEmpRoleExample();
			 example.createCriteria().andEmpuuidEqualTo(emp.getUuid());
			 empRoleMapper.deleteByExample(example);
			 
		for (Long rid : roleId) {
			TblEmpRoleExample example2=new TblEmpRoleExample();
			cn.erp.pojo.TblEmpRoleExample.Criteria criteria = example2.createCriteria();
			criteria.andEmpuuidEqualTo(emp.getUuid());
			criteria.andRoleuuidEqualTo(rid);
			
			//List<TblEmpRole> list = empRoleMapper.selectByExample(example2);
			//if(list==null||list.size()==0){
				//不存在该关系
				TblEmpRole empRole=new TblEmpRole();
				empRole.setEmpuuid(emp.getUuid());
				empRole.setRoleuuid(rid);
				empRole.setUuid(null);
				empRoleMapper.insert(empRole);  //插入该关系
			//}else{
				//该关系已经存在无需改动
				//}
			}
		 }else{
			 //删除所有关系
			 TblEmpRoleExample example=new TblEmpRoleExample();
			 example.createCriteria().andEmpuuidEqualTo(emp.getUuid());
			 empRoleMapper.deleteByExample(example);
			 
		 }
		 
		 
		/* List<EmpDep> edList = empMapper.selEmpAndRoleById(emp.getUuid());
		 for (EmpDep empDep : edList) {    			//遍历用户部门类
			Long jueseid = empDep.getJueseid();   //角色ID   
			TblEmpRoleExample example=new TblEmpRoleExample(); 
			cn.erp.pojo.TblEmpRoleExample.Criteria criteria = example.createCriteria();
			criteria.andEmpuuidEqualTo(emp.getUuid());   //设置用户ID条件
			criteria.andRoleuuidEqualTo(jueseid);			//设置用户ID条件
			List<TblEmpRole> list = empRoleMapper.selectByExample(example);  //用户角色类
			for (TblEmpRole tblEmpRole : list) {
				Long erUuid = tblEmpRole.getUuid();
				for (Long rId : roleId) {
					TblEmpRole EmpRole=new TblEmpRole();
					EmpRole.setUuid(erUuid);
					EmpRole.setEmpuuid(emp.getUuid());
					EmpRole.setRoleuuid(rId);
					   j = empRoleMapper.updateByPrimaryKey(EmpRole);
				}
				
			}
		 }	*/
		 
		 //return j==0?false:true;
		 return true;
	}
	
	public List<TblEmp> getAllEmp() {
		TblEmpExample example=new TblEmpExample();
		List<TblEmp> list = empMapper.selectByExample(example);
		if(list.size()>0 && list != null){
			return list;
		}
		return null;
	}
	public Long selempByName(String glyname) {
		TblEmpExample example=new TblEmpExample();
		Criteria criteria = example.createCriteria();
		if(StringUtils.isNotBlank(glyname)){
			criteria.andNameEqualTo(glyname);
		}
		
		List<TblEmp> emp = empMapper.selectByExample(example);
		TblEmp tblEmp = emp.get(0);
		//管理员uuid
		Long empuuid = tblEmp.getUuid();
		return empuuid;
	}
	public Long selOrderByGdrname(String gdrName) {
		TblEmpExample example=new TblEmpExample();
		Criteria criteria = example.createCriteria();
		criteria.andNameEqualTo(gdrName);
		List<TblEmp> list = empMapper.selectByExample(example);
		if(list.size()>0 && list != null){
			list.get(0).getUuid();
		}
		return null;
	}

	

	

}
