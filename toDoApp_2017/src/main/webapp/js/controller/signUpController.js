var myApp = angular.module('todoApp')
myApp.controller('signupController', function($scope, $state, signUpService) {
    this.signUp = function() {
        // console.log($scope.user);
        var httpObj = signUpService.signup($scope.user)
        
        httpObj.then(function(data) {
            console.log(data);
            
            var resp = data.data;
            if( resp.status == 1)
            {
            	$state.go("login");
            }
            else
            {
            	console.log(resp);
            	var errors = resp.errorlist;
            	for( var i in errors)
            	{
            		var er = errors[i];
            		var fId = er.field;
            		$scope[fId] = er.defaultMessage; 
            	}
            	return false;
            }
        })
        .catch(function(error) {
            console.log(error);
        })
    }
});

myApp.service('signUpService',function($http){
	this.signup = function(user){ 
		return $http({
			url:"http://localhost:8080/toDoApp_2017/create",
			method:"post",
			data:user
		});
	}
	});
