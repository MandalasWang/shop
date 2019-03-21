<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<HTML>
<HEAD>
<meta http-equiv="Content-Language" content="zh-cn">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.request.contextPath}/css/Style1.css"
	rel="stylesheet" type="text/css" />
<script language="javascript"
	src="${pageContext.request.contextPath}/js/public.js">
</script>

</HEAD>
<body>
	<br>
	<form id="Form1" name="Form1"
		action="javascript:void(0);"
		method="post">
		<%-- 商品名称:<input type="text" name="pname">
		 &nbsp;&nbsp; 是否热门:
		<select name="is_hot">
			<option value="">不限</option>
			<option value="1">是</option>
			<option value="0">否</option>
		</select>
		&nbsp;&nbsp; 商品类别:
		 <select name="cid">
			<option value="">不限</option>
			<c:forEach items="${categorylist }" var="category">
				<option value="${category.cid }">${category.cname }</option>
			</c:forEach>
		</select>&nbsp;&nbsp; 
		<input type="submit" value="搜索"> --%>

		<table style="margin-top: 10px" cellSpacing="1" cellPadding="0"
			width="100%" align="center" bgColor="#f5fafe" border="0">
			<TBODY>
				<tr>
					<td class="ta_01" align="center" bgColor="#afd1f3"><strong>商品列表</strong>
					</TD>
				</tr>
				<!-- <tr>
					<td class="ta_01" align="right">
						<button type="button" id="add" name="add" value="添加"
							class="button_add" onclick="addProduct()">
							&#28155;&#21152;</button>

					</td>
				</tr> -->
				<tr>
					<td class="ta_01" align="center" bgColor="#f5fafe">
						<table cellspacing="0" cellpadding="1" rules="all"
							bordercolor="gray" border="1" id="DataGrid1"
							style="BORDER-RIGHT: gray 1px solid; BORDER-TOP: gray 1px solid; BORDER-LEFT: gray 1px solid; WIDTH: 100%; WORD-BREAK: break-all; BORDER-BOTTOM: gray 1px solid; BORDER-COLLAPSE: collapse; BACKGROUND-COLOR: #f5fafe; WORD-WRAP: break-word">


							<tr
								style="FONT-WEIGHT: bold; FONT-SIZE: 12pt; HEIGHT: 25px; BACKGROUND-COLOR: #afd1f3">

								<td align="center" width="10%">序号</td>
								<td align="center" width="14%">商品图片</td>
								<td align="center" width="14%">商品名称</td>
								<td align="center" width="14%">商品价格</td>
								<td align="center" width="15%">购买数量</td>
								<td align="center" width="15%">商品描述</td>
								
								
							</tr>
							  
								<tr onmouseover="this.style.backgroundColor = 'white'"
									onmouseout="this.style.backgroundColor = '#F5FAFE';">
									<c:forEach items="${order.orderItems}" var="orderItem" varStatus="vs">
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="10%">${vs.count }</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="14%"><img width="40" height="45"
										src="${pageContext.request.contextPath }/${orderItem.product.pimage}"></td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="14%">${orderItem.product.pname}</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="14%">￥${orderItem.product.shop_price}</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="14%">${orderItem.count}</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="17%">${orderItem.product.pdesc }</td>	
									</c:forEach>
									
								</tr>
							  
							      
							           
			
						</table>
						  
						    <a  style="align:right;width:60px;height:25px" href="${pageContext.request.contextPath }/Admin?method=orderlist">返回上一级</a>
					</td>
				</tr>

			</TBODY>
		</table>
	</form>
</body>
</HTML>

