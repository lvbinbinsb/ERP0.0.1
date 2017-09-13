<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="base" value="${pageContext.request.contextPath }"></c:set>
<link href="${base }/css/index.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="${base}/js/Calendar.js"></script>
<script type="text/javascript">
	$(function() {
		/* $("#all").click(function() {
			$("[name=roles]:checkbox").attr("checked",$("#all").attr("checked")=="checked");
		});
		$("#reverse").click(function() {
			$("[name=roles]:checkbox").each(function () {
                $(this).attr("checked", !$(this).attr("checked"));
            });
		});
		$("#supplier").change(function(){
			$.post("../../goodsTypeAction_getAll.action",{"gm.supplier.uuid":$(this).val()},function(data){
				$("#goodsType").empty();
				for(var i = 0;i<data.gtList.length;i++){
					var goodsType = data.gtList[i];
					var $option = $("<option value='"+goodsType.uuid+"'>"+goodsType.goodsTypeName+"</option>");	//创建option对象(jQuery格式)
					$("#goodsType").append($option);				//将option对象添加到select组件中
				}
			});
		}); */
		$('option').each(function(i,l){
			
			var gys=$("#gys").val()
			//alert(gys)
			if($(l).html()==gys){
				$(l).attr('selected','selected');
			}
			if($(l).html()==$("#gt").val()){
				$(l).attr('selected','selected');
			}
			
		});
		
	});
	
	function submit(){
		var flag=false;
		var supplier=$("#supplier").val();
		var goodstype= $("#goodstype").val();//$("#goodstype").find("option:selected").text();
		
		if(supplier != null){
			flag=true;
		}else{
			alert("填写完整")
			return false;
		}
		if(goodstype != null){
			flag=true;
		}else{
			alert("填写完整")
			return false;
		}
		if(flag){
			$("#s").val(supplier);
			$("#g").val(goodstype);
			$("#forms").submit();
		}
	}
	
	
</script>
<input type="hidden" id="gys" value="${goods.gysname }"/>
<input type="hidden" id="gt" value="${goods.goodstypename }"/>
<div class="content-right">
	<div class="content-r-pic_w">
		<div style="margin:8px auto auto 12px;margin-top:6px">
			<span class="page_title">商品管理</span>
		</div>
	</div>
	<div class="content-text">
		<div class="square-order">
			<form action="${base }/goods/GoodsEdit.do" method="post" id="forms">
			<input type="hidden" name="uuid" value="${goods.uuid }"/>
			<input type="hidden" name="gysuuid" id="s">
			<input type="hidden" name="goodstypeuuid" id="g">
  			<div style="border:1px solid #cecece;">
				<table width="100%"  border="0" cellpadding="0" cellspacing="0">
				  <tr bgcolor="#FFFFFF">
				    <td>&nbsp;</td>
				  </tr>
				</table>
				<table width="100%"  border="0" cellpadding="0" cellspacing="0">
				    <tr  bgcolor="#FFFFFF">
				      <td width="18%" height="30" align="center">供&nbsp;应&nbsp;商</td>
				      <td width="32%">
				      		<select style="width:190px" id="supplier">
								<option value="-1">----请-选-择----</option>
								<c:forEach items="${supplierAll}" var="supplierAll">
							<option  value="${supplierAll.uuid }"  >${supplierAll.name }</option>
							</c:forEach>

							</select>
				      </td>
				      <td width="18%"align="center">商品类别</td>
				      <td width="32%">
				      		<select style="width:190px" id="goodstype" >
								<option value="-1">----请-选-择----</option>
								<c:forEach items="${goodstypeAll}" var="goodstypeAll">
							<option  value="${goodstypeAll.uuid }"  >${goodstypeAll.name }</option>
							</c:forEach>
							</select>
					  </td>
				    </tr>
				    <tr bgcolor="#FFFFFF">
					  <td colspan="4">&nbsp;</td>
					</tr>
				    <tr  bgcolor="#FFFFFF">
				      <td align="center">商品名称</td>
				      <td>
				      	<input type="text" size="25" name="name" value="${goods.name }"/>
				      </td>
				      <td  align="center">产&nbsp;&nbsp;&nbsp;&nbsp;地</td>
				      <td >
				      	<input type="text" size="25" name="origin" value="${goods.origin }"/>
				      </td>
				    </tr>
				     <tr bgcolor="#FFFFFF">
					  <td colspan="4">&nbsp;</td>
					</tr>
				    <tr  bgcolor="#FFFFFF">
				      <td height="30" align="center">生产厂家</td>
				      <td>
				      	<input type="text" size="25" name="producer" value="${goods.producer }"/>
				      <td align="center">单&nbsp;&nbsp;&nbsp;&nbsp;位</td>
				      <td>
				      	<input type="text" size="25" name="unit" value="${goods.unit }"/>
					  </td>
				     </tr>
				    <tr bgcolor="#FFFFFF">
					  <td colspan="4">&nbsp;</td>
					</tr>
				    <tr  bgcolor="#FFFFFF">
				      <td height="30" align="center">进货单价</td>
				      <td>
				      	<input type="text" size="25" name="inprice" value="${goods.inprice }"/>
					  </td>
				      <td align="center">销售单价</td>
				      <td>
				      	<input type="text" size="25" name="outprice" value="${goods.outprice }"/>
					  </td>
				    </tr>
				    <tr bgcolor="#FFFFFF">
					  <td colspan="4">&nbsp;</td>
					</tr>
				    <tr  bgcolor="#FFFFFF">
				      <td height="30" align="center">体&nbsp;&nbsp;&nbsp;&nbsp;积</td>
				      <td>
				      	<input type="text" size="25" name="usenum" value="${goods.usenum }"/>
					  </td>
				      <td align="center">&nbsp;</td>
				      <td>&nbsp;</td>
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
					    	<a href="javascript:submit()"><img src="${base}/images/save.png" border="0" /></a>
					    </td>
					    <td>&nbsp;</td>
					    <td><a href="#"><img src="${base}/images/reload.png" border="0" /></a></td>
					    <td>&nbsp;</td>
					    <td><a href="#"><img src="${base}/images/cancel.png" border="0" /></a></td>
					  </tr>
					</table>
				</div>
			</div>
			</form>
		</div><!--"square-order"end-->
	</div><!--"content-text"end-->
	<div class="content-bbg"><img src="${base}/images/content_bbg.jpg" /></div>
</div>
