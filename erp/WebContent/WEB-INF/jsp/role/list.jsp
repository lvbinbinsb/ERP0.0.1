<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
		<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="base" value="${pageContext.request.contextPath }"></c:set>
<link href="${base }/css/index.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="${base}/js/Calendar.js"></script>
<script type="text/javascript">
	$(function(){
	
	
	
	
	});
	
	
</script>
<div class="content-right">
	<div class="content-r-pic_w">
		<div style="margin:8px auto auto 12px;margin-top:6px">
			<span class="page_title">角色管理</span>
		</div>
	</div>
	<div class="content-text">
		<form action="${base}/role/rolelist.do" method="post" id="forms"> 
			<div class="square-o-top">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					style="font-size:14px; font-weight:bold; font-family:"黑体";">
					<tr>
						<td width="68" height="30">&nbsp;</td>
						<td width="123">&nbsp;</td>
						<td width="62">角色名称</td>
						<td width="142"><input type="text" size="18"  name="name" value="${name }"/></td>
						<td width="60">角色编码</td>
						<td width="149"><input type="text" size="18" name="code" value="${code }"/></td>
						<td width="70"><a href="javascript:query();" id="query"><img src="${base }/images/can_b_01.gif" border="0" /> </a></td>
						<script>
						function query(){
							//$("[name='pageNum']").val(1);
							$("form:first").submit();
						}						
						</script>
						<td width="70"><a href="${base}/role/toAddRole.do "><img src="${base }/images/can_b_02.gif" border="0" /></a></td>
					</tr>
				</table>
			</div>
			<!--"square-o-top"end-->
			<div class="square-order">
				<table width="100%" border="1" cellpadding="0" cellspacing="0">
					<tr align="center"
						style="background:url(${base }/images/table_bg.gif) repeat-x;">
						<td width="40%" height="30">角色名称</td>
						<td width="40%">角色编码</td>
						<td width="20%">操作</td>
					</tr>
					<c:forEach items="${roles}" var="role">
					<tr align="center" bgcolor="#FFFFFF">
						<td height="30">${role.name }</td>
						<td>${role.code }</td>
						<td>
							<img src="${base }/images/icon_3.gif" /> 
							<span style="line-height:12px; text-align:center;"> 
								<a href="${base}/role/toEditRole.do?uuid=${role.uuid}" class="xiu">修改</a>
							</span> 
							<img src="${base }/images/icon_04.gif" /> 
							<span style="line-height:12px; text-align:center;"> 
								<a href="javascript:void(0)" class="xiu" onclick="showMsg('是否删除该项数据？',${role.uuid})">删除</a>
								<script type="text/javascript">
								function showMsg(msg,uuid){
									top.$('context-msg').style.display = "block";
									top.$('context-msg-text').innerHTML=msg;
									top.$('hid-action').value="${base}/role/delete.do?uuid="+${role.uuid};
									top.lock.show();
								}
								</script>
							</span>
						</td>
					</tr>
					</c:forEach>
				</table>
			</div>
		</form>
	</div>
	<div class="content-bbg"></div>
</div>
