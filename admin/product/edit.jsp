<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<HTML>
<HEAD>
<meta http-equiv="Content-Language" content="zh-cn">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<LINK href="${pageContext.request.contextPath}/css/Style1.css"
	type="text/css" rel="stylesheet">
</HEAD>
<script type="text/javascript">
//页面加载完后确定哪个option被选中
 window.onload=function(){
	//获得当前回显的product的id值
	 var cid="${product.cid}";
	 var options=document.getElementById("cid").getElementByTagName("option");
	 //比较每一个option的value值域cid
	 for(var i=0;i<options.length;i++){
		 if(cid==option[i].value){
			 option[i].selected=true;
		 }
	 }
 }
</script>
<body>
	<!--  -->
	<form id="userAction_save_do" name="Form1"
		action="${pageContext.request.contextPath}/Admin?method=updateproduct&pid=${product.pid}"
		method="post" enctype="multipart/form-data">

		<table cellSpacing="1" cellPadding="5" width="100%" align="center"
			bgColor="#eeeeee" style="border: 1px solid #8ba7e3" border="0">
			<tr>
				<td class="ta_01" align="center" bgColor="#afd1f3" colSpan="6"
					height="26"><strong><STRONG>编辑商品</STRONG> </strong></td>
			</tr>

			<tr>
				<td width="18%" align="center" bgColor="#f5fafe" class="ta_01">
					商品名称：</td>
				<td class="ta_01" bgColor="#ffffff"><input type="text"
					name="pname" value="${product.pname }"
					id="userAction_save_do_logonName" class="bg" /></td>
				<td width="18%" align="center" bgColor="#f5fafe" class="ta_01">
					是否热门：</td>
				<td class="ta_01" bgColor="#ffffff"><select name="is_hot">
						<option value="1">是</option>
						<option value="0">否</option>
				</select></td>
			</tr>
			<tr>
				<td width="18%" align="center" bgColor="#f5fafe" class="ta_01">
					市场价格：</td>
				<td class="ta_01" bgColor="#ffffff"><input type="text"
					name="market_price" value="${product.market_price }"
					id="userAction_save_do_logonName" class="bg" /></td>
				<td width="18%" align="center" bgColor="#f5fafe" class="ta_01">
					商城价格：</td>
				<td class="ta_01" bgColor="#ffffff"><input type="text"
					name="shop_price" value="${product.shop_price }"
					id="userAction_save_do_logonName" class="bg" /></td>
				
			</tr>
			<tr>
				<td width="18%" align="center" bgColor="#f5fafe" >
					商品图片：</td>
				<td class="ta_01" bgColor="#ffffff" colspan="1">
				<input type="file" name="upload" /></td>
				
			</tr>
			
			<tr>
				<td width="18%" align="center" bgColor="#f5fafe" class="ta_01">
					所属分类：</td>
				<td class="ta_01" bgColor="#ffffff" colspan="4">
				<select name="cid">
						<c:forEach items="${categorylist }" var="category">
							<option value="${category.cid }">${category.cname }</option>
						</c:forEach>
				</select></td>
				
			</tr>
			<tr>
				<td width="18%" align="center" bgColor="#f5fafe" class="ta_01">
					商品描述：</td>
				<td class="ta_01" bgColor="#ffffff" colspan="3"><textarea
						name="pdesc" rows="5" cols="30">${product.pdesc }</textarea></td>
			</tr>
			<tr>
				<td class="ta_01" style="WIDTH: 100%" align="center"
					bgColor="#f5fafe" colSpan="6">
					<button type="submit" id="userAction_save_do_submit" value="确定"
						class="button_ok">&#30830;&#23450;</button> <FONT face="宋体">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</FONT>
					<button type="reset" value="重置" class="button_cancel">&#37325;&#32622;</button>

					<FONT face="宋体">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</FONT> <INPUT
					class="button_ok" type="button" onclick="history.go(-1)" value="返回" />
					<span id="Label1"></span>
				</td>
			</tr>
		</table>
	</form>
</body>
</HTML>