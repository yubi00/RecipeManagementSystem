/*
Name: Yuba Raj Khadka
Student Id: 18040483
Username: 18040483
Course Code: CSE4OAD

*/
var app = angular.module("myApp", []);

app.controller("myController", function($scope, $http)
	{

		$scope.recipes = null;
		$scope.message = null;

		$scope.filterString = '';
		$scope.sortByName = false;
		$scope.sortOrder = '';
		$scope.setSortOrder = function()
		{
			if($scope.sortByName)
			{
				$scope.sortOrder = 'name';
			}
			else
			{
				$scope.sortOrder = '';
			}
		}

			 var connection =
			$http(
			{
				method: "get",
				url: "http://localhost:8080/recipeWS/recipes"
			})

			.then(function(response)		// process success request
			{
				$scope.recipes = response.data;
			})

			.catch(function(response)		// process error request
			{

			})
			.finally(function()
			{

			});


	});
	//end controller