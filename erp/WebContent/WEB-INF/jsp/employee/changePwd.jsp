<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<link href="${pageContext.request.contextPath }/css/index.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
<script type="text/javascript">
function tijiao(){
var flag=false;
   var newpwd=$("#newpwd").val()
	var newpw=$("#newpw").val()
	if(newpwd != newpw){
		alert("二次密码不一致");
		return false;
	}else {
		flag=true;
	}

   if(flag){
	   $("#forms").submit();
   }
}
</script>



<div class="content-right">
	<div class="content-r-pic_w">
		<div style="margin:8px auto auto 12px;margin-top:6px">
			<span class="page_title">修改密码</span>
		</div>
	</div>
	<div class="content-text">
		<div class="square-order">
			<form action="${pageContext.request.contextPath }/login/editPwd.do" method="post" id="forms">
  			<div style="border:1px solid #cecece;">
				<table width="100%"  border="0" cellpadding="0" cellspacing="0">
				  <tr bgcolor="#FFFFFF">
				    <td>&nbsp;</td>
				  </tr>
				</table>
				<table width="100%"  border="0" cellpadding="0" cellspacing="0">
					<tr  bgcolor="#FFFFFF">
				      <td align="center">原始密码</td>
				      <td colspan="3">
				      	<input type="text" size="25" name="pwd" id="pwd" value="${tblEmp_session.pwd }"/>
				      </td>
				    </tr>
				    <tr bgcolor="#FFFFFF">
					  <td colspan="4">&nbsp;</td>
					</tr>
					<tr  bgcolor="#FFFFFF">
				      <td align="center">新&nbsp;密&nbsp;码</td>
				       <td colspan="3">
				      	<input type="password" size="25" name="newpwd" id="newpwd"/>
				      </td>
				    </tr>
				    <tr bgcolor="#FFFFFF">
					  <td colspan="4">&nbsp;</td>
					</tr>
					<tr  bgcolor="#FFFFFF">
				      <td align="center">确认密码</td>
				       <td colspan="3">
				      	<input type="password" size="25" name="newpw" id="newpw"/>
				      </td>
				    </tr>
				    <tr bgcolor="#FFFFFF">
					  <td colspan="4">&nbsp;</td>
					</tr>
				</table>
			</div>
			<div class="order-botton">
				<div style="margin:1px auto auto 1px;">
					<table width="100%"  border="0" cellpadding="0" cellspacing="0">
					  <tr>
					    <td>
					    	<a href="javascript:tijiao();"><img src="${pageContext.request.contextPath }/images/save.png" border="0" /></a>
					    </td>
					    <td>&nbsp;</td>
					    <td><a href="#"><img src="${pageContext.request.contextPath }/images/reload.png" border="0" /></a></td>
					    <td>&nbsp;</td>
					    <td><a href="#"><img src="${pageContext.request.contextPath }/images/cancel.png" border="0" /></a></td>
					  </tr>
					</table>
				</div>
			</div>
			</form>
		</div><!--"square-order"end-->
	</div><!--"content-text"end-->
	<div class="content-bbg"><img src="../../images/content_bbg.jpg" /></div>
</div>
