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
				<form method="get">
					<div class="input-group">
						<!-- /btn-group -->
						<input type="text" class="form-control" name="searchInput" /> <span class="input-group-btn">
							<button class="btn btn-default" type="submit">
								<span class="glyphicon glyphicon-search"></span> GO!
							</button>
						</span>
					</div>
					<!-- /input-group -->
				</form>
			</div>
			<!-- col-lg-9 -->
			<div class="col-lg-3">
				<button class="btn btn-block btn-default" data-toggle="modal" data-target="#newProduct">New Product</button>
			</div>
			<!-- /col-lg-3 -->

			<!-- modal -->
			<div class="modal fade" id="newProduct" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<form:form class="form-horizontal" role="form" action="addNew" method="post" commandName="newProduct">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
								<h4 class="modal-title" id="myModalLabel">Add New Product</h4>
							</div>
							<div class="modal-body">
								<div class="form-group">
									<label for="productEAN" class="col-sm-2 control-label">EAN</label>
									<div class="col-sm-10">
										<form:input path="productEAN" type="text" class="form-control" name="productEAN" required="required" />
									</div>
								</div>
								<div class="form-group">
									<label for="productName" class="col-sm-2 control-label">Name</label>
									<div class="col-sm-10">
										<form:input path="productName" type="text" class="form-control" name="productName" required="required" />
									</div>
								</div>
								<div class="form-group">
									<label for="productMake" class="col-sm-2 control-label">Make</label>
									<div class="col-sm-10">
										<form:input path="productMake" type="text" class="form-control" name="productMake" required="required" />
									</div>
								</div>
								<div class="form-group">
									<label for="productDescribe" class="col-sm-2 control-label">Describe</label>
									<div class="col-sm-10">
										<form:input path="productDescribe" type="text" class="form-control" name="productDescribe" required="required" />
									</div>
								</div>
								<div class="form-group">
									<label for="productSpec" class="col-sm-2 control-label">Spec</label>
									<div class="col-sm-10">
										<form:input path="productSpec" class="form-control" name="productSpec" />
									</div>
								</div>
								<div class="form-group">
									<label for="productSpec" class="col-sm-2 control-label">Retail Price</label>
									<div class="col-sm-10">
										<form:input path="retailPrice" class="form-control" name="retailPrice" />
									</div>
								</div>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
								<button type="submit" class="btn btn-primary">Save changes</button>
							</div>
						</form:form>
					</div>
				</div>
			</div>
			<!-- modal -->
		</div>
		<!-- /.row -->
	</div>
	<!-- container -->

	<div class="container">
		<br />
		<c:forEach var="product" items="${products}">
			<div class="row">

				<div class="col-lg-9">
					<!-- panel -->
					<div class="panel panel-default">
						<!-- Default panel contents -->
						<div class="panel-body">
							<!-- List group -->
							<div class="row">
								<div class="col-lg-3">
									<img src="${product.imageLinks[0].link}" class="img-rounded" style="min-height: 100px; height: 100px;" />
									<br>
									<button class="btn btn-block btn-default" data-toggle="modal" data-target="#img${product.id}">Update Img</button>
								</div>
								<div class="col-lg-9">
									<table class="table">
										<tr>
											<td class="col-lg-3">EAN:</td>
											<td class="col-lg-9">${product.productEAN}</td>
										</tr>

										<tr>
											<td class="col-lg-3">Name:</td>
											<td class="col-lg-9">${product.productName}</td>
										</tr>

										<tr>
											<td class="col-lg-3">Make:</td>
											<td class="col-lg-9">${product.productMake}</td>
										</tr>

										<tr>
											<td class="col-lg-3">Description:</td>
											<td class="col-lg-9">${product.productDescribe}</td>
										</tr>

										<tr>
											<td class="col-lg-3">Spec:</td>
											<td class="col-lg-9">${product.productSpec}</td>
										</tr>

										<tr>
											<td class="col-lg-3">Last Update:</td>
											<td class="col-lg-9">${product.getLastUpdateTimeToString()}</td>
										</tr>

										<tr>
											<td class="col-lg-3">Retail Price:</td>
											<td class="col-lg-9">\$ ${product.retailPrice}</td>
										</tr>
									</table>
									<button class="btn btn-primary btn-default" data-toggle="modal" data-target="&#35;${product.id}">Update</button>
								</div>
							</div>
						</div>
						<!-- panelbody -->
					</div>
					<!-- panel -->



					<!-- Modal -->
					<div class="modal fade" id="${product.id}" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
						<div class="modal-dialog">
							<div class="modal-content">
								<form:form class="form-horizontal" role="form" method="post" commandName="updateProduct">
									<form:hidden name="id" value="${product.id}" path="id" />
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
										<h4 class="modal-title" id="myModalLabel">Update Product</h4>
									</div>
									<div class="modal-body">
										<div class="form-group">
											<label for="productEAN" class="col-sm-2 control-label">EAN</label>
											<div class="col-sm-10">
												<form:input path="productEAN" type="text" class="form-control" name="productEAN" value="${product.productEAN}" required="required" />
											</div>
										</div>
										<div class="form-group">
											<label for="productName" class="col-sm-2 control-label">Name</label>
											<div class="col-sm-10">
												<form:input path="productName" type="text" class="form-control" name="productName" value="${product.productName}" required="required" />
											</div>
										</div>
										<div class="form-group">
											<label for="productMake" class="col-sm-2 control-label">Make</label>
											<div class="col-sm-10">
												<form:input path="productMake" type="text" class="form-control" name="productMake" value="${product.productMake}" required="required" />
											</div>
										</div>
										<div class="form-group">
											<label for="productDescribe" class="col-sm-2 control-label">Describe</label>
											<div class="col-sm-10">
												<form:input path="productDescribe" type="text" class="form-control" name="productDescribe" value="${product.productDescribe}" required="required" />
											</div>
										</div>
										<div class="form-group">
											<label for="productSpec" class="col-sm-2 control-label">Spec</label>
											<div class="col-sm-10">
												<form:input path="productSpec" class="form-control" name="productSpec" value="${product.productSpec}" />
											</div>
										</div>
										<div class="form-group">
											<label for="productSpec" class="col-sm-2 control-label">Retail Price</label>
											<div class="col-sm-10">
												<form:input path="retailPrice" class="form-control" name="retailPrice" value="${product.retailPrice}" />
											</div>
										</div>
									</div>
									<div class="modal-footer">
										<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
										<button type="submit" class="btn btn-primary">Save changes</button>
									</div>
								</form:form>
							</div>
						</div>
					</div>
					<!-- modal product -->



					<!-- modal pic -->
					<div class="modal fade" id="img${product.id}" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
						<div class="modal-dialog">
							<div class="modal-content">
							<div class="modal-body">
							<form action="updateImg" method="post">
								<table class="table">
								<c:forEach var="imgLink" items="${product.imageLinks}">
									<tr>
									<td>
									<img src="${imgLink.link}" class="img-thumbnail" style="min-height: 100px; height: 100px;" />
									
									<a class="btn btn-default" href="deleteImg?imageId=${imgLink.id}">Delete</a>
									</td>
									<tr>
								</c:forEach>
								<tr>
									<td>
									<input type="url" class="form-control" name="imageLink"/> <input type="hidden" name="productId" value="${product.id}" />
								<button type="submit" class="btn btn-default">New Image</button>
								</td>
								</tr>
								</table>
							</form>
							</div>
							</div>
						</div>
					</div>
					<!-- modal pic -->
				</div>
				<!-- col-lg-9 -->

				<div class="col-lg-3">
					<div class="panel panel-default">
						<div class="panel-body">
							<form action="addTag" method="get">
								<table class="table">
									<thead>
										<tr>
											<th>Tags</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="tag" items="${product.tags}">
											<tr>
												<td colspan="2">${tag.tagContent}</td>
											</tr>
										</c:forEach>
									</tbody>
									<tfoot>
										<tr>
											<td><input type="text" class="form-control input-sm" name="newTag" required="required" /> <input type="hidden" name="productId" value="${product.id}" /></td>
											<td>
												<button type="submit" class="btn btn-default btn-sm">Add Tag</button>
											</td>
										<tr>
									</tfoot>
								</table>
							</form>
						</div>
						<!-- panel body -->
					</div>
					<!-- panel -->
				</div>
				<!-- col-lg-3 -->
			</div>
			<!-- row -->
		</c:forEach>

	</div>
	<!-- container -->




	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
	<!-- bootstrap js -->
	<script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>