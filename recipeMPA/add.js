/*
Name: Yuba Raj Khadka
Student Id: 18040483
Username: 18040483
Course Code: CSE4OAD

*/
var app = angular.module("myApp", []);

app.controller("myController", function($scope, $http){

	$scope.add = function() {

		var connection = $http(
			{
				// TO DO
				method: "post",
				url: "http://localhost:8080/recipeWS/recipes",
				data:
					{
						"id": $scope.id,
						"name": $scope.name,
						"serves": $scope.serves,
						"ingredients": $scope.ingredients,
						"steps": $scope.steps,
						"remarks": $scope.remarks
					}
			})

		.then(function(response)
			{
				// TO DO
				$scope.message = "Message for Add recipe: Success - status: " + response.status;
			})

		.catch(function(response)
			{
				// TO DO
				$scope.message = "Message for Add recipe: Error - status: " + response.status
					+ response.statusText;
			})

		.finally (function()
			{
				// TO DO
				alert($scope.message);
			});

	};

});
//end controller