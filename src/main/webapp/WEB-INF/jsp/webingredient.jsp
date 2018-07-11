<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Pizza APP</title>
<link rel="stylesheet" href="/resources/css/bootstrap.css">
<link rel="stylesheet" type="text/css"
	href="/resources/css/bootstrap.css" />
<link rel="stylesheet" type="text/css"
	href="/resources/css/font-awesome.css" />
<script src="/resources/js/jquery.min.js"></script>
<script src="/resources/js/bootstrap.js"></script>
<script src="/resources/js/angular.min.js"></script>
<script src="/resources/js/my_app2.js"></script>



<style type="text/css">
.header, .message {
	margin-bottom: 20px;
}

th, td {
	text-align: center;
}
</style>



<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body ng-app="myApp">
	<div class="container">
		<div class="col-md-12 header">
			<h1 align="center">
				<a href="/">Add Ingredient</a>
			</h1>
		</div>
		<div class="col-md-12" style="text-align: center; margin-top: 20px;"
			ng:controller="CartForm">
			<div class="panel panel-success">
				<div class="panel-heading">
					<h3>
						<i class="fa fa-cart-plus"></i> Ingredients
					</h3>
				</div>

				<div class="panel-body">
					<div>
						<table class="table">
							<tr>

								<th>name</th>
								<th>inventory</th>
								<th>price</th>
								<th></th>
							</tr>
							<tr ng:repeat="item in ingredients">


								<td><input type="text" ng-model="item.name"
									class="input-mini" disabled></td>
								<td><input type="number" ng-model="item.inventory"
									class="input-mini" disabled></td>
								<td><input type="number" ng-model="item.price"
									class="input-mini" disabled></td>

								<td><button type="button" class="btn btn-success"
										ng:click="modify(item.id,item.name,item.inventory,item.price)">Modify</button></td>
							</tr>
						</table>
						</d>
					</div>
				</div>


			</div>
			<div class="panel panel-default">
				<div class="panel-heading">
					<h3>
						<i class="fa fa-cart-plus"></i> Modify or Add ingredient
					</h3>
				</div>

				<div class="panel-body">
					<div>
						<table class="table">
							<tr>

								<th>name</th>
								<th>inventory</th>
								<th>price</th>
								<th></th>
							</tr>
							<tr>


								<td><input type="text" ng-model="cur_ing.name" ng:required
									class="input-mini"></td>
								<td><input type="number" ng-model="cur_ing.inventory"
									ng:required class="input-mini"></td>
								<td><input type="number" ng-model="cur_ing.price"
									ng:required class="input-mini"></td>
								<td></td>
							</tr>

							<tfoot>
								<tr ng-show="cartProduct.length != 0">
									<td colspan="6" style="text-align: right">
										<button data-toggle="modal" ng:click="deleteIngredient()"
											type="button" class="btn btn-danger">
											<i class="fa fa-times"></i> Delete
										</button>
										<button data-toggle="modal" ng:click="submitIngredient()"
											type="button" class="btn btn-success">
											<i class="fa fa-check"></i> Submit
										</button>
									</td>
								</tr>
							</tfoot>
						</table>

					</div>
				</div>
			</div>
		</div>


	</div>

</body>
</html>