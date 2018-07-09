angular.module('myApp', []).controller('CartForm',
		[ '$scope', function($scope) {

			$scope.size =[{size : "small", price : 1}, {size : "medium", price : 2},{size : "big", price : 2.5}];

			$scope.base =["pita","nun","bread"];
			$scope.sauce =["pesto","bechamel","Salsa"];
			$scope.ingredient =[
				{id :"1",name:"cheese",inventory:"100",price:1.0},
				{id :"2",name:"meatball",inventory:"30",price:3.0},
				{id :"3",name:"mushroom",inventory:"",price:2.5},
				{id :"4",name:"tomato",inventory:"",price:100},
			];

			$scope.invoice = {
				orderdetails : [ {
					size : "medium",
					base : "pita",
					sauce : "pesto",
					ingredient: ["cheese","meatball"],
					qty : 1,
					cost : 0
				} ]
			};
			$scope.addItem = function() {
				$scope.invoice.orderdetails.push({
					size : "medium",
					base : "pita",
					sauce : "pesto",
					ingredient: [],
					qty : 1,
					cost : 0
				});
			};

			$scope.removeItem = function(index) {
				$scope.invoice.orderdetails.splice(index, 1);
			};

			$scope.eachcost = function(index){
				var cost = 0;

				cost += $scope.size.find(x => x.size === $scope.invoice.orderdetails[index].size).price;
				angular.forEach($scope.invoice.orderdetails[index].ingredient,function(eachingredient) {
					cost += $scope.ingredient.find(x => x.name === eachingredient).price;
				})
				$scope.invoice.orderdetails[index].cost = cost; 
				console.log($scope.invoice);
			};

			$scope.total = function() {
				var total = 0;
				angular.forEach($scope.invoice.orderdetails, function(item) {
					total += item.qty * item.cost;
				})

				return total;
			};

			$scope.submitOrder = function() {

			};

		} ]);