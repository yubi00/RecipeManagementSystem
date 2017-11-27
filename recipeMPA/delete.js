/*
Name: Yuba Raj Khadka
Student Id: 18040483
Username: 18040483
Course Code: CSE4OAD

*/
var app = angular.module("myApp", []);

app.controller("myController", function($scope, $http)
{
	// $scope.id = null;
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
			// TO DO
			$scope.recipe = response.data;
			console.log($scope.recipe);
		})

		.catch(function(response)
		{
			// OK to do nothing
		})

		.finally(function(config)
		{
			// OK to do nothing
		})
		// end http

	};// end display


	// start delete
	$scope.delete = function()
	{
		var connection = $http(
		{
			// TO DO
			method: "delete",
				url: "http://localhost:8080/recipeWS/recipes?id=" + $scope.id 
		})

		.then(function(response)
		{
			// TO DO
			$scope.message = "Message for Delete recipe: Success - status: "
				+ response.status;
		})

		.catch(function(response)
		{
			// TO DO
			$scope.message = "Message for Delete recipe: error - status: "
				+ response.status;
		})

		.finally(function(config)
		{
			// TO DO
			alert($scope.message);
		})
		// end http

	};
	// end delete


});
//end controller