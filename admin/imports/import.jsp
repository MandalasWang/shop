<%@ page language="java" pageEncoding="UTF-8"%>
<HTML>
	<HEAD>
		<meta http-equiv="Content-Language" content="zh-cn">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<LINK href="${pageContext.request.contextPath}/css/Style1.css" type="text/css" rel="stylesheet">
	</HEAD>
	
	<body>
		<form id="userAction_save_do" name="Form1" action="${pageContext.request.contextPath}/Admin?method=updateimportBypid&pid=${product.pid}" method="post">
			<input type="hidden" name="method" value="save">
			&nbsp;
			<table cellSpacing="1" cellPadding="5" width="100%" align="center" bgColor="#eeeeee" style="border: 1px solid #8ba7e3" border="0">
				<tr >
					<td class="ta_01" align="center" bgColor="#afd1f3" colSpan="7" width="100%"
						height="26">
						<strong><STRONG>进货控制</STRONG>
						</strong>
					 </td> 
				</tr>

				<tr>
					<td width="10%" bgColor="#f5fafe" align="center" >
						商品序号:
					</td>
					<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="10%">${product.pid }</td>
					<td width="10%" bgColor="#f5fafe" align="center" >
						商品图片:
					</td>
					<td style="CURSOR: hand; HEIGHT: 22px" align="center"
					width="10%"><img width="40" height="45"src="${pageContext.request.contextPath}/${product.pimage }">
					</td>
					<td width="10%"  bgColor="#f5fafe" align="center">
						进货数量:
					</td>
					<td  bgColor="#ffffff" width="10%" >
						<input type="text" name="importconts"  id="userAction_save_do_logonName" />
					</td>
					<td class="ta_01" style="WIDTH: 100%" align="left"
						bgColor="#f5fafe" colSpan="6">
						<button type="submit" id="userAction_save_do_submit" value="确定进货" style="height:26px" class="button_ok">
							确认进货
						</button>
					</td>
				
				</tr>
			
			</table>
		</form>
	</body>
</HTML>

