var app = angular
		.module("routerEx", ["ngRoute"]);
			app.config(function($routeProvider, $locationProvider){
				$routeProvider.caseInsensitiveMatch = true;
				$routeProvider
					.when("/home", {
							templateUrl: "templates/home.html",
							/*template: "<h1> This is The Institution HomePage </h1>" +
									"<div>"+
								    "<h3>We are Started online Training & Real time Project support and development</h3>"+
								     "</div>"+ 
								     "<ul>"+
								     " <li>Training will delivered by Real Time Software Experts</li>"+
								     " <li>Training includes Realtime Scenerio's $ Templates </li>"+
								     "<li>Project Support for the real time Development</li>"+
								     "</ul>",*/
							controller: "homeController",
							controllerAs: "homeCntl"
					})
					.when("/courses", {
							templateUrl: "templates/courses.html",
							controller: "coursesController",
							controllerAs: "coursesCntl"						
					})
					.when("/students", {
							templateUrl: "templates/students.html",
							controller: "studentsController",
							controllerAs: "studentsCntl",
							resolve: {
								studentlist: function($http){
									return $http.get("http://localhost:8081/AngWithRS/rest/employee/getStudentsFromDB")
									.then(function(response){
										return response.data;
									})
									
								}
							}
					})
					.when("/students/:id", {
							templateUrl: "templates/studentDetails.html",
							controller: "studentDetailsController",
							controllerAs: "studentDetailsCntl"	
					})
					.when("/studentsSearch/:name", {
							templateUrl: "templates/studentSearch.html",
							controller: "studentSearchController",
							controllerAs: "studentSearchCntl"	
					})
					.when("/studentsSearch", {
							templateUrl: "templates/studentSearch.html",
							controller: "studentSearchController",
							controllerAs: "studentSearchCntl"	
					})
					/*.otherwise({
						redirectTo: "/home"
					})*/
					$locationProvider.html5Mode(true);
			});
			
			app.controller("coursesController", function($rootScope){
				$rootScope.courses = [
				                  "AngularJS",
				                  "Java",
				                  "Hibernate",
				                  "Spring",
				                  "Struts",
				                  "Java Script",
				                  "Html",
				                  "WebServices(Rest, SOAP)"
				                 ];
				
			});
			app.controller("studentsController", function(studentlist, $route, $scope, $rootScope, $log, $location){
				var vm = this;
				
				vm.searchStudent = function(){
				   if(vm.name){
					   $location.url("/studentsSearch/" +vm.name);
				   }else{
					   $location.url("/studentsSearch/");
				   }
				}
				vm.reloadData = function(){
					$route.reload();			
					
				}
				  vm.students = studentlist;
				/*$http.get("http://localhost:8081/AngWithRS/rest/employee/getStudentsFromDB")
				.then(function(response){
					vm.students = response.data;
				})*/
				
				
				
			/*	$scope.$on("$routeChangeStart", function (event, next, current){
					if(!confirm("are you sure you want to navigate from this page to " +next.$$route.originalPath)){
						event.preventDefault();
					}
				})*/
				
				/*$rootScope.$on("$locationChangeStart", function (event, next, current){
					if(!confirm("are you sure you want to navigate from this page to " +next)){
						event.preventDefault();
					}
				});*/
				
				
				/*$rootScope.$on("$locationChangeStart", function (event, next, current){
					$log.info("$locationChangeStart fired");
					$log.info(event);
					$log.info(next);
					$log.info(current);
				});
				
				$rootScope.$on("$routeChangeStart", function (event, next, current){
					$log.info("$routeChangeStart fired");
					$log.info(event);
					$log.info(next);
					$log.info(current);
				});*/
				
				/*$rootScope.$on("$locationChangeSuccess", function (event, next, current){
					$log.debug("$locationChangeSuccess fired");
				});
				
				$rootScope.$on("$routeChangeSuccess", function (event, next, current){
					$log.debug("$routeChangeSuccess fired");
				});
				*/
				
			
			});
			
			app.controller("studentDetailsController", function($http, $routeParams, $log){
				var vm = this;
				$http({
					url:"http://localhost:8081/AngWithRS/rest/employee/getStudentInfo",
					params: {id:$routeParams.id},
					method: "get"
				})
				.then(function(response){
					$log.info(response.data)
					vm.student = response.data;
				})
				
			
			});
			
			app.controller("studentSearchController", function($http, $routeParams, $log){
				var vm = this;
				if($routeParams.name){
				$http({
					url:"http://localhost:8081/AngWithRS/rest/employee/getNameMatchStudentsFromDB",
					params: {name:$routeParams.name},
					method: "get"
				})
				.then(function(response){
					$log.info(response.data)
					vm.students = response.data;
				})
				}else{
					$http.get("http://localhost:8081/AngWithRS/rest/employee/getStudentsFromDB")
					.then(function(response){
						vm.students = response.data;
					})
					
				}
				
			
			});
				