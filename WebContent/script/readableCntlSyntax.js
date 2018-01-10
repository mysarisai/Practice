var myApp = angular.module("readableSyntaxModule", [])
				   .controller("countryController", function(){
					   this.name = "India";
				   })
				   .controller("stateController", function(){
					   this.name = "Karnataka";
				   })
				    .controller("cityController", function(){
				    	this.name = "Bangalore";
				   })