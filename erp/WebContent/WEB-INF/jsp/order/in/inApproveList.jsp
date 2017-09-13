<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link href="../../../css/index.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/Calendar.js"></script>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<script type="text/javascript">
	$(function() {
		$("#query").click(function() {
			//$("[name='pageNum']").val(1);
			$("form:first").submit();
		});
	});
</script>
<div class="content-right">
	<div class="content-r-pic_w">
		<div style="margin:8px auto auto 12px;margin-top:6px">
			<span class="page_title">进货审核</span>
		</div>
	</div>
	<div class="content-text">
		<form action="${pageContext.request.contextPath }/order/orderCheck.do" method="post"> 
			<div class="square-o-top">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					style="font-size:14px; font-weight:bold; font-family:"黑体";">
					<tr>
						<td  height="30">下单时间:</td>
						<td>
							<input type="text" size="14" onfocus="c.showMoreDay=false;c.show(this);" readonly="true" name="createtimes"/>
						</td>
						<td>到</td>
						<td>
							&nbsp;&nbsp;<input type="text" size="14" onfocus="c.showMoreDay=false;c.show(this);" readonly="true" name="createtimeends"/>
						</td>
						<td>总量:</td>
						<td><input type="text" size="14" /></td>
						<td>到 </td>
						<td>&nbsp;&nbsp;<input type="text" size="14" /></td>
					</tr>
					<tr>
						<td>总额:</td>
						<td><input type="text" size="14" /></td>
						<td>到</td>
						<td>&nbsp;&nbsp;<input type="text" size="14" /></td>
						<td>下单人:</td>
						<td><input type="text" size="14" name="glyname"/></td>
						<td>&nbsp;</td>
						<td>&nbsp;&nbsp;<a id="query"> 
							<img src="${pageContext.request.contextPath }/images/can_b_01.gif" border="0" /> </a>
						</td>
					</tr>
				</table>
			</div>
			<!--"square-o-top"end-->
			<div class="square-order" style="overflow-y:scroll; overflow-x:scroll;">
				<table width="100%" border="1" cellpadding="0" cellspacing="0">
					<tr align="center"
						style="background:url(${pageContext.request.contextPath }/images/table_bg.gif) repeat-x;">
						<td width="30%" height="10">订单号</td>
						<td width="12%">供应商</td>
						<td width="10%">制单人</td>
						<td width="20%">制单时间</td>
						<td width="10%">订单商品总量</td>
						<td width="12%">订单总金额</td>
						<td width="6%">审核</td>
					</tr>
					<!-- <tr align="center" bgcolor="#FFFFFF">
						<td width="13%" height="30">2389fd8af93</td>
						<td>七匹狼</td>
						<td>张三</td>
						<td>2014-2-12</td>
						<td>100</td>
						<td align="right">400.00  元</td>
						<td>
							<a href="./inApprove.jsp">审核</a>
						</td>
					</tr> 
					<tr align="center" bgcolor="#FFFFFF">
						<td width="13%" height="30">2389fd8af93</td>
						<td>七匹狼</td>
						<td>张三</td>
						<td>2014-2-12</td>
						<td>100</td>
						<td align="right">400.00  元</td>
						<td>
							<span style="color:red">已审核</span>
						</td>
					</tr>-->
					<c:forEach items="${orderDtos}" var="orderDto">
					<tr align="center" bgcolor="#FFFFFF">
						<td width="13%" height="10">${orderDto.ordernum}</td>
						<td>${orderDto.gysname}</td>
						<td>${orderDto.zdrname}</td>
						<td>${orderDto.createtime}</td>
						<td>${orderDto.num}</td>
						<td align="right">${orderDto.totalprice} 元</td>
						<td>
							<a href="${pageContext.request.contextPath }/order/inApprove.do?uuid=${orderDto.uuid}">审核</a>
						</td>
					</tr>
					</c:forEach>
					
					
				</table>
				<table width="100%" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td width="51%">&nbsp;</td>
						<td width="13%">共${PageResult.totalCount}条记录
						<td width="6%">
							<a id="fir" class="sye" href="${pageContext.request.contextPath  }/order/orderCheck.do?beginNo=0&pageSize=${PageResult.pageSize}&pageNo=1">首&nbsp;&nbsp;页</a>
						</td>
						<!-- 分页先注释以后完成 -->
						
						<td width="6%">
						<c:choose>
							<c:when test="${PageResult.beginNo==0}">
								<a href="#" disabled="disabled"  style="background: #eee;">上一页</a>
							</c:when>
						
							
							<c:otherwise>
								<a href="${pageContext.request.contextPath  }/order/orderCheck.do?beginNo=${PageResult.beginNo-PageResult.pageSize}&pageSize=${PageResult.pageSize}&pageNo=${PageResult.pageNo-1}">上一页</a>
							</c:otherwise>
						</c:choose>	
						</td>
						
						
					
						<td width="6%">
						<c:choose>
						<c:when test="${PageResult.pageNo >= PageResult.totalPageCount}">
								<a href="#" disabled="disabled" style="background: #eee;">下一页</a>
							</c:when>
						<c:otherwise>
								<a href="${pageContext.request.contextPath }/order/orderCheck.do?beginNo=${PageResult.beginNo+PageResult.pageSize}&pageSize=${PageResult.pageSize}&pageNo=${PageResult.pageNo+1}">下一页</a>
							</c:otherwise>
						</c:choose>  
							<!-- <a id="next" class="sye">下一页</a> -->
						</td>
						
						<td width="6%">
							<a id="last" class="sye" href="${pageContext.request.contextPath  }/order/orderCheck.do?beginNo=${(pageResult.totalPageCount-1)*pageResult.pageSize}&pageSize=${PageResult.pageSize}&pageNo=${pageResult.totalPageCount}">末&nbsp;&nbsp;页</a>
						</td>
						<td width="12%">当前第<span style="color:red;">${PageResult.pageNo }</span>/${ PageResult.totalPageCount }页</td>
					</tr>
				</table>
			</div>
		</form>
	</div>
	<div class="content-bbg"></div>
</div>
