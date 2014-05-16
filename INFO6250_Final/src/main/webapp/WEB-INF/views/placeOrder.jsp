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

				<div class="row">
					<div class="col-lg-6">
						<div class="panel panel-default">
							<div class="panel-heading">Shipping address:</div>
							<div class="panel-body">
								<c:choose>
									<c:when test="${userAddressesList.size()==0}">
										<a href="/spring/address/update?fromUrl=/order/place" class="btn btn-default btn-sm">Add address</a>
									</c:when>
									<c:otherwise>
									${sessionScope.selectedAddress.toString()}<br />
										<br />
										<button type="button" data-toggle="modal" data-target="#changeAddress" class="btn btn-default btn-sm">Change Address</button>
									</c:otherwise>
								</c:choose>
							</div>
						</div>
					</div>
					<!-- col-lg-6 -->
					<div class="col-lg-6">
						<div class="panel panel-default">
							<div class="panel-heading">Payment Method:</div>
							<div class="panel-body">
								<c:choose>
									<c:when test="${userPaymentMethodList.size()==0}">
										<a href="/spring/payment/update?fromUrl=/order/place" class="btn btn-default btn-sm">Add Payment Method</a>
									</c:when>
									<c:otherwise>
									${sessionScope.selectedPaymentMethod.toString()}<br />
										<br />
										<button type="button" data-toggle="modal" data-target="#changePaymentMethod" class="btn btn-default btn-sm">Change Payment Method:</button>
									</c:otherwise>
								</c:choose>
							</div>
						</div>
					</div>
					<!-- col-lg-6 -->
				</div>
				<!-- row -->

				<div class="row">
					<div class="col-lg-12">
						<div class="panel panel-default">
							<!-- Default panel contents -->
							<div class="panel-heading">Order Items</div>
							<!-- List group -->
							<table class="table">
								<c:forEach var="entry" items="${sessionScope.cart}">
									<tr>
										<td>
											<div class="col-lg-3">
												<img src="${entry.key.product.imageLinks[0].link}" style="min-height: 64px; height: 64px;" />
											</div>
											<div class="col-lg-9">
												${entry.key.product.productName} <br /> <span class="text-success"><b>\$ ${entry.key.price}</b></span> <br /> 
												<c:if test="${entry.value>0}">Amount: ${entry.value}</c:if> 
												<c:if test="${entry.value<=0}"><span class="text-danger">this product is out of stock in your selected address!</span></c:if> 
											</div>
										</td>
									</tr>
								</c:forEach>
							</table>
						</div>
						<!-- panel -->
					</div>
					<!-- col-lg-12 -->
				</div>
				<!-- row -->

			</div>
			<!-- col-lg-9 -->


			<div class="col-lg-3">
				<table class="table">
					<tr>
						<td><span class="lead">Subtotal (before Tax):</span></td>
					</tr>
					<tr>
						<td><span class="text-success lead">\$ ${beforeTax}</span></td>
					</tr>
					<tr>
						<td><span class="lead">Tax :</span></td>
					</tr>
					<tr>
						<td><span class="text-success lead">\$ ${tax}</span></td>
					</tr>
					<tr>
						<td><span class="lead">Subtotal (after Tax):</span></td>
					</tr>
					<tr>
						<td><span class="text-success lead">\$ ${afterTax}</span></td>
					</tr>
				</table>
				<p>
				<c:if test="${confirmable}">
				<a class="btn btn-block btn-primary btn-success" href="/spring/order/confirm">Confirm Order</a>				
				</c:if>
				<c:if test="${not confirmable}">
				<a class="btn btn-block btn-primary btn-success disabled" href="#" >Confirm Order</a>
				<em class="text-danger">Please select shipping address and payment method first</em>				
				</c:if>
					
				</p>
			</div>
			<!-- col-lg-3 -->

		</div>
		<!-- row -->
		<a class="btn btn-default" href="/spring/cart/view">Edit Cart</a>
	</div>
	<!-- container -->

	<!-- Modal address-->
	<div class="modal fade" id="changeAddress" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">Change address</h4>
				</div>
				<div class="modal-body">
					<table class="table">
						<c:forEach var="address" items="${userAddressesList}">
							<tr>
								<td>${address.toString()}</td>
								<td><a href="/spring/order/place?selectedAddressId=${address.id}">Choose this</a></td>
							</tr>
						</c:forEach>
					</table>
					<a href="/spring/address/update?fromUrl=/order/place" class="btn btn-default">Add new address</a>
				</div>
			</div>
		</div>
	</div>
	<!-- Modal address -->

	<!-- Modal payment method-->
	<div class="modal fade" id="changePaymentMethod" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">Change address</h4>
				</div>
				<div class="modal-body">
					<table class="table">
						<c:forEach var="paymentMethod" items="${userPaymentMethodList}">
							<tr>
								<td>${paymentMethod.toString()}</td>
								<td><a href="/spring/order/place?selectedPaymentMethodId=${paymentMethod.id}">Choose this</a></td>
							</tr>
						</c:forEach>
					</table>
					<a href="/spring/payment/update?fromUrl=/order/place" class="btn btn-default">Add new Payment</a>
				</div>
			</div>
		</div>
	</div>
	<!-- Modal payment method-->

	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
	<!-- bootstrap js -->
	<script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>