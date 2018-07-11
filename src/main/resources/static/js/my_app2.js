angular.module('myApp', []).controller('CartForm',
		[ '$scope','$http','$window', function($scope,$http,$window) {
			
			$http.get("/ingredient/all")
		    .then(function(response) {
		    	$scope.ingredients = response.data;
		    });
			
			$scope.cur_ing ={
					id : "",
					name : "",
					inventory : 0,
					price: 0.0,
			};

			$scope.modify = function(id, name, inventory, price){
				$scope.cur_ing.id = id;
				$scope.cur_ing.name = name;
				$scope.cur_ing.inventory =inventory;
				$scope.cur_ing.price = price;
			};

			$scope.deleteIngredient = function(){
				if($scope.cur_ing.id === ""){
					$window.alert("You must select one ingredient to delete!")
				}else{
					$http.delete("/ingredient/" + $scope.cur_ing.id)
					.then(function(response) {
						$window.alert("Delete success!!")
				 		$window.location.href = '/webingredient';
					}).catch(function(data) {
						$window.alert("Delete Fail!!");
					});
				}
			}

			$scope.submitIngredient = function() {
				
				var parameter = JSON.stringify($scope.cur_ing);
//			 	return json
			 	
			 	if($scope.cur_ing.id === ""){
			 		$scope.cur_ing.id = "null";
			 		$http.post("/ingredient", parameter)
				 	.then(function(response) {
				 		
				 		$window.alert("Submit success!!")
				 		$window.location.href = '/webingredient';
				 	}).catch(function (data) {
				 		if(data.status === 409)
				 			$window.alert("ingredient conflict");
				 		else
				 			$window.alert("conflict"); 		
				    });
			 		
			 	}else{
			 		$http.put("/ingredient", parameter)
				 	.then(function(response) {
				 		
				 		$window.alert("Update success!!")
				 		$window.location.href = '/webingredient';
				 	}).catch(function (data) {
				 		$window.alert("conflict:" +  data.status);
				    });
			 		
			 	}
			 };

		} ]);