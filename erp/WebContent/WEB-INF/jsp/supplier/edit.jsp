<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<link href="../css/index.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../js/jquery-1.8.3.js"></script>

<script type="text/javascript">
$(function() {
	
	//回现
	 $("option").each(function (i,l) {
     //alert(l)
     if($(l).val()==$("#needid").val()){
			$(l).attr('selected','selected')
		}
 })
	
});


function submit(){
	
	var result=false;
	var name=$("#name").val();
	//alert(name)
	var needs=$("#needs").val();
	var address=$("#address").val();
	//alert(needs)
	var contact=$("#contact").val()
	var tele=$("#tele").val()
	//alert(contact);
	 if(name != null ){
		
		result=true;
	}else{
		alert("请填写完整信息");
		return false;
	} 
	
	 if(address != null ){
		
		result=true;
	}else{
		alert("请填写完整信息");
		return false;
	} 
	 if(contact != null ){
		 
		result=true;
	}else{
		alert("请填写完整信息");
		return false;
	} 
	 if(tele != null ){
		
		result=true;
	}else{
		alert("请填写完整信息");
		return false;
	} 
	
	if(needs =="-1"){
		alert("请选择正确配送方式");
		return false;
	}else{
		result=true;
	}
	if(result){
		//alert(result);
		$("#need").val(needs);
		$("#sub").submit();
	}
	
	
}



</script>


<input type="hidden" id="needid" value="${tblSupplier.needs }">
<div class="content-right">
	<div class="content-r-pic_w">
		<div style="margin:8px auto auto 12px;margin-top:6px">
			<span class="page_title">供应商管理</span>
		</div>
	</div>
	<div class="content-text">
		<div class="square-order">
			<form action="${pageContext.request.contextPath }/supplier/updateSupplier.do" method="post" id="sub">
			<input type="hidden" name="needs" id="need" value="">
			<input type="hidden" name="uuid" value="${tblSupplier.uuid }">
  			<div style="border:1px solid #cecece;">
				<table width="100%"  border="0" cellpadding="0" cellspacing="0">
				  <tr bgcolor="#FFFFFF">
				    <td>&nbsp;</td>
				  </tr>
				</table>
				<table width="100%"  border="0" cellpadding="0" cellspacing="0">
				    <tr  bgcolor="#FFFFFF">
				      <td width="18%" height="30" align="center">供应商名称</td>
				      <td width="82%" colspan="3">
				      	<input type="text" size="82" name="name" id="name" value="${tblSupplier.name }"/>
				      </td>
				    </tr>
				    <tr  bgcolor="#FFFFFF">
				      <td colspan="4">&nbsp;</td>
				    </tr>
				    <tr  bgcolor="#FFFFFF">
				      <td width="18%" height="30" align="center">供应商地址</td>
				      <td width="82%" colspan="3">
				      	<input type="text" size="82" name="address" id="address" value="${tblSupplier.address }"/>
				      </td>
				    </tr>
				    <tr  bgcolor="#FFFFFF">
				      <td colspan="4">&nbsp;</td>
				    </tr>
				    <tr  bgcolor="#FFFFFF">
				      <td width="18%" height="30" align="center">联系人</td>
				      <td width="32%">
				      	<input type="text" size="25" name="contact" id="contact" value="${tblSupplier.contact }"/>
				      </td>
				      <td width="18%" align="center">电话</td>
				      <td width="32%">
				      	<input type="text" size="25" id="tele" name="tele" value="${tblSupplier.tele }"/>
				      </td>
				    </tr>
				    <tr  bgcolor="#FFFFFF">
				      <td colspan="4">&nbsp;</td>
				    </tr>
				    <tr  bgcolor="#FFFFFF">
				      <td width="18%" height="30" align="center">送货方式</td>
				      <td width="32%">
				      		<select style="width:190px" id="needs">
								<option value="-1">----请-选-择----</option>
								<option value="1">送货</option>
								<option value="0">自提</option>
							</select>
				      </td>
				      <td width="18%" align="center">&nbsp;</td>
				      <td width="32%">
				      	&nbsp;
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
					    	<a href="javascript:submit();" ><img src="../images/save.png" border="0" /></a>
					    </td>
					    <td>&nbsp;</td>
					    <td><a href="#"><img src="../images/reload.png" border="0" /></a></td>
					    <td>&nbsp;</td>
					    <td><a href="#"><img src="../images/cancel.png" border="0" /></a></td>
					  </tr>
					</table>
				</div>
			</div>
			</form>
		</div><!--"square-order"end-->
	</div><!--"content-text"end-->
	<div class="content-bbg"><img src="../images/content_bbg.jpg" /></div>
</div>
