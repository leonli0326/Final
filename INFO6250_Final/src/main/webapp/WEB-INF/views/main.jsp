<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Main</title>
<!-- Bootstrap -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
<!-- Bootstrap Theme-->
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap-theme.min.css">
<!-- custom css-->
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/custom.css">
</head>
<body>
	<form method="post">
		<!-- Fixed navbar begin-->
		<div class="navbar navbar-default navbar-fixed-top" role="navigation">
			<div class="container">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
						<span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span>
					</button>
					<a href="/spring"><img class="navbar-brand navbar-left" src="${pageContext.request.contextPath}/resources/Logo.png"></a>
				</div>

				<div class="collapse navbar-collapse">
					<sec:authorize access="isAuthenticated()">
						<ul class="nav navbar-nav navbar-right">
							<li class="navbar-text">Welcome! <sec:authentication property="principal.username" /></li>
							<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown">
									My Account<b class="caret"></b>
								</a>
								<ul class="dropdown-menu">
									<sec:authorize access="hasRole('ADMIN')">
										<li class="divider"></li>
										<li><a href="/spring/admin">Manage Authority</a></li> <li><a href="/spring/enterprise">Manage Enterprise</a></li>
									</sec:authorize>
									<sec:authorize access="hasRole('SUPPLIER')">
										<li class="divider"></li>
										<li><a href="/spring/stock/update">Manage Stock</a></li>
										<li><a href="/spring/logistic/package">Manage Delivery</a></li>
									</sec:authorize>
									<sec:authorize access="hasRole('PRODUCTMANAGER')">
										<li class="divider"></li>
										<li><a href="/spring/product/update">Manage Product</a></li>
									</sec:authorize>
									<sec:authorize access="hasRole('CUSTOMER')">
										<li class="divider"></li>
										<li><a href="/spring/order/view">My Orders</a></li>
										<li><a href="/spring/payment/update">My Payment</a></li>
										<li><a href="/spring/address/update">My Address</a></li>
									</sec:authorize>
									<li class="divider"></li>
									<li><a href="/spring/changePassword">Change Password</a></li>
									
								</ul></li>
							<li><a href="/spring/cart/view">My Cart</a></li>
							<li><a href="<c:url value="j_spring_security_logout" />">Logout</a></li>
						</ul>
					</sec:authorize>
					<sec:authorize access="!isAuthenticated()">
						<ul class="nav navbar-nav navbar-right">
							<li><a href="/spring/userRegister">New User? Register Now!</a></li>
							<li><a href="/spring/cart/view">My Cart</a></li>
							<li><a href="/spring/login">Login</a></li>
						</ul>
					</sec:authorize>
				</div>

				<!--/.nav-collapse -->
			</div>
			<!-- container -->
		</div>
		<!-- Fixed navbar end-->
		<!-- Search Bar start -->
		<div class="container">

			<div class="row">
				<div class="col-lg-12">
					<div class="input-group">
						<div class="input-group-btn">
							<a class="btn btn-default dropdown-toggle" href="/spring">
								Search All<span class="caret"></span>
							</a>
						</div>
						<!-- /btn-group -->
						<input type="text" class="form-control" name="searchInput" /> <span class="input-group-btn">
							<button class="btn btn-default" type="submit">
								<span class="glyphicon glyphicon-search"></span> GO!
							</button>
						</span>

					</div>
					<!-- /input-group -->
				</div>
				<!-- /.col-lg-6 -->
			</div>
			<!-- /.row -->

		</div>
		<!-- /.container -->
		<!-- Search Bar end -->
		<br />
		<div class="container">
			<div class="row">
				<!-- List Group start-->
				<div class="col-lg-3">
					<ul class="list-group">
						<li class="list-group-item active"><span class="whiteText">Popular Search</span> <c:forEach var="tag" items="${tags}">
								<li class="list-group-item">${tag.tagContent}<input class="pull-right" type="checkbox" name="selectedTags" value="${tag.tagContent}"></li>
							</c:forEach>
						<li class="list-group-item active"><span class="whiteText">Price Range</span>
						<li class="list-group-item"><input class="form-control" type="range" name="priceRange" min="0" max="100" oninput="amount.value=priceRange.value"> <output class="form-inline"
								name="amount" for="rangeInput"
							>50</output></li>
						<li class="list-group-item active"><span class="whiteText">Sorting</span>
						<li class="list-group-item"><a href="/spring?searchInput=${searchInput}&sortFileName=retailPrice&reverse=false">Sort by Price</a></li>
						<li class="list-group-item"><a href="/spring?searchInput=${searchInput}&sortFileName=reviewScore&reverse=false">Sort by Review</a></li>
						<li class="list-group-item"><a href="/spring?searchInput=${searchInput}&sortFileName=lastUpdateTime&reverse=false">Sort by Release Date</a></li>
					</ul>
				</div>
				<!-- List Group end-->
				<!-- col -->
				<!-- main content start-->
				<div class="col-lg-9">
					<div class="row">
						<div class="col-lg-9">
							<a href="/spring/shopping/productDetail?productId=${products[0].id}" class="thumbnail">
								<img src="${products[0].imageLinks[0].link}" style="min-height: 300px; height: 300px;" />
							</a>
						</div>
						<div class="col-lg-3">
							<p class="lead">
								<a href="/spring/shopping/productDetail?productId=${products[0].id}">${products[0].productName}</a>
							</p>
							<div class="progress">
									<div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="${products[0].reviewScore}" aria-valuemin="0" aria-valuemax="5" style="width: ${products[0].reviewScore/0.05}%;">${products[0].reviewScore}</div>
								</div>
							<h4 class="text-success">\$ ${products[0].retailPrice}</h4>
							<p>${products[0].productDescribe}</p>
						</div>
					</div>
					<br>
					<!-- for each -->
					<c:forEach var="product" begin="1" step="1" items="${products}">
						<div class="row">
							<div class="col-lg-6">
								<a href="/spring/shopping/productDetail?productId=${product.id}" class="thumbnail">
									<img src="${product.imageLinks[0].link}" style="min-height: 200px; height: 200px;" />
								</a>
							</div>
							<div class="col-lg-6">
								<p class="lead">
									<a href="/spring/shopping/productDetail?productId=${product.id}">${product.productName}</a>
								</p>
								<div class="progress">
									<div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="${product.reviewScore}" aria-valuemin="0" aria-valuemax="5" style="width: ${product.reviewScore/0.05}%;">${product.reviewScore}</div>
								</div>
								<h4 class="text-success">\$ ${product.retailPrice}</h4>
								<p>${product.productDescribe}</p>
							</div>
						</div>
					</c:forEach>
				</div>
				<!-- main content end-->
			</div>
			<!-- row -->
		</div>
		<!-- container -->
		<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
		<!-- bootstrap js -->
		<script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
	</form>
</body>
</html>