myApp.directive('myPinned', function() {
  return {
      strict: 'EA',
      templateUrl: 'template/myPinUp.html',
      controller:"homeController",
      controllerAs:"hc",
       scope:{
         "result":"=",
         "isList":"@"
	       }
  /*,
	       link:function link(scope, elm, attr, ctrl){

	           //Listen for event 
	           scope.$on('send', function(e, value){
	               //Retrieve car value from Controller
	               console.log(value);
	           });

	       }*/
	  }; 
})
