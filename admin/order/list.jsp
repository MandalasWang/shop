<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<HTML>
<HEAD>
<meta http-equiv="Content-Language" content="zh-cn">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.request.contextPath}/css/Style1.css"
	rel="stylesheet" type="text/css" />
<script language="javascript"
	src="${pageContext.request.contextPath}/js/public.js"></script>
<script type="text/javascript">
	function searchinfo(oid) {
		window.location.href = "${pageContext.request.contextPath}/Admin?method=getOrderinfo&oid="
				+ oid;

	}
	function deleteorder(oid) {
		if (confirm("您确认要收货吗？")) {
			//确认删除			
			window.location.href = "${pageContext.request.contextPath}/Admin?method=DelOrderByoid&oid="+oid;
					
		}
	}
	
</script>
</HEAD>
<body>
	<br>
	<table cellSpacing="1" cellPadding="0" width="100%" align="center"
		bgColor="#f5fafe" border="0">
		<TBODY>
			<tr>
				<td class="ta_01" align="center" bgColor="#afd1f3"><strong>订单列表</strong>
				</TD>
			</tr>

			<tr>
				<td class="ta_01" align="center" bgColor="#f5fafe">
					<table cellspacing="0" cellpadding="1" rules="all"
						bordercolor="gray" border="1" id="DataGrid1"
						style="BORDER-RIGHT: gray 1px solid; BORDER-TOP: gray 1px solid; BORDER-LEFT: gray 1px solid; WIDTH: 100%; WORD-BREAK: break-all; BORDER-BOTTOM: gray 1px solid; BORDER-COLLAPSE: collapse; BACKGROUND-COLOR: #f5fafe; WORD-WRAP: break-word">
						<tr
							style="FONT-WEIGHT: bold; FONT-SIZE: 12pt; HEIGHT: 25px; BACKGROUND-COLOR: #afd1f3">

							<td align="center" width="8%">订单号</td>
							<td width="7%" align="center">收货人</td>
							<td width="8%" align="center">收货人手机号码</td>
							<td width="22%" align="center">收货人地址</td>
							<td align="center" width="7%">买家昵称</td>
							<td align="center" width="7%">付款方式</td>
							<td align="center" width="8%">订单信息</td>
							<td align="center" width="6%">收货信息</td>


						</tr>
						<c:forEach items="${orderlist }" var="order" varStatus="vs">
							<tr onmouseover="this.style.backgroundColor = 'white'"
								onmouseout="this.style.backgroundColor = '#F5FAFE';">
								<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="8%">${vs.count }</td>
								<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="8%">${order.name }</td>
								<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="8%">${order.telephone }</td>
								<td style="CURSOR: hand; HEIGHT: 22px" align="center"
									width="20%">${order.address }</td>
								<td style="CURSOR: hand; HEIGHT: 22px" align="center"
									width="10%">${order.user.username }</td>
								<td style="CURSOR: hand; HEIGHT: 22px" align="center"
									width="10%">${order.state==1?"在线付款":"货到付款" }</td>
								<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="8%">
									<button type="button" id="info" name="info"
										onclick="searchinfo('${order.oid}')">查询</button>
								</td>
							
								 <td style="CURSOR: hand; HEIGHT: 22px" align="center" width="8%">
								 <a href="javascript:void(0);" onclick="deleteorder('${order.oid}')">								
									<button >确认收货</button></a>
								</td>
							</tr>
						</c:forEach>
					</table>
				</td>
			</tr>
		</TBODY>
	</table>
</body>
</HTML>

