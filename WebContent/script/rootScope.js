var myApp = angular
			.module("myModule", [])
			.controller("redColorController", function($scope, $rootScope){
				$scope.redColour = "It's a Red colour";
					$rootScope.rootRedColour =  "It's Root Scope RedScopecolour";
				
			})
			.controller("greenColorController", function($scope, $rootScope){
				
				$scope.greenColour = "It's a green colour";
				$rootScope.rootgreenColour =  "It's Root Scope greenScopecolour";
				
			})