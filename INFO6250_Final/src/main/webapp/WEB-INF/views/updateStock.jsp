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
		<c:forEach var="entry" items="${map.entrySet()}">
			<div class="row">
				<div class="col-lg-6">

					<div class="panel panel-default">
						<!-- Default panel contents -->
						<div class="panel-body">
							<!-- List group -->
							<div class="row">
								<div class="col-lg-3">
									<img src="${entry.key.imageLinks[0].link}" style="min-height: 64px; height: 64px;" />
								</div>
								<div class="col-lg-9">
									<table class="table">
										<tr>
											<td>EAN:</td>
											<td>${entry.key.productEAN}</td>
										</tr>

										<tr>
											<td>Name:</td>
											<td>${entry.key.productName}</td>
										</tr>

										<tr>
											<td>Make:</td>
											<td>${entry.key.productMake}</td>
										</tr>

										<tr>
											<td>Description:</td>
											<td>${entry.key.productDescribe}</td>
										</tr>

										<tr>
											<td>Spec:</td>
											<td>${entry.key.productSpec}</td>
										</tr>

										<tr>
											<td>Retail Price:</td>
											<td>\$ ${entry.key.retailPrice}</td>
										</tr>
									</table>
								</div>
							</div>
						</div>
						<!-- panelbody -->
					</div>
					<!-- panel -->
				</div>
				<!-- col-lg-6 -->
				
				<div class="col-lg-6">
					<div class="panel panel-default">
						<div class="panel-body">
							<form method="post">
								<table class="table">
									<thead>
										<tr>
											<th>Location</th>
											<th>Stock Count</th>
											<th>Supply Price</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="stock" items="${entry.value}">
											<tr>
												<td>${stock.location.toString()}</td>
												<td><input type="number" class="form-control input-sm" name="${stock.id}" value="${stock.stockCount}" step="1" required="required"></td>
												<td><input type="number" class="form-control input-sm" name="${stock.id}" value="${stock.price}" step="0.01" required="required"></td>
											</tr>
										</c:forEach>
									</tbody>
									<tfoot>
										<tr>
										<td><a class="btn btn-default" data-toggle="modal" data-target="&#35;${entry.key.id}">Add new Stock</a></td>
										<td></td>
											<td><button type="submit" class="btn btn-block btn-primary">Update Stock</button></td>
										</tr>
									</tfoot>
								</table>
							</form>
						</div>
						<!-- panel body -->
					</div>
					<!-- panel -->
					
				</div>

				<!-- col-lg-6 -->
			</div>
			
			<!-- modal -->
			<div class="modal fade" id="${entry.key.id}" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				<form action="add" method="post">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
							<h4 class="modal-title" id="myModalLabel">Modal title</h4>
						</div>
						<div class="modal-body">
							<p class="lead">For:${entry.key.productName}</p>

							<div class="form-group">
								<label for="stockCount">Stock Count</label> <input type="number" class="form-control" name="stockCount" min="1" required="required">
							</div>
							<div class="form-group">
								<label for="price">Supply price</label> <input type="number" class="form-control" name="price" min="0" step="0.01" required="required">
							</div>
							<div class="form-group">
								<label for="taxRate">Tax Rate (%)</label> <input type="number" class="form-control" name="taxRate" min="0" step="0.01" required="required">
							</div>
							<div class="form-group">
								<label for="selectedLocationId">City: </label> <select class="form-control" name="selectedLocationId">
									<c:forEach var="location" items="${locations}">
										<option value="${location.id}">${location.toString()}</option>
									</c:forEach>
								</select>
							</div>
							<input type="hidden" name="productId" value="${entry.key.id}" />

						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
							<button type="submit" class="btn btn-primary">Save changes</button>
						</div>
					</div>
				</div>
				</form>
			</div>
			<!-- modal -->

		</c:forEach>
		<!-- row -->
	</div>





	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
	<!-- bootstrap js -->
	<script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>