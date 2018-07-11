angular.module('myApp', []).controller('CartForm',
		[ '$scope','$http','$window', function($scope,$http,$window) {

			
			$http.get("/ingredient/all")
		    .then(function(response) {
		    	$scope.ingredients = response.data;
		        
		        $scope.eachprice = function(index){
					var price = 0;

					price += $scope.size.find(x => x.size === $scope.order.orderdetails[index].pizza.size).price;
					angular.forEach($scope.order.orderdetails[index].pizza.ingredients,function(eachingredient) {
						price += $scope.ingredients.find(x => x.name === eachingredient).price;
					})
					$scope.order.orderdetails[index].pizza.price = price; 
				};
		    });
			
			$scope.customer = {name :"",phone:"",address:""};
			$scope.size =[{size : "small", price : 1}, {size : "medium", price : 2},{size : "big", price : 2.5}];
			$scope.base =["pita","nun","bread"];
			$scope.sauce =["pesto","bechamel","Salsa"];
			$scope.totalp =0;
			$scope.order = {
				orderdetails : [ {
					pizza :{
						size : "medium",	
						base : "pita",
						sauce : "pesto",
						ingredients: [],
						price :0,
					},
					qty : 1
				} ]
			};
			$scope.addItem = function() {
				$scope.order.orderdetails.push({
					pizza :{
						size : "medium",	
						base : "pita",
						sauce : "pesto",
						ingredients: [],
						price :0,
					},
					qty : 1,
				});
			};

			$scope.removeItem = function(index) {
				$scope.order.orderdetails.splice(index, 1);
			};


			$scope.total = function() {
				var total = 0;
				angular.forEach($scope.order.orderdetails, function(item) {
					total += item.qty * item.pizza.price;
				})
				$scope.totalp =total;

				return total;
			};
			
			
//			function for submit order
			$scope.submitOrder = function() {
				var json ={};
				json.customer = $scope.customer;
				json.orderdetails = $scope.order.orderdetails;
			 	json.total = $scope.totalp;

			 	var parameter = JSON.stringify(json);
			 	
			 	$http.post("/order", parameter)
			 	.then(function(response) {
			 		
			 		$window.alert("Submit success!!")
			 		$window.location.href = '/weborder/' + response.data.id;
			 	}).catch(function (data) {
			 		if(data.status === 400)
			 			$window.alert("inventory is not enough!!");
			 		else
			 			$window.alert("submit fail");
			 		
			    });
			 	
			 };

		} ]);