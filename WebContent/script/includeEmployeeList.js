var myApp = angular
			.module("includeModule", [])
			.controller("includeController", function($scope){
				var employeeList = [
				                    {name:"Raj", gender:"male", salary:45000},
				                    {name:"Vinod", gender:"male", salary:55000},
				                    {name:"Surya", gender:"male", salary:65000},
				                    {name:"Anusha", gender:"female", salary:75000},
				                    {name:"Rekha", gender:"female", salary:85000}
				                   ];
				$scope.employees = employeeList;
				$scope.employeeview = "includeEmployeetable.html";
			});