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
<script src="/resources/js/my_app.js"></script>




<style type="text/css">
.header, .message {
	margin-bottom: 20px;
}

th, td {
	text-align: center;
}
</style>

</head>
<body ng-app="myApp">



	<div class="container">

		<div class="col-md-12 header">
			<h1 align="center">
				<a href="<%=request.getContextPath()%>/">Pizza APP</a>
			</h1>
		</div>




		<div class="col-md-12" style="text-align: center; margin-top: 20px;">
			<div class="panel panel-success">
				<div class="panel-heading">
					<h3>
						<i class="fa fa-cart-plus"></i> Cart
					</h3>
				</div>
				<div class="panel-body">
					<div ng:controller="CartForm">
						<table class="table">
							<tr>

								<th>Size</th>
								<th>Base</th>
								<th>Sauce</th>
								<th>ingredients</th>
								<th>Qty</th>
								<th>Cost</th>
								<th>Total</th>
								<th></th>
							</tr>
							<tr ng:repeat="item in invoice.orderdetails">
								<td><select ng-model="item.size">
										<option ng-repeat="x in size" value="{{x.size}}">{{x.size}}</option>
								</select></td>
								<td><select ng-model="item.base">
										<option ng-repeat="x in base" value="{{x}}">{{x}}</option>
								</select></td>
								<td><select ng-model="item.sauce">
										<option ng-repeat="x in sauce" value="{{x}}">{{x}}</option>
								</select></td>
								<td><select multiple class="form-control"
									ng-model="item.ingredient">
										<option ng-repeat="x in ingredient" value="{{x.name}}">{{x.name}}</option>
								</select></td>
								<td><input type="number" ng:model="item.qty" ng:required
									class="input-mini"></td>
								<td><input type="number" ng:model="item.cost" ng:required
									class="input-mini">{{eachcost($index)}}</td>
								<!-- <td>{{eachcost($index)}}</td>	 -->
								<td>{{item.qty * item.cost | currency}}</td>
								<td>[<a ng:click="removeItem($index)">X</a>]
								</td>
							</tr>
							<tr>
								<td><a ng:click="addItem()" class="btn btn-small">add
										item</a></td>
								<td></td>
								<td>Total:</td>
								<td>{{total() | currency}}</td>
							</tr>
							<tfoot>
								<tr ng-show="cartProduct.length != 0">
									<td colspan="6" style="text-align: right">
										<button data-toggle="modal" data-target="#checkoutModal"
											type="button" ng-show="cartProduct.length != 0"
											class="btn btn-success">
											<i class="fa fa-check"></i> Check Out
										</button>
									</td>
								</tr>
							</tfoot>
						</table>
						</d
					</div>
				</div>


				<div class="col-md-12" style="text-align: center; margin-top: 60px;">
					&copy; Wei-Nung Chao</div>

				<!-- Modal -->
				<%-- <div id="messageModal" class="modal fade" role="dialog">
                <div class="modal-dialog">

                    <!-- Modal content-->
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                            <h4 class="modal-title" align="center">Congratulation</h4>
                        </div>
                        <div class="modal-body">
                            <h1 style="color: green; text-align: center">
                                Order Complete Successfully
                            </h1>
                        </div>
                        <div class="modal-footer">
                            <a href="<%= request.getContextPath()%>" class="btn btn-default">Close</a>
                        </div>
                    </div>

                </div>
            </div> --%>


			</div>
</body>



</html>
