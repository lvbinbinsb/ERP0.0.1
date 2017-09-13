<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link href="../../css/index.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
<script type="text/javascript">
$(function() {
	 //alert("1");
	/* $("#commit").click(function() {
		$("form:first").submit();
	}); */
});  
	
	 function save(){
		var rs=false;
		var name=$("#name").val();
		if(!name || !$("#code").val() ){
			
			alert("请输入完整!");
			return false;
		}else{
			rs=true;
		}
		if(rs){
			$("#saves").submit();
		}
	}
	

	//重置
	function reloads(){
		//window.location.reload();
		window.location.href="${pageContext.request.contextPath }/role/toEditRole.do?uuid=${role.uuid}";
	}
	
	function quit(){
		window.location.href='${pageContext.request.contextPath}/role/rolelist.do';
	}
	//修改
	function update(){
		var url="${pageContext.request.contextPath}/dep/updatedep.do";
		
		/* $.post(url,params,function(data){
			if(data ==){}
		}"json") */
		
	}
	
</script>
  <html>
 <body>
 	
	<div class="content-right">
	<div class="content-r-pic_w">
		<div style="margin:8px auto auto 12px;margin-top:6px">
			<span class="page_title">角色管理</span>
		</div>
	</div>
	<div class="content-text">
		<div class="square-order">
			<form action="${pageContext.request.contextPath }/role/update.do" method="post" id="saves">
			<input type="hidden" name="uuid" value="${role.uuid }">
  			<div style="border:1px solid #cecece;">
				<table width="100%"  border="0" cellpadding="0" cellspacing="0">
				  <tr bgcolor="#FFFFFF">
				    <td>&nbsp;</td>
				  </tr>
				</table>
				<table width="100%"  border="0" cellpadding="0" cellspacing="0">
				    <tr  bgcolor="#FFFFFF">
				      <td width="18%" height="30" align="center"  >角色名称</td>
				      <td width="32%">
				      	<input type="text" size="25" name="name" id="name" value="${role.name }"/>
				      </td>
				      <td width="18%" align="center">角色代码</td>
				      <td width="32%">
				      	<input type="text" size="25" name="code" id="code" value="${role.code }"/>
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
					    	<a href="javascript:save();" id="commit"><img src="${pageContext.request.contextPath }/images/save.png" border="0" /></a>
					    </td>
					    <td>&nbsp;</td>
					    <td><a href="javascript:reloads()"><img src="${pageContext.request.contextPath }/images/reload.png" border="0" /></a></td>
					    <td>&nbsp;</td>
					    <td><a href="javascript:quit()"><img src="${pageContext.request.contextPath }/images/cancel.png" border="0" /></a></td>
					  </tr>
					</table>
				</div>
			</div>
			</form>
		</div><!--"square-order"end-->
	</div><!--"content-text"end-->
	<div class="content-bbg"><img src="${pageContext.request.contextPath }/images/content_bbg.jpg" /></div>
</div>
</body>
<script type="text/javascript">

</script>
 </html>
