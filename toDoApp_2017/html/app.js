var myApp = angular.module('todoApp', ['ui.router'])
.config(function ($stateProvider, $urlRouterProvider) {
  $stateProvider.state("home",{
    url:"/home",
    templateUrl:"template/home.html"
    // controller:""
  })
  .state("about",{
    url:"/about",
    templateUrl:"template/about.html"
  })
  .state("login",{
    url:"/login",
    templateUrl:"template/login.html",
    controller:"loginController"
  })
  .state("signUp",{
    url:"/create",
    templateUrl:"template/signUp.html",
    controller:"signupController"
  })
  $urlRouterProvider.otherwise('/home');

});
