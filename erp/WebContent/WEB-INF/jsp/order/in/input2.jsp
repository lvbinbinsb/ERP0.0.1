<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
		<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="base" value="${pageContext.request.contextPath}"></c:set>
<link href="${base }/css/index.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base }/js/jquery-1.8.3.js"></script>
</script>
<script type="text/javascript">
	 function intToFloat(val){
		return new Number(val).toFixed(2);
	}
	//修改供应商
	$(document).ready(function() {
		
		//获取商品种类列表
		$(".kuan").change(function(obj){
			var uuid=$(this).val();
			$.post("${pageContext.request.contextPath}/order/findGoodstype.do",{"gysuuid":uuid},function(data){
				//alert(0);
				$(".goodsType").empty();
				if(data==null ||data.length==0){
					var $option = $("<option value='-1'>--暂无--</option>");
					$(".goodsType").append($option);
				}else{
				$.each(data,function(i,n){
					//alert(n.name);
					var $option = $("<option value='"+n.uuid+"'>"+n.name+"</option>");	//创建option对象
					$(".goodsType").append($option);
				});
				}
			},'json');
			
			$.post("${pageContext.request.contextPath}/order/findGood.do",{"gooduuid":$(".kuan option:selected").val()},function(data){
				$(".goods").empty();
				if(data==null ||data.length==0){
					var $option = $("<option value='-1'>--暂无--</option>");	//创建option对象
					$(".goods").append($option);
				}else{
				$.each(data,function(i,n){
					//alert(n.name);
					var $option = $("<option value='"+n.uuid+"'>"+n.name+"</option>");	//创建option对象
					$(".goods").append($option);
					
				});
				}
			},'json');
			
			
			});
		
		
		//获取商品列表
		$(".goodsType").change(function(obj){
			var uuid=$(this).val();
			//alert(uuid);获取商品种类ID成功
			$.post("${pageContext.request.contextPath}/order/findGood.do",{"gooduuid":uuid},function(data){
				$(".goods").empty();
				
				if(data==null ||data.length==0){
					var $option = $("<option value='-1'>--暂无--</option>");	//创建option对象
					$(".goods").append($option);
				}else{
				$.each(data,function(i,n){
					
					var $option = $("<option value='"+n.uuid+"'>"+n.name+"</option>");	//创建option对象
					$(".goods").append($option);
					
				});
				}
			},'json');
			
		});
		
		});
		
	
	
		
	
</script>
<div class="content-right">
	<div class="content-r-pic_w">
		<div style="margin:8px auto auto 12px;margin-top:6px">
			<span class="page_title">订单管理</span>
		</div>
	</div>
	<div class="content-text">
		<form action="inList.jsp" method="post">
			<div class="square-o-top">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					style="font-size:14px; font-weight:bold; font-family:"黑体";">
					<tr>
						<td height="24">&nbsp;</td>
					</tr>
					<tr>
						<td width="68px" height="30">供应商：</td>
						<td width="648px">
							 <select class="kuan" style="width:190px" name="gysuuid">
								<!-- <option value="1">七匹狼</option>
								<option value="0">康师傅</option> -->
								<c:forEach items="${supplierAll}" var="supplierAll">
							<option  value="${supplierAll.uuid }"  >${supplierAll.name }</option>
							</c:forEach>
							</select>
						</td>
						
						
						
						
						
						<td height="30">
							<a id="add"><img src="${base }/images/can_b_02.gif" border="0" /> </a>
						</td>
					</tr>
				</table>
			</div>
			<!--"square-o-top"end-->
			<div class="square-order">
				<table id="order" width="100%" border="1" cellpadding="0" cellspacing="0">
					<tr align="center"
						style="background:url(${base }/images/table_bg.gif) repeat-x;">
						<td width="25%" height="30">商品类别</td>
						<td width="25%">商品名称</td>
						<td width="10%">采购数量</td>
						<td width="15%">单价</td>
						<td width="15%">合计</td>
						<td width="10%">操作</td>
					</tr>
					<tr align="center" bgcolor="#FFFFFF">
						<td height="30">
							<select class="goodsType" style="width:200px" name="good">
								<!-- <option value="-1">--请选择--</option> -->
								<c:forEach items="${goodsTypelist}"  var="goodstypeDto">
									<option value="${goodstypeDto.uuid}">${goodstypeDto.name}</option>
								</c:forEach>
							</select>
						</td>
						<td>
							<select class="goods" style="width:200px">
								<!-- <option value="-1">--请选择--</option> -->
								<c:forEach items="${goodsList}"  var="goodsDto">
									<option value="${goodsDto.uuid}">${goodsDto.name}</option>
								</c:forEach>
							</select>
						</td>
						<td><input name="nums" class="num order_num" style="width:67px;border:1px solid black;text-align:right;padding:2px" type="text" value="1"/></td>
						<td><input name="prices" class="prices order_num" style="width:93px;border:1px solid black;text-align:right;padding:2px" type="text" value="100"/> 元</td>
						<td class="total" align="right">100.00&nbsp;元</td>
						<td>
							<a class="deleteBtn delete xiu" value="4"><img src="${base }/images/icon_04.gif" /> 删除</a>
						</td>
					</tr>
					<tr id="finalTr" align="center"
						style="background:url(${base }/images/table_bg.gif) repeat-x;">
						<td height="30" colspan="4" align="right">总&nbsp;计:&nbsp;</td>
						<td class="all" width="16%" align="right">100.00&nbsp;元</td>
						<td>&nbsp;</td>
					</tr>
				</table>
				<div class="order-botton">
				<div style="margin:1px auto auto 1px;">
					<table width="100%"  border="0" cellpadding="0" cellspacing="0">
					  <tr>
					    <td>
					    	<a href="javascript:void(0)" id="submitOrder"><img src="${base }/images/save.png" border="0" /></a>
					    </td>
					    <td>&nbsp;</td>
					    <td><a href="#"><img src="${base }/images/reload.png" border="0" /></a></td>
					    <td>&nbsp;</td>
					    <td><a href="#"><img src="${base }/images/cancel.png" border="0" /></a></td>
					  </tr>
					</table>
				</div>
			</div>
			</div>
		</form>
	</div>
	
	<div class="content-bbg"></div>
</div>
