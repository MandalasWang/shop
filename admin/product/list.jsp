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
<script type="text/javascript">
	function addProduct() {
		window.location.href = "${pageContext.request.contextPath}/admin/product/add.jsp";
	}
	function delproduct(pid) {
		if (confirm("您确认要删除吗？")) {
			//确认删除			
			window.location.href= "${pageContext.request.contextPath}/Admin?method=DelproductBypid&pid="
					+ pid;
		}
	}
</script>
</HEAD>
<body>
	<br>
	<form id="Form1" name="Form1"
		action="${pageContext.request.contextPath}/Admin?method=searchproductlist"
		method="post">
		商品名称:<input type="text" name="pname">
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
		<input type="submit" value="搜索">
        </form>
		<table style="margin-top: 10px" cellSpacing="1" cellPadding="0"
			width="100%" align="center" bgColor="#f5fafe" border="0">
			<TBODY>
				<tr>
					<td class="ta_01" align="center" bgColor="#afd1f3"><strong>商品列表</strong>
					</TD>
				</tr>
				<tr>
					<td class="ta_01" align="right">
						<button type="button" id="add" name="add" value="添加"
							class="button_add" onclick="addProduct()">
							&#28155;&#21152;</button>

					</td>
				</tr>
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
								<td align="center" width="15%">是否热门</td>
								<td align="center" width="14%">商品库存</td>
								<td width="7%" align="center">编辑</td>
								<td width="7%" align="center">删除</td>
							</tr>
							<c:forEach items="${productlist }" var="pro" varStatus="vs">
								<tr onmouseover="this.style.backgroundColor = 'white'"
									onmouseout="this.style.backgroundColor = '#F5FAFE';">
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="10%">${vs.count }</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="14%"><img width="40" height="45"
										src="${pageContext.request.contextPath}/${pro.pimage }"></td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="14%">${pro.pname }</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="14%">${pro.shop_price }</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="14%">${pro.is_hot==1?"是":"否" }</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="17%">${pro.counts }</td>	
									<td align="center" style="HEIGHT: 22px"><a
										href="${ pageContext.request.contextPath }/Admin?method=editproduct&pid=${pro.pid}">
											<img
											src="${pageContext.request.contextPath}/images/i_edit.gif"
											border="0" style="CURSOR: hand">
									</a></td>

									<td align="center" style="HEIGHT: 22px"><a
										href="javascript:void(0);" onclick="delproduct('${pro.pid}')">
											<img
											src="${pageContext.request.contextPath}/images/i_del.gif"
											width="16" height="16" border="0" style="CURSOR: hand">
									</a></td>
								</tr>
							</c:forEach>

						</table>
					</td>
				</tr>

			</TBODY>
		</table>
	
</body>
</HTML>

