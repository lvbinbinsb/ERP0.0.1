<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
		<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="base" value="${pageContext.request.contextPath }"></c:set>
<link href="${base }/css/index.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base }/js/jquery-1.8.3.js"></script>
<script>
$(function(){
	//alert("1")
	var glyuuid=$("#g").val();
	//alert(glyuuid)
	$("option").each(function(i,l){
		if($(l).val()==glyuuid){
			$(l).attr("selected","selected");
		}
	})
	
	
})

function save(){
	var flag=false;
	var empUuid=$("#empUuid").val();
	//alert(empUuid)
	if(empUuid == null){
		alert("请填写管理员");
		return false;
	}else{
		flag=true;
	}
	if(flag){
		$("#e").val(empUuid);
		$("#save").submit();
	}
	
	
}




</script>


<input  id="g"  type="hidden" value="${storeDto.glyuuid }" />
<div class="content-right">
	<div class="content-r-pic_w">
		<div style="margin:8px auto auto 12px;margin-top:6px">
			<span class="page_title">仓库管理</span>
		</div>
	</div>
	<div class="content-text">
		<div class="square-order">
			<form action="${base }/store/edit.do" method="post" id="save">
			<input id="e" name="empuuid" type="hidden">
			<input  id="id"   type="hidden" name="uuid" value="${storeDto.uuid }" />
  			<div style="border:1px solid #cecece;">
				<table width="100%"  border="0" cellpadding="0" cellspacing="0">
				  <tr bgcolor="#FFFFFF">
				    <td>&nbsp;</td>
				  </tr>
				</table>
				<table width="100%"  border="0" cellpadding="0" cellspacing="0">
				    <tr  bgcolor="#FFFFFF">
				      <td width="18%" height="30" align="center">仓库名称</td>
				      <td width="32%">
				      	<input type="text" size="25" id="name" name="name" value="${storeDto.name }"/>
				      </td>
				      <td width="18%" align="center">仓库地址</td>
				      <td width="32%">
				      	<input type="text" size="25" id="address" name="address" value="${storeDto.address }"/>
				      </td>
				    </tr>
				    <tr  bgcolor="#FFFFFF">
				      <td colspan="4">&nbsp;</td>
				    </tr>
				    <tr  bgcolor="#FFFFFF">
				      <td width="18%" height="30" align="center">管理员</td>
				      <td width="32%">
				      	<select style="width:190px" id="empUuid">
								<option value="-1">----请-选-择----</option>
								<c:forEach items="${emps }" var="emp">
								<option value="${emp.uuid }">${emp.name }</option>
								</c:forEach>
								<!-- <option value="2">李四</option> -->
							</select>
				      </td>
				      <td width="18%" align="center">最大容积</td>
				      <td width="32%">
				      	<input type="text"size="22" value="100"/>&nbsp;米<sup>3</sup>
				      </td>
				    </tr>
				    <tr  bgcolor="#FFFFFF">
				      <td colspan="4">&nbsp;</td>
				    </tr>
				</table>
			</div>
			<div class="order-botton">
				<div style="margin:1px auto auto 1px;">
					<table width="100%"  border="0" cellpadding="0" cellspacing="0">
					  <tr>
					    <td>
					    	<a href="javascript:void(0)" onclick="save()"><img src="${base }/images/save.png" border="0" /></a>
					    </td>
					    <td>&nbsp;</td>
					    <td><a href="#"><img src="${base }/images/reload.png" border="0" /></a></td>
					    <td>&nbsp;</td>
					    <td><a href="#"><img src="${base }/images/cancel.png" border="0" /></a></td>
					  </tr>
					</table>
				</div>
			</div>
			</form>
		</div><!--"square-order"end-->
	</div><!--"content-text"end-->
	<div class="content-bbg"><img src="${base }/images/content_bbg.jpg" /></div>
</div>
