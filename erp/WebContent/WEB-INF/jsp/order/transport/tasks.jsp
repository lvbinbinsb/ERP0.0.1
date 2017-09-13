<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
			<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<link href="${pageContext.request.contextPath }/css/index.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
<script type="text/javascript">
	
	function query() {
		var flag=false;
		var uuid=$("#uuid").val();
		var needs=$("#needs").val();
		if(uuid == null){
			alert("正确填写供应商");
			return false;
		}else{
			flag=true;
		}
		if(needs==null){
			alert("选择提货方式");
			return false;
		}else{
			flag=true;
		}
		if(flag){
			$("#u").val(uuid);
			$("#n").val(needs);
			$("#form").submit();
		}
		
		
	}
	
	
	
</script>
<div class="content-right">
	<div class="content-r-pic_w">
		<div style="margin:8px auto auto 12px;margin-top:6px">
			<span class="page_title">任务查询</span>
		</div>
	</div>
	<div class="content-text">
		<form action="${pageContext.request.contextPath }/goodsTransport/goodsTransportQuery.do" method="post" id="form"> 
		<input name="uuid" id="u">
		<input name="needs" id="n">
			<div class="square-o-top">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					style="font-size:14px; font-weight:bold; font-family:"黑体";">
					<tr>
						<td width="8%">供&nbsp;应&nbsp;商:</td>
						<td width="29%">
							<select style="width:137px" id="uuid">
								<option value="-1">----请-选-择----</option>
								<c:forEach items="${sup}" var="sup">
							<option value="${sup.uuid }">${sup.name }</option>
							</c:forEach>
							</select>
						</td>
						<td width="8%">发货方式:</td>
						<td width="45%">
							<select style="width:137px" id="needs">
								<option value="-1">----请-选-择----</option>
								<option value="1">送货</option>
								<option value="0">自提</option>
							</select>
						</td>
						<td width=""><a id="query" onclick="query()"> 
							<img src="${pageContext.request.contextPath }/images/can_b_01.gif" border="0" /> </a>
						</td>
					</tr>
				</table>
			</div>
			<!--"square-o-top"end-->
			<div class="square-order">
				<table width="100%" border="1" cellpadding="0" cellspacing="0">
					<tr align="center"
						style="background:url(${pageContext.request.contextPath }/images/table_bg.gif) repeat-x;">
						<td width="8%" height="30">订单类别</td>
						<td width="11%">供应商</td>
						<td width="7%">发货方式</td>
						<td width="6%">联系人</td>
						<td width="12%">联系方式</td>
						<td width="40%">地址</td>
						<td width="8%">状态(废弃该字段)</td>
						<td width="6%">详情</td>
					</tr>
					<c:forEach items="${Suppliers }" var="Supplier">
						<tr align="center" bgcolor="#FFFFFF">
							<td height="30">采购(废弃该字段)</td>
							<td>${Supplier.name }</td>
							<td>${Supplier.needs==1?'送货':'自提' }</td>
							<td>${Supplier.concat }</td>
							<td>${Supplier.tele }</td>
							<td align="left">&nbsp;${Supplier.address }</td>
							<td>正在采购</td>
							<td>
								<a href="${base }/goodsTransport/taskDetail.do?orderuuid=${Supplier.orderuuid }">
									<img src="${pageContext.request.contextPath }/images/icon_3.gif" />详情
								</a>
							</td>
						</tr>
						</c:forEach>
				</table>
			</div>
		</form>
	</div>
	<div class="content-bbg"></div>
</div>
