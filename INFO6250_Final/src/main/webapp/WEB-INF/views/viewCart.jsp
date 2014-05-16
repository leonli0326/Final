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

	<div class="container">
		<div class="row">
			<div class="col-lg-9">
				<div class="panel panel-default">
					<div class="panel-heading lead">${sessionScope.cart.size()} items in your cart</div>
					<div class="panel-body">
						<c:forEach var="entry" items="${sessionScope.cart}">
							<div class="row">
								<div class="col-lg-3">
									<a href="/spring/shopping/productDetail?productId=${entry.key.product.id}" class="thumbnail">
										<img src="${entry.key.product.imageLinks[0].link}" style="min-height: 80px; height: 80px;" />
									</a>
								</div>
								<div class="col-lg-9">
									<span class="lead"> <a href="/spring/shopping/productDetail?productId=${entry.key.product.id}">${entry.key.product.productName}</a>
									</span> <br /> <span class="text-success"><b>\$ ${entry.key.product.retailPrice}</b></span> <br />

									<form action="/spring/cart/changeAmount" method="post">

										<c:if test="${entry.value<entry.key.stockCount and entry.value>0}">
											<span>Amount:</span>
											<input type="number" name="changeAmount" min="1" value="${entry.value}" max="${entry.key.stockCount}" style="width: 40px;" />
											<input type="hidden" name="changeId" value="${entry.key.id}" />
											<button class="btn btn-xs" type="submit">Update</button>
										</c:if>
										<c:if test="${entry.value>entry.key.stockCount or entry.value<0}">
											<span class="text-warning">Out of stock!</span>
										</c:if>


									</form>
									<a href="/spring/cart/delete?deleteId=${entry.key.id}">Delete</a>
								</div>
							</div>
						</c:forEach>
					</div>
				</div>
			</div>
			<div class="col-lg-3">
				<table class="table">
					<tr>
						<td><span class="lead">Subtotal (before Tax):</span></td>
					</tr>
					<tr>
						<td><span class="text-success lead">\$ ${beforeTax}</span></td>
					</tr>
					<tr>
						<td><span class="lead">Subtotal (after Tax):</span></td>
					</tr>
					<tr>
						<td><span class="text-success lead">\$ ${afterTax}</span></td>
					</tr>
				</table>
				<p>
				<c:if test="${sessionScope.cart.size()==0}">
					<a class="btn btn-block btn-primary btn-success disabled" href="/spring/order/place">Proceed to Checkout</a>
					</c:if>
					<c:if test="${sessionScope.cart.size()>0}">
					<a class="btn btn-block btn-primary btn-success" href="/spring/order/place">Proceed to Checkout</a>
					</c:if>
				</p>
				<p>
					<a class="btn btn-block btn-primary btn-default" href="/spring/">Continue Shopping</a>
				</p>
			</div>
		</div>
		<!-- row -->
	</div>
	<!-- container -->

	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
	<!-- bootstrap js -->
	<script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>