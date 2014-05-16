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
			<div class="col-lg-12">
				<h2>${product.productName}</h2>
				<p>Provide by ${product.productMake}</p>
				<div class="progress">
									<div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="${product.reviewScore}" aria-valuemin="0" aria-valuemax="5" style="width: ${product.reviewScore/0.05}%;">${product.reviewScore}</div>
								</div>
				<p>
					<c:forEach var="tag" items="${product.getTagByRanking()}">
						<a href="/spring?searchInput=${tag.tagContent}">
							&nbsp&nbsp${tag.tagContent}<span class="badge">${tag.ranking}</span>
						</a>
					</c:forEach>
				</p>
			</div>
		</div>
		<c:if test="${previousOrder!=null && !isReviewed}">
			<div class="row">
				<div class="col-lg-12">
					<div class="panel panel-default">
						<div class="panel-body">
							You've order this item in <span class="text-success">${previousOrder.getOrderTimeToString()}</span> ,do you want to give a
							<button class="btn btn-link" data-toggle="modal" data-target="#review">review</button>
							?
						</div>
					</div>
				</div>
			</div>
		</c:if>
		<c:if test="${isReviewed}">
			<div class="row">
				<div class="col-lg-12">
					<div class="panel panel-default">
						<div class="panel-body">Thanks for your review on this product!</div>
					</div>
				</div>
			</div>
		</c:if>
		<div class="row">
			<div class="col-lg-12">
				<div class="panel panel-default">
					<div id="productImg" class="carousel slide" data-ride="carousel">
						<div class="carousel-inner">
							<c:forEach var="imageLink" items="${product.imageLinks}" varStatus="ilIndex">
								<div class="item <c:if test="${ilIndex.index==0}">active</c:if>">
									<img src="${imageLink.link}" style="min-height: 400px; height: 400px;">
								</div>
							</c:forEach>
						</div>

						<!-- Controls -->
						<a class="left carousel-control" href="#productImg" data-slide="prev">
							<span class="glyphicon glyphicon-chevron-left"></span>
						</a>
						<a class="right carousel-control" href="#productImg" data-slide="next">
							<span class="glyphicon glyphicon-chevron-right"></span>
						</a>
					</div>
					<!-- carousel -->
				</div>
				<!-- panel -->
			</div>
		</div>
		<div class="row">
			<div class="col-lg-6">
				<p class="lead">
					Price From: <span class="text-success">\$ ${product.retailPrice}</span>
				</p>
				<p>Available stock: ${selectedStock.stockCount} by <em class="text-info">${selectedStock.enterprise.enterpriseName}</em></p>
				<i>Choose your location to check valid stock:</i>
				<div class="dropdown">
					<a id="dLabel" role="button" data-toggle="dropdown" data-target="#" href="/page.html">
						Location <span class="caret"></span>
					</a>
					<ul class="dropdown-menu" role="menu" aria-labelledby="dLabel">
						<c:forEach var="stock" items="${stocks}">
							<li><a href="/spring/shopping/productDetail?productId=${product.id}&selectedStockId=${stock.id}">${stock.location.toString()}</a></li>
						</c:forEach>
					</ul>
				</div>
			</div>
			<div class="col-lg-6">
				<form method="post" class="form-inline pull-right">
					<div class="form-group">
						Amount: <input type="number" class="form-control" value="1" min="1" max="${stock.stockCount}" name="amountToCart">
						<button type="submit" class="btn btn-primary">Add to Cart</button>
					</div>
				</form>
			</div>
		</div>
		<br />
		<div class="row">
			<table class="table table-striped">
				<thead>
					<tr>
						<td>Product Description</td>
					</tr>
					<tr>
						<td>${product.productDescribe}</td>
					</tr>
				</thead>
			</table>
		</div>
		<div class="row">
			<table class="table table-striped">
				<thead>
					<tr>
						<td>Product Spec</td>
					</tr>
					<tr>
						<td>${product.productSpec}</td>
					</tr>
				</thead>
			</table>
		</div>
		<div class="row">
			<table class="table table-striped">
				<thead>
					<tr>
						<td>Product Spec</td>
					</tr>
					<c:forEach var="review" items="${product.reviews}">
						<tr>
							<td>${review.toString()}</td>
						</tr>
					</c:forEach>
				</thead>
			</table>
		</div>
	</div>



	<!-- Modal -->
	<div class="modal fade" id="review" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<form action="/spring/shopping/review" method="post" class="form-horizontal">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalLabel">Review</h4>
					</div>
					<div class="modal-body">

						<div class="form-group">
							<label for="tag" class="col-sm-2 control-label">Add Tag</label>
							<div class="col-sm-4">
								<input type="text" class="form-control" name=tag placeholder="e.g.awsome">
							</div>
						</div>
						<div class="form-group">
							<label for="score" class="col-sm-2 control-label">Score</label>
							<div class="col-sm-3">
								<input type="number" class="form-control" name=score min="1" max="5" step="1">
							</div>
						</div>
						<div class="form-group">
							<label for="review" class="col-sm-2 control-label">Review</label>
							<div class="col-sm-10">
								<textarea rows="3" class="form-control" name=review></textarea>
							</div>
						</div>
						<input type="hidden" name="productId" value="${product.id}" />

					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
						<button type="submit" class="btn btn-primary">Confirm</button>
					</div>
				</div>
			</form>
		</div>
	</div>

	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
	<!-- bootstrap js -->
	<script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>