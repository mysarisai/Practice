var app = angular
		.module("routerEx", ["ui.router"]);
			app.config(function($stateProvider, $urlMatcherFactoryProvider, $urlRouterProvider, $locationProvider){
				$urlMatcherFactoryProvider.caseInsensitive(true);
				$urlRouterProvider.otherwise("home");
				$stateProvider
					.state("home", {
							url: "/home",
							templateUrl: "templates/home.html",
							controller: "homeController",
							controllerAs: "homeCntl"
					})
					.state("courses", {
							url:"/courses",
							templateUrl: "templates/courses.html",
							controller: "coursesController",
							controllerAs: "coursesCntl"						
					})
					.state("students", {
							url:"/students",
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
					.state("studentDetails", {
						 	url :"/students/:id",
							templateUrl: "templates/studentDetails.html",
							controller: "studentDetailsController",
							controllerAs: "studentDetailsCntl"	
					})
					.state("studentsSearch", {
							url:"/studentsSearch/:name",
							templateUrl: "templates/studentSearch.html",
							controller: "studentSearchController",
							controllerAs: "studentSearchCntl"	
					})
					.state("allStudentsSearch", {
							url :"/allStudentsSearch",
							templateUrl: "templates/studentSearch.html",
							controller: "studentSearchController",
							controllerAs: "studentSearchCntl"	
					})
					.state("addStudent", {
						url :"/addStudent",
						templateUrl: "templates/addStudent.html",
						controller: "addStudentController",
						controllerAs: "addStudentCntl"	
					})
					.state("editStudent", {
						url :"/editStudent",
						templateUrl: "templates/addStudent.html",
						controller: "editStudentController",
						controllerAs: "editStudentCntl"	
					})
				
					
					
					
					
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
			app.controller("studentsController", function(studentlist, $log, $location, $state){
				var vm = this;
				//This is call for Search Student
				vm.searchStudent = function(){
				   if(vm.name){
					   $state.go("studentsSearch", {name:vm.name});
				   }else{
					   $state.go("allStudentsSearch");
				   }
				}
				//this is the state call add Student
				vm.addStudent = function(){
						   $state.go("addStudent");
					 
					}
				
				
				vm.reloadData = function(){
					$state.reload();			
					
				}
				  vm.students = studentlist;
		
			});
			
			app.controller("studentDetailsController", function($http, $stateParams, $log, $state){
				var vm = this;
				$http({
					url:"http://localhost:8081/AngWithRS/rest/employee/getStudentInfo",
					params: {id:$stateParams.id},
					method: "get"
				})
				.then(function(response){
					$log.info(response.data)
					vm.student = response.data;
				})
				
			
			});
			
			app.controller("studentSearchController", function($http, $stateParams, $log, $scope, editStudentInfoService, $state){
				var vm = this;
				
				$scope.editStudent = function(stud){
					$log.info(stud);
					editStudentInfoService.set(stud);
					
					$state.go("editStudent");
				}
				
				if($stateParams.name){
				$http({
					url:"http://localhost:8081/AngWithRS/rest/employee/getNameMatchStudentsFromDB",
					params: {name:$stateParams.name},
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
			
			//addStudent Controller  addStudentController
			app.controller("addStudentController", function($http, $stateParams, $scope, $log){
				
				$scope.form = {
						studentId : "",
						studentName : "",
						gender : "",
						city :""
				}
				
				$scope.submitStudent = function(){
					$http({
						url: "http://localhost:8081/AngWithRS/rest/employee/addStudent",
						data :angular.toJson($scope.form),
						method :"POST",
						headers : {
							'contentType' : 'application/json'
						}
					}).then(success, error);
				}
				
				function success(response){
					$log.info(response.data);
					if(!confirm("Student is added Successfully")){
						event.preventDefault();
					}
					clearForm();
				}
				
				function error(response){
					if(!confirm("Adding Student is Failure")){
						event.preventDefault();
					}
					$log.info(response.statusText);
				}
				
				function clearForm(){
					$scope.form.studentId = "";
					$scope.form.studentName = "";
					$scope.form.gender = "";
					$scope.form.city = "";
				}
				
			});
			
			app.controller("editStudentController",  function($http, $state, $log, $scope, editStudentInfoService){
				
				$scope.form = {
						studentId : "",
						studentName : "",
						gender : "",
						city :""
				}
				
				$scope.form.studentId = editStudentInfoService.get().studentId;
				$scope.form.studentName = editStudentInfoService.get().studentName;
				$scope.form.gender = editStudentInfoService.get().gender;
				$scope.form.city = editStudentInfoService.get().city;
				
				$scope.submitStudent = function(){
				
				$http({
					url: "http://localhost:8081/AngWithRS/rest/employee/editStudent",
					data :angular.toJson($scope.form),
					method :"POST",
					headers : {
						'contentType' : 'application/json'
					}
				}).then(success, error);
			
			
			function success(response){
				$log.info(response.data);
				if(confirm("Student is Edited Successfully")){
					$state.go("students");
				}
				//clearForm();
			}
			
			function error(response){
				if(!confirm("Adding Student is Failure")){
					event.preventDefault();
				}
				$log.info(response.statusText);
			}
		}
				
			});
			
			
			app.factory("editStudentInfoService", function(){
				var savedData = {};
				function set(data){
					savedData =data;
				}
				
				function get(){
					return savedData
				}
				
				
				return{
					set: set,
					get: get
				}
			})
		
				