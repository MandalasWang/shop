<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>订单详情</title>
<link rel="stylesheet" href="css/bootstrap.min.css" type="text/css" />
<script src="js/jquery-1.11.3.min.js" type="text/javascript"></script>
<script src="js/bootstrap.min.js" type="text/javascript"></script>
<!-- 引入自定义css文件 style.css -->
<link rel="stylesheet" href="css/style.css" type="text/css" />
<style>
body {
	margin-top: 20px;
	margin: 0 auto;
}

.carousel-inner .item img {
	width: 100%;
	height: 300px;
}
</style>
<script type="text/javascript">
    
	function confirmOrder() {
		//提交表单
		 $("#orderForm").submit();
		 location.href = "${pageContext.request.contextPath }/product?method=confirmOrder"; 
	}
	
</script>
</head>

<body>
	<!-- 引入header.jsp -->
	<jsp:include page="/header.jsp"></jsp:include>

	<div class="container">
		<form class="form-horizontal"
		    action="${pageContext.request.contextPath }/product?method=confirmOrder"
				method="post" style="margin-top: 5px; margin-left: 150px;">
				
		<div class="row">
			<div style="margin: 0 auto; margin-top: 10px; width: 950px;">
				<strong>订单详情</strong>
				<table class="table table-bordered">
					<tbody>
						<tr class="warning">
							<th colspan="5">订单编号:${product.pid }</th>
						</tr>
						<tr class="warning">
							<th>图片</th>
							<th>商品</th>
							<th>价格</th>
							<th>数量</th>
							<th>小计</th>
						</tr>
						<c:forEach items="${order.orderItems }" var="orderItem">

							<tr class="active">
								<td width="60" width="40%"><img
									src="${pageContext.request.contextPath }/${orderItem.product.pimage}"
									width="70" height="60"></td>
								<td width="30%"><a target="_blank">${orderItem.product.pname}</a></td>
								<td width="20%">￥${orderItem.product.shop_price}</td>
								<td width="10%">${orderItem.count}</td>
								<td width="15%"><span class="subtotal">￥${orderItem.subtotal }</span></td>
							</tr>

						</c:forEach>
					</tbody>
				</table>
			</div>

			<div style="text-align: right; margin-right: 120px;">
				商品金额: <strong style="color: #ff6600;">￥${order.total }元</strong>
			</div>

		</div>

		<div>
			<hr />
			
				<!-- method的名字 通过表单提交 -->
				<input type="hidden" name="method" value="confirmOrder">
				<!-- 传递订单oid -->
				<div class="form-group">
					<label for="address" class="col-sm-1 control-label">地址</label>
					<div class="col-sm-5">
						<input type="text"  class="form-control" id="useraddress"
							name="address" placeholder="请输入收货地址">
					</div>
				</div>
				<div class="form-group">
					<label for="inputPassword3" class="col-sm-1 control-label">收货人</label>
					<div class="col-sm-5">
						<input type="text"  class="form-control" id="checkworker"
							name="name" placeholder="请输收货人">
					</div>
				</div>
				<div class="form-group">
					<label for="confirmpwd" class="col-sm-1 control-label">电话</label>
					<div class="col-sm-5">
						<input type="text"  class="form-control" name="telephone"
							placeholder="请输入联系方式">
					</div>
				</div>
			
			<hr />
		
			<p style="text-align: right; margin-right: 100px;">
				<INPUT type=image src="./images/finalbutton.gif" width="204"
					height="51" border="0" onclick="this.form.submit()">
				<!--  <a href="javascript:;" onclick="this.form.submit()">
                       <img src="./images/finalbutton.gif" width="204" height="51" border="0" /></a> -->

			</p>

		</div>

</form>
	</div>

	<!-- 引入footer.jsp -->
	<jsp:include page="/footer.jsp"></jsp:include>

</body>

</html>