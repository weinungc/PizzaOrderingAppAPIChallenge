<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Your Order</title>
<link rel="stylesheet" href="/resources/css/bootstrap.css">
<link rel="stylesheet" type="text/css"
	href="/resources/css/bootstrap.css" />
<link rel="stylesheet" type="text/css"
	href="/resources/css/font-awesome.css" />
<script src="/resources/js/jquery.min.js"></script>
<script src="/resources/js/bootstrap.js"></script>

</head>
<body>
	<div class="container">
		<div class="col-md-12 header">
			<h1 align="center">
				<a href="<%=request.getContextPath()%>/">Your Order</a>
			</h1>
		</div>
		
		<div class="col-md-12" style="text-align: center; margin-top: 20px;">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h3>
						<i class="fa fa-cart-plus"></i> Information
					</h3>
				</div>
				<div class="panel-body">
					<div class="form-group">
					  	<label for="usr">Name:</label>
					  	<input type="text" class="form-control" value ="${order.customer.name}" disabled>
					</div>
					<div class="form-group">
					  	<label for="usr">Phone:</label>
					  	<input type="text" class="form-control" value ="${order.customer.phone}" disabled >
					</div>
					<div class="form-group">
					  	<label for="usr">Address:</label>
					  	<input type="text" class="form-control" value ="${order.customer.address}" disabled>
					</div>
				</div>
			</div>
			
			<div class="panel panel-success">
				<div class="panel-heading">
					<h3>
						<i class="fa fa-cart-plus"></i> Cart
					</h3>
				</div>
				<div class="panel-body">
					<div>
						<table class="table">
							<tr>

								<th>Size</th>
								<th>Base</th>
								<th>Sauce</th>
								<th>ingredients</th>
								<th>Qty</th>
								<th>Price</th>
								<th>Total</th>
								<th></th>
							</tr>
							<c:forEach items="${order.orderdetails}" var="orderdetail">
							<tr>
								<td><input type="text" class="form-control" value ="${orderdetail.pizza.size}" disabled></td>
								<td><input type="text" class="form-control" value ="${orderdetail.pizza.base}" disabled></td>
								<td><input type="text" class="form-control" value ="${orderdetail.pizza.sauce}" disabled></td>
								<td>
									<select multiple class="form-control" disabled>
										<c:forEach items="${orderdetail.pizza.ingredients}" var="ingredient">
											<option value="${ingredient}">${ingredient}</option>
										</c:forEach>
									</select>
									
								</td>
								<td><input type="text" class="form-control" value ="${orderdetail.qty}" disabled></td>
								<td><input type="text" class="form-control" value ="${orderdetail.pizza.price}" disabled></td>
								<td><input type="text" class="form-control" value ="${orderdetail.pizza.price * orderdetail.qty}" disabled></td>
							</tr>
							</c:forEach>						
							<tr>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td>Total:</td>
								<td>${order.total}</td>
							</tr>
						</table>
						
					</div>
				</div>


				<div class="col-md-12" style="text-align: center; margin-top: 60px;">
					&copy; Wei-Nung Chao</div>
			</div>
			
		</div>
		
	</div>

</body>
</html>