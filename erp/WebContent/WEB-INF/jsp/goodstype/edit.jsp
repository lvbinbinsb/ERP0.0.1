<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<link href="${pageContext.request.contextPath }/css/index.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath  }/js/jquery-1.8.3.js"></script>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set value="${pageContext.request.contextPath }" var="base"></c:set>
<script type="text/javascript">
$(function() {
	$('option').each(function(i,l){
		var id=$("#id").val();
		if($(l).val()==id){
			$(l).attr('selected','selected');
		}
	});
	
});

function submit(){
	//alert(1)
	var gys=$("#gys").val();
	var flag=false;
	if(gys == -1){
		alert("填写正确")
		return false;
	}else{
		flag=true;
	}
	if(flag){
		$("#supplieruuid").val(gys);
		$("#forms").submit();
	}
	
}

function reload(){
	window.location.href="${pageContext.request.contextPath}/goodstype/toGoodstypeEdit.do?uuid="+${goodstypes.uuid};
}

function cancel(){
	window.location.href="${pageContext.request.contextPath}/goodstype/goodstypeList.do";
}
</script>
<input type="hidden"   id="id" value="${goodstypes.supplieruuid }"/>
<div class="content-right">
	<div class="content-r-pic_w">
		<div style="margin:8px auto auto 12px;margin-top:6px">
			<span class="page_title">商品类别管理</span>
		</div>
	</div>
	<div class="content-text">
		<div class="square-order">
			<form action="${base }/goodstype/editGoodstype.do" method="post" id="forms">
			<input type="hidden"  name="supplieruuid" id="supplieruuid"/>
			<input type="hidden"   id="uuid" name="uuid" value="${goodstypes.uuid }"/>
  			<div style="border:1px solid #cecece;">
				<table width="100%"  border="0" cellpadding="0" cellspacing="0">
				  <tr bgcolor="#FFFFFF">
				    <td>&nbsp;</td>
				  </tr>
				</table>
				<table width="100%"  border="0" cellpadding="0" cellspacing="0">
				    <tr  bgcolor="#FFFFFF">
				      <td width="18%" height="30" align="center">供应商</td>
				      <td width="82%" colspan="3">
				      		<select class="kuan" style="width:190px" id="gys">
								<option value="-1">----请-选-择----</option>
								<c:forEach items="${suppliers}" var="supplier">
							<option value="${supplier.uuid }">${supplier.name }</option>
							</c:forEach>
							</select>
				      </td>
				    </tr>
				    <tr  bgcolor="#FFFFFF">
				      <td colspan="4">&nbsp;</td>
				    </tr>
				    <tr  bgcolor="#FFFFFF">
				      <td width="18%" height="30" align="center">商品类别名称</td>
				      <td width="82%" colspan="3">
				      	<input type="text" size="25" name="name" id="goodsname" value="${goodstypes.name }"/>
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
					    	<a href="javascript:submit()" ><img src="${base }/images/save.png" border="0" /></a>
					    </td>
					    <td>&nbsp;</td>
					    <td><a href="javascript:reload();"><img src="${base }/images/reload.png" border="0" /></a></td>
					    <td>&nbsp;</td>
					    <td><a href="javascript:cancel();"><img src="${base }/images/cancel.png" border="0" /></a></td>
					  </tr>
					</table>
				</div>
			</div>
			</form>
		</div><!--"square-order"end-->
	</div><!--"content-text"end-->
	<div class="content-bbg"><img src="${base }/images/content_bbg.jpg" /></div>
</div>
