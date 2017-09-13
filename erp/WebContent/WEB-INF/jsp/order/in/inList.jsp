<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
			<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="/tags" prefix="date"%>
<link href="${pageContext.request.contextPath }/css/index.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/Calendar.js"></script>
<script type="text/javascript">
	$(function() {
		$("#query").click(function() {
			$("[name='pageNum']").val(1);
			$("form:first").submit();
		});
	});
</script>
<div class="content-right">
	<div class="content-r-pic_w">
		<div style="margin:8px auto auto 12px;margin-top:6px">
			<span class="page_title">进货订单管理</span>
		</div>
	</div>
	<div class="content-text">
		<form action="${pageContext.request.contextPath }/order/orderList.do" method="post"> 
			<div class="square-o-top">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					style="font-size:14px; font-weight:bold; font-family:"黑体";">
					<tr>
						<td height="30">订单状态</td>
						<td> 
							<select class="kuan" style="width:113px" name="ordertype">
								<option value="-1">----请-选-择----</option>
								<option value="1">未审核</option>
								<option value="0">已审核</option>
								<option value="0">正在采购</option>
								<option value="0">入库完成</option>
							</select> 
						</td>
						<td>下单人:</td>
						<td><input type="text" size="14" name="creater"/></td>
						<td>总量:</td>
						<td><input type="text" size="14" name="totalnum1"/></td>
						<td>到 </td>
						<td>&nbsp;&nbsp;<input type="text" size="14" name="totalnum2"/></td>
						<td><a id="query"> 
							<img src="${pageContext.request.contextPath }/images/can_b_01.gif" border="0" /> </a>
						</td>
					</tr>
					<tr>
						<td>下单时间:</td>
						<td>
							<input type="text" size="14" onfocus="c.showMoreDay=false;c.show(this);" readonly="true" name="createtime1"/>
						</td>
						<td>到</td>
						<td>
							<input type="text" size="14" onfocus="c.showMoreDay=false;c.show(this);" readonly="true" name="createtime2"/>
						</td>
						<td>总额:</td>
						<td><input type="text" size="14" name="totalprice1"/></td>
						<td>到</td>
						<td>&nbsp;&nbsp;<input type="text" size="14" name="totalprice2"/></td>
						<td>
							<a href="${pageContext.request.contextPath }/order/orderAdd.do">
								<img src="${pageContext.request.contextPath }/images/can_b_02.gif" border="0" /> 
							</a>
						</td>
					</tr>
				</table>
			</div>
			<!--"square-o-top"end-->
			<div class="square-order">
				<table width="100%" border="1" cellpadding="0" cellspacing="0">
					<tr align="center"
						style="background:url(${pageContext.request.contextPath }/images/table_bg.gif) repeat-x;">
						<td width="25%" height="30">订单号</td>
						<td width="9%">供应商</td>
						<td width="10%">制单人</td>
						<td width="20%">制单时间</td>
						<td width="10%">订单商品总量</td>
						<td width="12%">订单总金额</td>
						<td width="5%">详情</td>
						<td width="9%">订单状态</td>
					</tr>
					<c:forEach items="${orderList }" var="orderDto">
					<tr align="center" bgcolor="#FFFFFF">
						<td width="13%" height="30">${orderDto.ordernum }</td>
						<td>${orderDto.gysname }</td>
						<td>${orderDto.zdrname }</td>
						<td>
						<date:date value="${orderDto.createtime }"/>
						</td>
						<td>${orderDto.totalnum }</td>
						<td align="center">${orderDto.totalprice }</td>
						<td>
							<a href="inDetailList.jsp" class="xiu">详情</a>
						</td>
						<td>未审核</td>
					</tr>
					</c:forEach>
				</table>
			</div>
		</form>
	</div>
	<div class="content-bbg"></div>
</div>
