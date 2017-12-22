/*var myApp = angular.module("myModule", [])
myApp.controller("myController", function($scope, $http){
				$http.get('http://localhost:8081/AngWithRS/rest/employee/findall')
				.success(function(response){
					$scope.employees = response.employee;
				});
				
			});*/

//it is working for methods params

/*var myApp = angular.module("myModule", []);
myApp.controller("myController", function($scope, $http){
	
	$http({
			method:'GET',
			url:'http://localhost:8081/AngWithRS/rest/employee/findall'})
			.success(function(response) {
		
	  $scope.employees = response.employee;
	})
	.catch(function(response) {
		alert("response.status"+response.status);
	  console.error('employee error', response.status, response.employee);
	})
	.finally(function() {
	  console.log("finally finished gists");
	});
				
	});*/
//not working
/*var myApp = angular.module("myModule", []);
myApp.service("dataService", function($http) {
    delete $http.defaults.headers.common['X-Requested-With'];
    this.getData = function() {
        // $http() returns a $promise that we can add handlers with .then()
        return $http({
            method: "GET",
            url: "http://localhost:8081/AngWithRS/rest/employee/findall"      
         })
     }
});

myApp.controller("myController", function($scope, dataService) {
    $scope.employees = null;
    dataService.getData().then(function(response) {
        $scope.employees = response.employee;
    });
});*/
var myApp = angular.module("myModule", []);
myApp.controller("myController", function($scope, $http) {
$http({
  method: 'GET',
  url: 'http://localhost:8081/AngWithRS/rest/employee/findall'
}).then(function successCallback(response) {
	 $scope.employees = response.data.employee;
  }, function errorCallback(response) {
	  console.log(response);
  });

});
/*.controller("myController", ["$scope", "$http", function ($scope, $http) { 
	  $http
	    .get('http://localhost:8081/AngWithRS/rest/employee/findall')
	    .then(function(response){
	    	alert("hello"+response.data.employee);
	      $scope.result = response.employee;
	    });
	}]) */