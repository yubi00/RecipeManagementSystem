/*
Name: Yuba Raj Khadka
Student Id: 18040483
Username: 18040483
Course Code: CSE4OAD

*/
var app = angular.module("myApp", []);

app.controller("myController", function($scope, $http)
{
	$scope.id = null;
	$scope.recipe = null;
	$scope.message = null;

	$scope.display = function()
	{
		var connection = $http(
		{
			// TO DO
			method: "get",
				url: "http://localhost:8080/recipeWS/recipes?id=" + $scope.id 
		})

		.then(function(response)
		{
			$scope.recipe = response.data;
			console.log($scope.recipe);
		})

		.catch(function(response)
		{
			// It is OK to do nothing here
			// It is clear if operation succeeds or not
		})

		.finally(function(config)
		{
			// It is OK to do nothing here
			// It is clear if operation succeeds or not
		})
		// end http

	};// end display


	// start edit
	$scope.edit = function()
	{
		var connection = $http(
		{
			// TO DO
			method: "put",
				url: "http://localhost:8080/recipeWS/recipes",
			data:
					{
						
						"id": $scope.recipe.id,
						"name": $scope.recipe.name,
						"serves": $scope.recipe.serves,
						"ingredients": $scope.recipe.ingredients,
						"remarks": $scope.recipe.remarks,
						"steps": $scope.recipe.steps
					}
		})

		.then(function(response)
		{
			// TO DO
			$scope.message = "Message for Edit recipe: Success - status: " + response.status;
		})

		.catch(function(response)
		{
			// TO DO
			$scope.message = "Message for Edit recipe: Error - status: " + response.status +
				": " + response.statusText;
		})

		.finally(function(config)
		{
			// TO DO
			alert($scope.message);
		})
		// end http

	};
	// end edit

});
//end controller