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
			// TO DO
			$scope.recipe = response.data;
			console.log($scope.recipe);
		})

		.catch(function(response)
		{
			// It's OK not to take no action here
			// because it will be clear to the user if the
			// operation has been successful or not
		})

		.finally(function(config)
		{
			// OK to take no action
		})
		// end http

	};// end display




});
//end controller