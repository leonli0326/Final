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
			<div class="col-lg-9 col-lg-offset-2">
				<table class="table">
					<thead>
						<tr>
							<th>#</th>
							<th>Enterprise</th>
							<th>Users</th>
							<th>Stocks</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="enterprise" items="${enterprises}">
							<tr>
								<td>${enterprise.id}</td>
								<td>${enterprise.enterpriseName}</td>
								<td>
									<form action="/spring/enterprise/addUser" method="post">
										<table class="table">
											<c:forEach var="user" items="${enterprise.users}">
												<tr>
													<td>${user.username}</td>
													<td><a href="/spring/enterprise/removeUser?userId=${user.id}&enterpriseId=${enterprise.id}">Remove</a></td>
												</tr>
											</c:forEach>
											<tr>
												<td><input type="text" name="username" required="required"/>
												 <input type="hidden" name="enterpriseId" value="${enterprise.id}" /></td>
												<td><button type="submit" class="btn btn-link">Add</button></td>
											</tr>
										</table>
									</form>
								</td>
								<td>
									<form action="/spring/enterprise/addStock" method="post">
										<table class="table">
											<c:forEach var="stock" items="${enterprise.stocks}">
												<tr>
													<td>${stock.product.productName}</td>
												</tr>
											</c:forEach>
											<tr>
												<td><input type="text" name="productId" required="required" placeholder="product id"/>
												 <input type="hidden" name="enterpriseId" value="${enterprise.id}" /></td>
												<td><button type="submit" class="btn btn-link">Add</button></td>
											</tr>
										</table>
									</form>
								</td>
							</tr>

						</c:forEach>
					</tbody>

				</table>
			</div>
		</div>
	</div>


<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
		<!-- bootstrap js -->
		<script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>