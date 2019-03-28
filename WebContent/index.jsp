<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>黑马商城首页</title>
		<link rel="stylesheet" href="css/bootstrap.min.css" type="text/css" />
		<script src="js/jquery-1.11.3.min.js" type="text/javascript"></script>
		<script src="js/bootstrap.min.js" type="text/javascript"></script>
<script type="text/javascript">
	
	function delproduct() {
		alert("请先登录！");
		window.localtion.href = "${pageContext.request.contextPath }/login.jsp";
	}
</script>
	</head>
 <style>
 * { margin: 0; padding: 0; } 
 body { margin: 0; padding: 0; text-decoration: none; font-size: 14px; }
li { list-style: none; }
.menu { width: 160pxpx; height: 60px; margin-left: 0px;margin-top: 8px;margin-bottom: 0px; background-color: #FBFFFD;position: absolute }
.menu .menuTop{ background-color:#3C3C3C; color: #fff; width: 145px; height: 43px; padding-left: 20px; line-height: 40px; cursor: pointer; }
.menu ul/* :hover */{ width: 145px; background-color:  #FBFFFD; border: 0px solid black; box-sizing: border-box; position: relative;z-index: 88; }
.menu ul li  {  background-color: #FBFFFD;height: 30px; padding-left: 8px; text-align: left; line-height: 30px; font-size: 13px; background: url(image/1.png) no-repeat right;position: relative z-index: 99; }
.menu ul li a { font-size: 14px;padding-left: 8px;color: #EOEOEO; }
.menu ul li a:hover { padding-left: 8px;color: red; text-decoration: underline; cursor: pointer; }
.menu ul li:hover { border: 1px solid #DDD; border-right: 0; background-image: none; }
.menu ul li:hover .submenu { display: block; }
.menu ul li:hover span { width: 30px; height: 30px; display: inline-block; background-color: #FFF; float: right; z-index: 100; position: relative; }
.menu ul li .submenu { position: absolute; left: 146px; top: 0; width: 760px; height: 260px; border: 1px solid #DDD; box-shadow: 0 0 8px #DDD; -moz-box-shadow: 0 0 8px #DDD; -webkit-box-shadow: 0 0 8px #DDD; background-color: #FFF; z-index: 3; display: none; }
.menu ul li .submenu .subleft { margin-left: 0px; width: 500px; height: 260px; float: left; padding: 5px; }
.menu ul li .submenu .subleft dl { overflow: hidden; border-bottom: 1px solid #D1D1D1; padding: 10px 0; }
.menu ul li .submenu .subleft dl dt { float: left; height: 22px; line-height: 22px; margin-right: 10px; font-weight: bold; color: #707070; font-size: 16px; cursor: pointer; }
.menu ul li .submenu .subleft dl dd { }
.menu ul li .submenu .subleft dl dd a { display: block; float: left; border-left: 1px solid #707070; padding: 0 8px; color: #707070; height: 14px; line-height: 14px; margin: 3px 0; font-size: 13px; }
.menu ul li .submenu .subright { width: 210px; height: 260px;  float: left; }
</style>

	<body>
		<div class="container-fluid">

			<!-- 引入header.jsp -->
			<%-- <jsp:include page="/header.jsp"></jsp:include> --%>
          
			<div class="container-fluid">
	<div class="col-md-4">
		<!-- <img src="img/logo2.png" /> -->
	</div>
	<div class="col-md-5">
		<img src="img/header.png" />
	</div>
	<div class="col-md-3" style="padding-top:20px">
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
			<li><a href="${pageContext.request.contextPath }/product?method=getMyorder_list&uid=${user.uid}">我的订单</a></li>&nbsp;&nbsp;&nbsp;
			<li><a href="${pageContext.request.contextPath }/admin">[我是管理员]</a></li>
			</c:if>
			
		</ol>
	</div>
</div>

<!-- 导航条 -->
<div class="container-fluid">
	<nav class="navbar navbar-inverse">
	
	<div class="menu"  >
    <div class="menuTop" >全部商品分类</div>
    <ul>   
      <li> <a href="javascript:;">手机通讯</a>
        <div class="submenu">
          <div class="subleft">
            <dl>
              <dt>热门品牌</dt>
              <dd> <a href="${pageContext.request.contextPath }/product?method=getmobileBypname&pname=${'iPhone'}&cid=${1}">iPhone</a>
                   <a href="${pageContext.request.contextPath }/product?method=getmobileBypname&pname=${'华为' }&cid=${1}">华为</a> 
                   <a href="${pageContext.request.contextPath }/product?method=getmobileBypname&pname=${'荣耀' }&cid=${1}">荣耀</a>
                   <a href="${pageContext.request.contextPath }/product?method=getmobileBypname&pname=${'小米'}&cid=${1}">小米</a>
                   <a href="${pageContext.request.contextPath }/product?method=getmobileBypname&pname=${'vivo'}&cid=${1}">vivo</a> 
                   <a href="${pageContext.request.contextPath }/product?method=getmobileBypname&pname=${'三星'}&cid=${1}">三星</a>
                    <a href="${pageContext.request.contextPath }/product?method=getmobileBypname&pname=${'魅族'}&cid=${1}">魅族</a> 
                 </dd>
            </dl>
            <dl>
              <dt>热门分类</dt>
              <dd> <a href="${pageContext.request.contextPath }/product?method=product_list&cid=${1}">全部手机</a> 
              <a href="${pageContext.request.contextPath }/product?method=getmobileBypname&pname=${'游戏'}&cid=${1}">游戏手机</a> 
              <a href="${pageContext.request.contextPath }/product?method=getmobileBypname&pname=${'拍照'}&cid=${1}">拍照神器</a>
               <a href="${pageContext.request.contextPath }/product?method=getmobileBypname&pname=${'全面屏16:9'}&cid=${1}">全面屏</a> </dd>
            </dl>
            
            <dl>
              <dt>价格区间</dt>
              <dd> <a href="${pageContext.request.contextPath }/product?method=getmobileByPrice&price=${999}&cid=${1}">低于1000</a>
               <a href="${pageContext.request.contextPath }/product?method=getmobileByPrice&price=${1499}&cid=${1}">1000-1500</a>
                <a href="${pageContext.request.contextPath }/product?method=getmobileByPrice&price=${2499}&cid=${1}">1500-2500</a>
                <a href="${pageContext.request.contextPath }/product?method=getmobileByPrice&price=${3499}&cid=${1}">2500-3500</a>
               <a href="${pageContext.request.contextPath }/product?method=getmobileByPrice&price=${4499}&cid=${1}">3500-4500</a>
                <a href="${pageContext.request.contextPath }/product?method=getmobileByPrice&price=${4501}&cid=${1}">4500以上</a> </dd>
            </dl>
          </div>
          <div class="subright" style="background-color: antiquewhite"> 
              <img alt=""  src="products\hao\big01.jpg" style=" width: 210px; height: 258px;">
              </div>
        </div>
      </li>
      
      
      <li> <a href="javascript:;">电脑办公</a> <span></span>
        <div class="submenu">
        <div class="subleft">
          <dl>
            <dt>热门品牌</dt>
               <dd> 
                <a href="${pageContext.request.contextPath }/product?method=getConputerBypname&pname=${'华硕'}&cid=${2}">华硕</a> 
                <a href="${pageContext.request.contextPath }/product?method=getConputerBypname&pname=${'联想'}&cid=${2}">联想</a> 
                <a href="${pageContext.request.contextPath }/product?method=getConputerBypname&pname=${'苹果'}&cid=${2}">苹果</a>
                <a href="${pageContext.request.contextPath }/product?method=getConputerBypname&pname=${'惠普'}&cid=${2}">惠普</a>
                <a href="${pageContext.request.contextPath }/product?method=getConputerBypname&pname=${'微星'}&cid=${2}">微星</a>
                <a href="${pageContext.request.contextPath }/product?method=getConputerBypname&pname=${'微软'}&cid=${2}">微软</a>
                <a href="${pageContext.request.contextPath }/product?method=getConputerBypname&pname=${'神舟'}&cid=${2}">神舟</a>
              </dd>
          </dl>
          <dl>
            <dt>电脑整机</dt>
              <dd> 
                 <a href="${pageContext.request.contextPath }/product?method=product_list&cid=${2}">全部电脑</a>
                 <a href="${pageContext.request.contextPath }/product?method=getConputerBypname&pname=${'游戏'}&cid=${2}">游戏本</a> 
                 <a href="${pageContext.request.contextPath }/product?method=getConputerBypname&pname=${'超薄'}&cid=${2}">超薄本</a> 
                 <a href="${pageContext.request.contextPath }/product?method=getConputerBypname&pname=${'商务'}&cid=${2}">商务本</a>          
                 <a href="${pageContext.request.contextPath }/product?method=getConputerBypname&pname=${'台式'}&cid=${2}">台式机</a>
             </dd>
          </dl>
          <dl>
            <dt>价格区间</dt>
               <dd>
                    <a href="${pageContext.request.contextPath }/product?method=getcomputerByPrice&price=${1999}&cid=${2}">低于2000</a> 
                    <a href="${pageContext.request.contextPath }/product?method=getmobileByPrice&price=${2999}&cid=${2}">2000-3000</a>
                    <a href="${pageContext.request.contextPath }/product?method=getmobileByPrice&price=${4499}&cid=${2}">3000-4500</a> 
                    <a href="${pageContext.request.contextPath }/product?method=getmobileByPrice&price=${4501}&cid=${2}">4500以上</a> 
              </dd>
          </dl>
          
        </div>
        <div class="subright" style="background-color: antiquewhite">
           <img alt="" src="products\1\c_0031.jpg" style=" width: 210px; height: 258px;">
         </div>
         </div>
      </li>
      
   
   
      <li> <a href="javascript:;">平板电脑</a> <span></span>
        <div class="submenu">
        <div class="subleft">
          <dl>
            <dt>经典品牌</dt>
            <dd> <a href="${pageContext.request.contextPath }/product?method=getTabletPCBypname&pname=${'小米'}&cid=${3}">小米</a>
             <a href="${pageContext.request.contextPath }/product?method=getTabletPCBypname&pname=${'荣耀'}&cid=${3}">荣耀</a> 
             <a href="${pageContext.request.contextPath }/product?method=getTabletPCBypname&pname=${'三星'}&cid=${3}">三星</a>
              <a href="${pageContext.request.contextPath }/product?method=getTabletPCBypname&pname=${'华为'}&cid=${3}">华为</a> 
              <a href="${pageContext.request.contextPath }/product?method=getTabletPCBypname&pname=${'Apple'}&cid=${3}">Apple</a>  </dd>
          </dl>
          <dl>
            <dt>为您推荐</dt>
            <dd> <a href="${pageContext.request.contextPath }/product?method=product_list&cid=${3}">全部平板</a>
                 <a href="${pageContext.request.contextPath }/product?method=getTabletPCBytype&pname=${'二合一'}&cid=${3}">二合一平板</a>
                 <a href="${pageContext.request.contextPath }/product?method=getTabletPCBytype&pname=${'通话'}&cid=${3}">通话平板</a> 
                 <a href="${pageContext.request.contextPath }/product?method=getTabletPCBytype&pname=${'娱乐平板'}&cid=${3}">娱乐平板</a>
            </dd>
          </dl>
          <dl>
            <dt>价格区间</dt>
            <dd> 
                   <a href="${pageContext.request.contextPath }/product?method=getTabletPCByprice&price=${999}&cid=${3}">低于1000</a>
                   <a href="${pageContext.request.contextPath }/product?method=getTabletPCByprice&price=${1999}&cid=${3}">1000-2000</a>
                   <a href="${pageContext.request.contextPath }/product?method=getTabletPCByprice&price=${2999}&cid=${3}">2000-3000</a> 
                   <a href="${pageContext.request.contextPath }/product?method=getTabletPCByprice&price=${3000}&cid=${3}">3000以上</a>
              </dd>
          </dl>
         
        </div>
        <div class="subright" style="background-color: antiquewhite"> 
          <img alt="" src="products\1\c_0049.jpg" style=" width: 210px; height: 258px;">
          </div>
          </div>
      </li>
      
      
      <li> <a href="javascript:;">娱乐影音</a> <span></span>
        <div class="submenu">
        <div class="subleft">
          <dl>
            <dt>经典品牌</dt>
            <dd> 
                 <a href="${pageContext.request.contextPath }/product?method=getSoudBypname&pname=${'Beat'}&cid=${4}">Beat</a> 
                 <a href="${pageContext.request.contextPath }/product?method=getSoudBypname&pname=${'铁三角'}&cid=${4}">铁三角</a> 
                 <a href="${pageContext.request.contextPath }/product?method=getSoudBypname&pname=${'漫步者'}&cid=${4}">漫步者</a> 
                 <a href="${pageContext.request.contextPath }/product?method=getSoudBypname&pname=${'Bose'}&cid=${4}">Bose</a>
                 <a href="${pageContext.request.contextPath }/product?method=getSoudBypname&pname=${'索尼'}&cid=${4}">索尼</a>
             </dd>
          </dl>
          <dl>
            <dt>分类精选</dt>
             <dd> 
                <a href="${pageContext.request.contextPath }/product?method=product_list&cid=${4}">全部耳机/音响</a>
                <a href="${pageContext.request.contextPath }/product?method=getSoudBypname&pname=${'流'}&cid=${4}">潮流耳机</a> 
                <a href="${pageContext.request.contextPath }/product?method=getSoudBypname&pname=${'蓝牙耳机'}&cid=${4}">蓝牙耳机</a> 
                <a href="${pageContext.request.contextPath }/product?method=getSoudBypname&pname=${'家'}&cid=${4}">家居音响</a>
                
              </dd>
          </dl>
          <dl>
            <dt>价格区间</dt>
            <dd>
                  <a href="${pageContext.request.contextPath }/product?method=getSoudByprice&price=${99}&cid=${4}">低于100</a> 
                  <a href="${pageContext.request.contextPath }/product?method=getSoudByprice&price=${299}&cid=${4}">100-300</a> 
                  <a href="${pageContext.request.contextPath }/product?method=getSoudByprice&price=${499}&cid=${4}">300-500</a>
                  <a href="${pageContext.request.contextPath }/product?method=getSoudByprice&price=${999}&cid=${4}">500-1000</a>
                  <a href="${pageContext.request.contextPath }/product?method=getSoudByprice&price=${1000}&cid=${4}">1000以上</a>
               </dd>
          </dl>
          
        </div>
        <div class="subright" style="background-color: antiquewhite">
          <img alt="" src="products/4/s_019.jpg" style=" width: 210px; height: 258px;">
         </div>
         </div>
      </li>
      
    </ul>
    
  </div>
		<div class="container-fluid" style="margin-left:160px">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
					<span class="sr-only">Toggle navigation</span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="${pageContext.request.contextPath }/product?method=index">首页</a>
			</div>

			<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
				<c:forEach items="${categories }" var="category">
					<li class="active">
					<a href="${pageContext.request.contextPath }/product?method=product_list&cid=${category.cid }">${category.cname }<span class="sr-only">(current)</span></a>
					</li>
				</c:forEach>
				</ul>
				
				<form class="navbar-form navbar-right" role="search" action="${pageContext.request.contextPath }/product?method=searchBypname" method=>
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
			
			<!-- 轮播图 -->
			<div class="container-fluid" >
				<div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
					<!-- 轮播图的中的小点 -->
					<ol class="carousel-indicators">
						<li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
						<li data-target="#carousel-example-generic" data-slide-to="1"></li>
						<li data-target="#carousel-example-generic" data-slide-to="2"></li>
					</ol>
					<!-- 轮播图的轮播图片 -->
					<div class="carousel-inner" role="listbox" style="margin-top:1px">
						<div class="item active">
							<img src="img/1.jpg">
							<div class="carousel-caption">
								<!-- 轮播图上的文字 -->
							</div>
						</div>
						<div class="item">
							<img src="img/2.jpg">
							<div class="carousel-caption">
								<!-- 轮播图上的文字 -->
							</div>
						</div>
						<div class="item">
							<img src="img/3.jpg">
							<div class="carousel-caption">
								<!-- 轮播图上的文字 -->
							</div>
						</div>
					</div>

					<!-- 上一张 下一张按钮 -->
					<a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
						<span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
						<span class="sr-only">Previous</span>
					</a>
					<a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
						<span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
						<span class="sr-only">Next</span>
					</a>
				</div>
			</div>
			
			<!-- 热门商品 -->
			<div class="container-fluid">
				<div class="col-md-12">
					<h2>热门商品&nbsp;&nbsp;<img src="img/title2.jpg"/></h2>
				</div>
				<div class="col-md-2" style="border:1px solid #E7E7E7;border-right:0;padding:0;">
					<img src="products/hao/big01.jpg" width="205" height="415" style="display: inline-block;"/>
				</div>
				<div class="col-md-10">
					<div class="col-md-6" style="text-align:center;height:200px;padding:0px;">
						<a href="javascript:void(0);">
							<img src="products/hao/middle01.jpg" width="516px" height="200px" style="display: inline-block;">
						</a>
					</div>
				<c:forEach items="${hotProducts }" var="hotpro">
					<div class="col-md-2" style="text-align:center;height:218px;padding:10px 0px;">
						<a href="${pageContext.request.contextPath }/product?method=product_info&pimage=${hotpro.pimage}">
							<img src="${pageContext.request.contextPath }/${hotpro.pimage }" width="130" height="130" style="display: inline-block;">
						</a>
						<p><a href="javascript:void(0);" style='color:#666'>${hotpro.pname }</a></p>
						<p><font color="#E4393C" style="font-size:16px">&yen;${hotpro.shop_price }</font></p>
					</div>
	           </c:forEach>
				
	
				</div>
			</div>
			
			<!-- 广告条 -->
            <div class="container-fluid" style="padding-top:8px">
				<img src="products/hao/ad.jpg" width="100%"/>
			</div>
			
			<!-- 最新商品 -->
			<div class="container-fluid">
				<div class="col-md-12">
					<h2>最新商品&nbsp;&nbsp;<img src="img/title2.jpg"/></h2>
				</div>
				<div class="col-md-2" style="border:1px solid #E7E7E7;border-right:0;padding:0;">
					<img src="products/hao/big01.jpg" width="205" height="415" style="display: inline-block;"/>
				</div>
				<div class="col-md-10">
					<div class="col-md-6" style="text-align:center;height:200px;padding:0px;">
						<a href="javascript:void(0);">
							<img src="products/hao/middle01.jpg" width="516px" height="200px" style="display: inline-block;">
						</a>
					</div>
				
						<c:forEach items="${newproducts }" var="newpro">
					<div class="col-md-2" style="text-align:center;height:200px;padding:10px 0px;">
						<a href="${pageContext.request.contextPath }/product?method=product_info&pimage=${newpro.pimage}">
							<img src="${pageContext.request.contextPath }/${newpro.pimage }" width="130" height="130" style="display: inline-block;">
						</a>
						<p><a href="javascript:void(0);" style='color:#666'>${newpro.pname }</a></p>
						<p><font color="#E4393C" style="font-size:16px">&yen;${newpro.shop_price }</font></p>
					</div>
	           </c:forEach>
				</div>
			</div>			
			
			<!-- 引入footer.jsp -->
			<jsp:include page="/footer.jsp"></jsp:include>
			
		</div>
	</body>

</html>