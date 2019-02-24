<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<script type="text/javascript">
	function delproduct() {
		alert("请先登录！");
		window.localtion.href = "${pageContext.request.contextPath }/login.jsp";
	}
</script>
<!-- 登录 注册 购物车... -->
<div class="container-fluid">
	<div class="col-md-4">
		<!-- <img src="img/logo2.png" /> -->
	</div>
	<div class="col-md-5">
		<img src="img/header.png" />
	</div>
	<div class="col-md-3" style="padding-top: 20px">
		<ol class="list-inline">
			<!-- 如果用户没有登录 -->
			<c:if test="${empty user}">
				<li><a href="login.jsp">用户登录</a></li>
				<li><a href="register.jsp">注册</a></li>
				<li><a href="${pageContext.request.contextPath }/cart.jsp">购物车</a></li>
				<li><a href="javascript:void(0);" onclick="delproduct()">我的订单</a></li>&nbsp;&nbsp;&nbsp;
			<li><a href="${pageContext.request.contextPath }/admin">[我是管理员]</a></li>
			</c:if>
			<!-- 如果用户登录了 -->
			<c:if test="${!empty user}">
				<li>${user.name }</li>
				<li><a href="${pageContext.request.contextPath }/loginOut">退出</a></li>
				<li><a href="${pageContext.request.contextPath }/cart.jsp">购物车</a></li>
				<li><a
					href="${pageContext.request.contextPath }/product?method=getMyorder_list&uid=${user.uid}">我的订单</a></li>&nbsp;&nbsp;&nbsp;
			<li><a href="${pageContext.request.contextPath }/admin">[我是管理员]</a></li>
			</c:if>
		</ol>
	</div>
</div>

<!-- 导航条 -->
<div class="container-fluid">
	<nav class="navbar navbar-inverse">


		<div class="container-fluid" style="margin-left: 160px">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
					aria-expanded="false">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand"
					href="${pageContext.request.contextPath }/product?method=index">首页</a>
			</div>

			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
					<c:forEach items="${categories }" var="category">
						<li class="active"><a
							href="${pageContext.request.contextPath }/product?method=product_list&cid=${category.cid }">${category.cname }<span
								class="sr-only">(current)</span></a></li>
					</c:forEach>
				</ul>

				<form class="navbar-form navbar-right" role="search"
					action="${pageContext.request.contextPath }/product?method=searchBypname">
					<div class="form-group" style="position: relative">
						<input type="text" class="form-control" placeholder="Search"
							id="search" onkeyup="searchword(this)" name="pname">
						<div id="showDiv"
							style="display: none; position: absolute; z-index: 1000; background: #fff; width: 179px; border: 1px solid #ccc;"></div>
					</div>
					<button type="submit" class="btn btn-default">Submit</button>
				</form>
				<!-- 完成异步搜索 -->
				<script type="text/javascript">
					//将鼠标移动到选项上选项变色
					function overFn(obj) {
						$(obj).css("background", "#DBEAF9");
					}
					//将鼠标移开商品选项变回原色
					function outFn(obj) {
						$(obj).css("background", "#fff");
					}
					//点击商品选项将商品选项结果传送给查询框
					function clickFn(obj) {
						var product = $("#search").val($(obj).html());
						$("#showDiv").css("display", "none");

					}

					function searchword(obj) {
						//1、获得输入框的输入的内容
						var word = $(obj).val();
						//2、根据输入框的内容去数据库中模糊查询---  返回List<objct>

						var content = "";
						$
								.post(
										"${pageContext.request.contextPath}/product?method=searchword",
										//将数据传送给searchwordwervlet中
										{
											"word" : word
										},
										function(data) {
											//3、将返回的商品的名称 现在showDiv中

											if (data.length > 0) {
												for (var i = 0; i < data.length; i++) {
													content += "<div style='padding:5px;cursor:pointer' onclick='clickFn(this)' onmouseover='overFn(this)' onmouseout='outFn(this)'>"
															+ data[i]
															+ "</div>";
												}
												$("#showDiv").html(content);
												$("#showDiv").css("display",
														"block");
											}

										}, "json");

					}
				</script>
			</div>
		</div>
	</nav>
</div>