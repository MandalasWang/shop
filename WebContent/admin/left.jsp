<%@ page language="java" pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>菜单</title>
<link href="${pageContext.request.contextPath}/css/left.css" rel="stylesheet" type="text/css"/>
<link rel="StyleSheet" href="${pageContext.request.contextPath}/css/dtree.css" type="text/css" />
</head>
<body>
<table width="100" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td height="12"></td>
  </tr>
</table>
<table width="100%" border="0">
  <tr>
    <td>
<div class="dtree">

	<a href="javascript: d.openAll();">展开所有</a> | <a href="javascript: d.closeAll();">关闭所有</a>
	
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/dtree.js"></script>
	<script type="text/javascript">
	
		d = new dTree('d');
		d.add('01',-1,'系统菜单树');
		d.add('0102','01','分类管理','','','mainFrame');
		d.add('010201','0102','分类管理','${pageContext.request.contextPath}/Admin?method=categorylist','','mainFrame');
		d.add('0104','01','商品管理');
		d.add('010401','0104','商品管理','${pageContext.request.contextPath}/Admin?method=productlist','','mainFrame');
		d.add('0105','01','用户管理');
		d.add('010501','0105','用户管理','${pageContext.request.contextPath}/Admin?method=userlist','','mainFrame');
		d.add('0106','01','订单管理');
		d.add('010601','0106','订单管理','${pageContext.request.contextPath}/Admin?method=orderlist','','mainFrame');
		d.add('0107','01','月销售额');
		d.add('010701','0107','月销售','${pageContext.request.contextPath}/Admin?method=salelist','','mainFrame');
		/* d.add('010702','0107','月销售计算','${pageContext.request.contextPath}/Admin?method=salelistadd','','mainFrame'); */
		d.add('0108','01','进货管理');
		d.add('010801','0108','进货管理','${pageContext.request.contextPath}/Admin?method=importlist','','mainFrame');
		document.write(d);
		
	</script>
</div>	</td>
  </tr>
</table>
</body>
</html>
